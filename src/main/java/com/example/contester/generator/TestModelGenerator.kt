package com.example.contester.generator

import com.example.contester.model.Model
import com.github.javaparser.ast.CompilationUnit
import java.io.File

object TestModelGenerator {

    fun generateJavaTestModel(model: Model, destPath: String): GeneratorOutput {
        val compilationUnit = CompilationUnit()
        model.addToCompilationUnit(compilationUnit)
        val sourceCode = compilationUnit.toString()
        File("${destPath}/${model.name}.java").writeText(sourceCode)
        return GeneratorOutput(model.name, sourceCode, model.getConstraintsAsString(null))
    }

}

data class GeneratorOutput(
    val className: String,
    val code: String,
    val constraints: String
)
