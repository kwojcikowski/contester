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

        const val TAG_NAME = "model-function"
        fun fromDocumentElement(element: Element): ModelFunction {
            if (TAG_NAME != element.tagName())
                throw IllegalArgumentException("Provided element was not a '${TAG_NAME}' tag. Was ${element.tagName()} instead.")

            return ModelFunction(
                id = element.attr("id"),
                name = element.attr("name"),
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
            .addThrownException(InterruptedException::class.java)
            .createBody()
            .addStatement("this.webDriver.findElement(By.id(\"$id\")).click();")
            .addStatement("Thread.sleep(200);")
            .addStatement(MethodCallExpr(ThisExpr(), fetchAllMethod.name))
    }

    fun getConstraintsAsString(parentContext: String?): String =
        constraints.joinToString("\n") { "$parentContext::$name() ${it.text}" }


}