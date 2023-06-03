package com.example.contester.generator

import com.example.contester.model.Model
import com.github.javaparser.ast.CompilationUnit
import org.apache.commons.io.FileUtils
import java.io.File

object TestModelGenerator {

    fun generateJavaTestModel(model: Model, destPath: String, packageName: String): GeneratorOutput {
        val compilationUnit = CompilationUnit()
        compilationUnit.setPackageDeclaration(packageName)
        model.addToCompilationUnit(compilationUnit)
        val sourceCode = compilationUnit.toString()
        FileUtils.writeStringToFile(File("${destPath}/${model.name}.java"), sourceCode, "UTF-8")
        return GeneratorOutput(model, sourceCode, model.getConstraintsAsString())
    }

}

data class GeneratorOutput(
    val model: Model,
    val code: String,
    val constraints: String
)
