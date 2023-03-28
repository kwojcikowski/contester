package com.example.contester.model

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import org.jsoup.nodes.Element
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class Model(
    val name: String,
    val attributes: List<ModelAttribute>,
    val functions: List<ModelFunction>,
    val constraints: List<Constraint>
) {

    companion object {
        const val TAG_NAME = "model"

        fun fromDocumentElement(element: Element): Model =
            if (TAG_NAME != element.tagName())
                throw IllegalArgumentException("Provided element was not a '${TAG_NAME}' tag. Was ${element.tagName()} instead.")
            else Model(
                name = element.attr("name"),
                attributes = element.getElementsByTag(ModelAttribute.TAG_NAME).map { ModelAttribute.fromDocumentElement(it) },
                functions = element.getElementsByTag(ModelFunction.TAG_NAME).map { ModelFunction.fromDocumentElement(it) },
                constraints = element.children()
                    .filter { child -> child.tagName().equals(Constraint.TAG_NAME) }
                    .map { Constraint.fromDocumentElement(it) }
            )
    }

    fun addToCompilationUnit(compilationUnit: CompilationUnit) {
        compilationUnit.setPackageDeclaration("com.example.contester.model.generated")
        compilationUnit.addImport(WebDriver::class.java)
        compilationUnit.addImport(By::class.java)

        val modelClass = compilationUnit.addClass(this.name)
            .setPublic(true)
        modelClass.addPrivateField(WebDriver::class.java, "webDriver")
            .addModifier(Modifier.Keyword.FINAL)
        modelClass.addConstructor(Modifier.Keyword.PUBLIC)
            .addParameter(WebDriver::class.java, "webDriver")
            .createBody()
            .addStatement("this.webDriver = webDriver;")

        val fetchAllMethod = modelClass.addMethod("fetchAllAttributes", Modifier.Keyword.PRIVATE)
        val fetchAllBody = fetchAllMethod.createBody()

        this.attributes.forEach { it.addToClassModel(modelClass, fetchAllBody) }
        this.functions.forEach { it.addToClassModel(modelClass, fetchAllMethod) }
    }

    fun getConstraintsAsString(parentContext: String?): String =
        constraints.joinToString("\n") { "context $name ${it.text}" } + "\n" +
                functions.joinToString("\n") { it.getConstraintsAsString("context $name") }

}
