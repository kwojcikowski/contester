package com.example.contester.model

import com.example.contester.util.HtmlUtil
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.ThisExpr
import org.jsoup.nodes.Element
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class Model(
    val name: String,
    val attributes: List<ModelAttribute>,
    val functions: List<ModelFunction>,
    val constraints: List<Constraint>,
    val tests: List<Test>
) {

    companion object {
        const val ATTRIBUTE_NAME = "model"

        fun fromDocumentElement(element: Element): Model =
            if (!element.hasAttr(ATTRIBUTE_NAME))
                throw IllegalArgumentException("Provided element does not have a '${ATTRIBUTE_NAME}' attribute: $element")
            else Model(
                name = element.attr("name"),
                attributes = element.getElementsByAttribute(ModelAttribute.ATTRIBUTE_NAME)
                    .map { ModelAttribute.fromDocumentElement(it) },
                functions = element.getElementsByTag(ModelFunction.ATTRIBUTE_NAME)
                    .map { ModelFunction.fromDocumentElement(it) },
                constraints = element.children()
                    .filter { child -> child.tagName().equals(Constraint.TAG_NAME) }
                    .map { Constraint.fromDocumentElement(it) },
                tests = element.children()
                    .filter { child -> child.tagName().equals(Test.TAG_NAME) }
                    .map { Test.fromDocumentElement(it) }
            )
    }

    fun addToCompilationUnit(compilationUnit: CompilationUnit) {
        compilationUnit.addImport(HtmlUtil::class.java)

        val modelClass = compilationUnit.addClass(this.name)
            .setPublic(true)

        val fetchAllMethod = modelClass.addMethod("fetchAllAttributes", Modifier.Keyword.PRIVATE)
        val fetchAllBody = fetchAllMethod.createBody()

        modelClass.addConstructor(Modifier.Keyword.PUBLIC)
            .createBody()
            .addStatement(MethodCallExpr(ThisExpr(), fetchAllMethod.name))

        this.attributes.forEach { it.addToClassModel(modelClass, fetchAllMethod) }
        this.functions.forEach { it.addToClassModel(modelClass, fetchAllMethod) }

        modelClass.addMethod("getElementAttribute", Modifier.Keyword.PRIVATE)
            .setType(String::class.java)
            .addParameter(String::class.java, "elementId")
            .addParameter(String::class.java, "attributeName")
            .createBody()
            .addStatement("return HtmlUtil.getElementAttribute(elementId, attributeName);")
    }

    fun getConstraintsAsString(parentContext: String?): String =
        constraints.joinToString("\n") { "context $name ${it.text}" } + "\n" +
                functions.joinToString("\n") { it.getConstraintsAsString("context $name") }

}
