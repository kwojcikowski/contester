package com.example.contester

import com.example.contester.generator.TestCaseGenerator
import com.example.contester.generator.TestModelGenerator
import com.example.contester.model.Model
import com.example.contester.provider.WebDocumentProvider
import org.mdkt.compiler.InMemoryJavaCompiler
import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser
import tudresden.ocl20.pivot.standalone.facade.StandaloneFacade
import tudresden.ocl20.pivot.tools.codegen.ocl2java.Ocl2JavaFactory

class ContesterApplication {
    companion object {

        private const val LOG4J_PROPERTIES_PATH = "log4j.properties"
        private const val SOURCE_PATH = "src/test/java"

        private var MODEL_GENERATION_PATH = "src/test/java/com/example/contester/generated/#modelName"
        private var MODEL_GENERATION_PACKAGE = "com.example.contester.generated.#modelName"
        private var TEST_CASE_GENERATION_PATH = "src/test/java/com/example/contester/generated/#modelName"
        private var TEST_CASE_GENERATION_PACKAGE = "com.example.contester.generated.#modelName"

        @JvmStatic
        fun main(args: Array<String>) {
            StandaloneFacade.INSTANCE.initialize(this::class.java.getResource(LOG4J_PROPERTIES_PATH))
            val url = args[0]
            val docProvider = WebDocumentProvider()

            docProvider.get(url) // 1. Get HTML file
                .getElementsByAttribute(Model.ATTRIBUTE_NAME)
                .asSequence()
                .map { Model.fromDocumentElement(it) }    // 2. Parse HTML tags into model class
                .map {
                    MODEL_GENERATION_PATH = MODEL_GENERATION_PATH.replace("#modelName", it.name.lowercase())
                    MODEL_GENERATION_PACKAGE = MODEL_GENERATION_PACKAGE.replace("#modelName", it.name.lowercase())
                    TEST_CASE_GENERATION_PATH = TEST_CASE_GENERATION_PATH.replace("#modelName", it.name.lowercase())
                    TEST_CASE_GENERATION_PACKAGE = TEST_CASE_GENERATION_PACKAGE.replace("#modelName", it.name.lowercase())
                    it
                }
                .map {
                    TestModelGenerator.generateJavaTestModel(
                        it,
                        MODEL_GENERATION_PATH,
                        MODEL_GENERATION_PACKAGE
                    )
                } // 3. Generate java test model class
                .map { // 4. Compile java test model class
                    val compiledModelClass = InMemoryJavaCompiler.newInstance()
                        .compile("$MODEL_GENERATION_PACKAGE.${it.model.name}", it.code)
                    Pair(it, compiledModelClass)
                }
                .map { // 5. Generate constraints as AspectJ files
                    val iModel = StandaloneFacade.INSTANCE.loadJavaModel(it.second)
                    val constraints = Ocl22Parser.INSTANCE.parseOclString(it.first.constraints, iModel)
                    val settings = Ocl2JavaFactory.getInstance().createJavaCodeGeneratorSettings()
                    settings.sourceDirectory = SOURCE_PATH
                    settings.setDefaultInvariantCheckMode(2)
                    StandaloneFacade.INSTANCE.generateAspectJCode(constraints, settings)
                    it.first
                }
                .toList()
                .forEach { // 6. Generate test cases
                    TestCaseGenerator.generateProvidedTestCases(it.model, url, TEST_CASE_GENERATION_PATH, TEST_CASE_GENERATION_PACKAGE)
                }

            // 7. Run tests and enjoy :)
        }
    }

}
