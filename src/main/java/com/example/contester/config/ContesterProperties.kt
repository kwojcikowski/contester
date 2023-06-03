package com.example.contester.config

import com.example.contester.ContesterApplication
import java.util.*

class ContesterProperties(
    val modelGenerationPath: String,
    val modelGenerationPackage: String,
    val testCaseGenerationPath: String,
    val testCaseGenerationPackage: String,
    val oclSrcPath: String,
    val log4jPropertiesPath: String
) {

    companion object {

        private const val MODEL_GENERATION_PATH_KEY = "contester.model-generation-path"
        private const val MODEL_GENERATION_PACKAGE_KEY = "contester.model-generation-package"
        private const val TEST_CASE_GENERATION_PATH_KEY = "contester.test-case-generation-path"
        private const val TEST_CASE_GENERATION_PACKAGE_KEY = "contester.test-case-generation-package"
        private const val OCL_SRC_PATH_KEY = "contester.ocl-src-path"
        private const val LOG4J_PROPERTIES_PATH_KEY = "contester.log4j-properties-path"

        fun fromProperties(propertiesFile: String = "/application.properties"): ContesterProperties {
            val properties = Properties()
            this::class.java.getResourceAsStream(propertiesFile).use { properties.load(it) }
            return ContesterProperties(
                properties.getProperty(MODEL_GENERATION_PATH_KEY),
                properties.getProperty(MODEL_GENERATION_PACKAGE_KEY),
                properties.getProperty(TEST_CASE_GENERATION_PATH_KEY),
                properties.getProperty(TEST_CASE_GENERATION_PACKAGE_KEY),
                properties.getProperty(OCL_SRC_PATH_KEY),
                properties.getProperty(LOG4J_PROPERTIES_PATH_KEY),
            )
        }

    }

}