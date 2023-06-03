package com.example.contester

import com.example.contester.config.ContesterProperties
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
        @JvmStatic
        fun main(args: Array<String>) {
            val properties = ContesterProperties.fromProperties()

            StandaloneFacade.INSTANCE.initialize(this::class.java.getResource(properties.log4jPropertiesPath))
            val url = args[0]
            val docProvider = WebDocumentProvider()

            docProvider.get(url) // 1. Get HTML file
                .getElementsByAttribute(Model.ATTRIBUTE_NAME)
                .asSequence()
                .map { Model.fromDocumentElement(it) }    // 2. Parse HTML tags into model class
                .map {
                    TestModelGenerator.generateJavaTestModel(
                        it,
                        properties.modelGenerationPath + "/${it.name.lowercase()}",
                        properties.modelGenerationPackage + ".${it.name.lowercase()}"
                    )
                } // 3. Generate java test model class
                .map { // 4. Compile java test model class
                    val compiledModelClass = InMemoryJavaCompiler.newInstance()
                        .compile(properties.modelGenerationPackage + ".${it.model.name.lowercase()}.${it.model.name}", it.code)
                    Pair(it, compiledModelClass)
                }
                .map { // 5. Generate constraints as AspectJ files
                    val iModel = StandaloneFacade.INSTANCE.loadJavaModel(it.second)
                    val constraints = Ocl22Parser.INSTANCE.parseOclString(it.first.constraints, iModel)
                    val settings = Ocl2JavaFactory.getInstance().createJavaCodeGeneratorSettings()
                    settings.sourceDirectory = properties.oclSrcPath
                    settings.setDefaultInvariantCheckMode(2)
                    StandaloneFacade.INSTANCE.generateAspectJCode(constraints, settings)
                    it.first
                }
                .toList()
                .forEach { // 6. Generate test cases
                    TestCaseGenerator.generateProvidedTestCases(
                        it.model,
                        url,
                        properties.testCaseGenerationPath + "/${it.model.name.lowercase()}",
                        properties.testCaseGenerationPackage + ".${it.model.name.lowercase()}"
                    )
                }

            // 7. Run tests and enjoy :)
        }
    }

}
