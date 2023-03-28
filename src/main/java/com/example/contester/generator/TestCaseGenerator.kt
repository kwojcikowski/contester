package com.example.contester.generator

import com.example.contester.generator.osmo.MyOSMOTester
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.ThisExpr
import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import osmo.tester.OSMOConfiguration
import osmo.tester.coverage.ScoreConfiguration
import osmo.tester.generator.SingleInstanceModelFactory
import osmo.tester.generator.algorithm.BalancingAlgorithm
import osmo.tester.generator.endcondition.Length
import osmo.tester.generator.endcondition.LengthProbability
import java.io.File

class TestCaseGenerator {

    companion object {

        fun generateTestCases(model: Class<*>, destPath: String) {
            val tester = MyOSMOTester();
            val oc: OSMOConfiguration = tester.config
            oc.setAlgorithm(BalancingAlgorithm())
            oc.setTestEndCondition(LengthProbability(5, 10, 0.2))
            oc.suiteEndCondition = Length(5)
            oc.factory = SingleInstanceModelFactory().also { it.add(model.declaredConstructors.first().newInstance(null)) }
            tester.generate(112)

            val compilationUnit = CompilationUnit()
            compilationUnit.setPackageDeclaration("com.example.contester.model.generated")
            compilationUnit.addImport(WebDriverManager::class.java)
            compilationUnit.addImport(ChromeOptions::class.java)
            compilationUnit.addImport(ChromeDriver::class.java)

            val modelTestClass = compilationUnit.addClass("${model.simpleName}Tests")
                .setPublic(true)

            modelTestClass.addPrivateField(WebDriver::class.java, "webDriver")
            modelTestClass.addPrivateField(model, "sut")

            modelTestClass.addMethod("setUp", Modifier.Keyword.PUBLIC)
                .addAnnotation(BeforeEach::class.java)
                .createBody()
                .addStatement("WebDriverManager.chromedriver().setup();")
                .addStatement("ChromeOptions options = new ChromeOptions();")
                .addStatement("options.addArguments(\"--remote-allow-origins=*\");")
                .addStatement("webDriver = new ChromeDriver(options);")
                .addStatement("sut = new ${model.simpleName}(webDriver);")
                .addStatement("webDriver.get(\"http://localhost:3000\");")

            modelTestClass.addMethod("tearDown", Modifier.Keyword.PUBLIC)
                .addAnnotation(AfterEach::class.java)
                .createBody()
                .addStatement("webDriver.close();")

            tester.suite.allTestCases.forEach { testCase ->
                val body = modelTestClass.addMethod(testCase.name, Modifier.Keyword.PUBLIC)
                    .addAnnotation(Test::class.java)
                    .addThrownException(InterruptedException::class.java)
                    .createBody()

                testCase.allStepNames.forEach {
                    body.addStatement(MethodCallExpr(FieldAccessExpr(ThisExpr(), "sut"), it))
                }
            }

            val sourceCode = compilationUnit.toString()
            File("$destPath/${model.simpleName}Tests.java").writeText(sourceCode)
        }
    }

}