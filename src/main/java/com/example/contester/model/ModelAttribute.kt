package com.example.contester.model

import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.ThisExpr
import org.jsoup.nodes.Element


class ModelAttribute(
    val id: String,
    val name: String,
    val tag: String,
    val constraints: List<Constraint>
) {

    private val settableElements = arrayOf("input", "textarea")

    companion object {
        const val ATTRIBUTE_NAME = "model-attribute"

        fun fromDocumentElement(element: Element): ModelAttribute {
            if (!element.hasAttr(ATTRIBUTE_NAME))
                throw IllegalArgumentException("Provided element does not have a '${ATTRIBUTE_NAME}' attribute: $element")

            return ModelAttribute(
                id = element.attr("id"),
                name = element.attr("name").ifEmpty { element.attr("id") },
                tag = element.tagName(),
                constraints = element.children()
                    .filter { child -> child.tagName().equals(Constraint.TAG_NAME) }
                    .map { Constraint.fromDocumentElement(it) }
            )
        }
    }

    fun addToClassModel(classModel: ClassOrInterfaceDeclaration, fetchAllMethod: MethodDeclaration) {
        val field = classModel.addPrivateField(String::class.java, name)
        if (tag in settableElements) {
            val setter = field.createSetter()
            setter.createBody()
                .addStatement("HtmlUtil.setElementValue(\"$id\", ${setter.parameters[0].name});")
                .addStatement(MethodCallExpr(ThisExpr(), fetchAllMethod.name))
        }

        val fetchMethod = classModel.addMethod("fetch${name.replaceFirstChar { it.uppercase() }}")
            .addModifier(Modifier.Keyword.PRIVATE)
        fetchMethod.createBody()
            .addStatement("this.$name = HtmlUtil.getElementValue(\"$id\");")

        fetchAllMethod.body.get()
            .addStatement(MethodCallExpr(ThisExpr(), fetchMethod.name))
    }

    fun getConstraintsAsString(parentContext: String?): String =
        constraints.joinToString("\n") { "$parentContext::$name() ${it.text}" }

}
