package com.example.contester

import com.example.contester.generator.TestCaseGenerator
import com.example.contester.generator.TestModelGenerator
import com.example.contester.model.Model
import com.example.contester.provider.DocumentProvider
import org.mdkt.compiler.InMemoryJavaCompiler
import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser
import tudresden.ocl20.pivot.standalone.facade.StandaloneFacade
import tudresden.ocl20.pivot.tools.codegen.ocl2java.Ocl2JavaFactory

class ContesterApplication {
    companion object {

        private const val LOG4J_PROPERTIES_PATH = "log4j.properties"
        private const val SOURCE_PATH = "src/test/java"
        private const val MODEL_GENERATION_PATH = "src/test/java/com/example/contester/model/generated"
        private const val MODEL_GENERATION_PACKAGE = "com.example.contester.model.generated"
        private const val TEST_CASE_GENERATION_PATH = "src/test/java/com/example/contester/model/generated"

        @JvmStatic
        fun main(args: Array<String>) {
            StandaloneFacade.INSTANCE.initialize(this::class.java.getResource(LOG4J_PROPERTIES_PATH))

            DocumentProvider.getDocumentFromFile(DocumentProvider.COUNTER_HTML_FILE_PATH) // 1. Get HTML file
                .getElementsByTag(Model.TAG_NAME)
                .asSequence()
                .map { Model.fromDocumentElement(it) }    // 2. Parse HTML tags into model class
                .map { TestModelGenerator.generateJavaTestModel(it, MODEL_GENERATION_PATH) } // 3. Generate java test model class
                .map { // 4. Compile java test model class
                    val compiledModelClass = InMemoryJavaCompiler.newInstance()
                        .compile("$MODEL_GENERATION_PACKAGE.${it.className}", it.code)
                    Pair(it, compiledModelClass)
                }
                .map { // 5. Generate constraints as AspectJ files
                    val iModel = StandaloneFacade.INSTANCE.loadJavaModel(it.second)
                    val constraints = Ocl22Parser.INSTANCE.parseOclString(it.first.constraints, iModel)
                    val settings = Ocl2JavaFactory.getInstance().createJavaCodeGeneratorSettings()
                    settings.sourceDirectory = SOURCE_PATH
                    StandaloneFacade.INSTANCE.generateAspectJCode(constraints, settings)
                    it.second
                }
                .forEach { // 6. Generate test cases
                    TestCaseGenerator.generateTestCases(it, TEST_CASE_GENERATION_PATH)
                }

            // 7. Run tests and enjoy :)
        }
    }

}
