package com.example.contester.model

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.ThisExpr
import org.jsoup.nodes.Element
import osmo.tester.annotation.TestStep

class ModelFunction(
    val id: String,
    val name: String,
    val constraints: List<Constraint>
) {
    companion object {

        const val ATTRIBUTE_NAME = "model-function"
        fun fromDocumentElement(element: Element): ModelFunction {
            if (!element.hasAttr(ATTRIBUTE_NAME))
                throw IllegalArgumentException("Provided element does not have a '${ATTRIBUTE_NAME}' attribute: $element")

            return ModelFunction(
                id = element.attr("id"),
                name = element.attr(ATTRIBUTE_NAME),
                constraints = element.children()
                    .filter { child -> child.tagName().equals(Constraint.TAG_NAME) }
                    .map { Constraint.fromDocumentElement(it) }
            )
        }
    }

    fun addToClassModel(classModel: ClassOrInterfaceDeclaration, fetchAllMethod: MethodDeclaration) {
        classModel.addMethod(name)
            .setPublic(true)
            .addSingleMemberAnnotation(TestStep::class.java, "\"$name\"")
            .createBody()
            .addStatement("HtmlUtil.clickElement(\"$id\");")
            .addStatement(MethodCallExpr(ThisExpr(), fetchAllMethod.name))
    }

    fun getConstraintsAsString(parentContext: String?): String =
        constraints.joinToString("\n") { "$parentContext::$name() ${it.text}" }


}
