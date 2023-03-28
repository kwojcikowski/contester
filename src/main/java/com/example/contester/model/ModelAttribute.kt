package com.example.contester.model

import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.ThisExpr
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ReturnStmt
import org.jsoup.nodes.Element
import osmo.tester.annotation.Variable

class ModelAttribute(
    val id: String,
    val name: String,
    val type: Class<*>,
) {

    companion object {
        const val TAG_NAME = "model-attribute"
        private val simpleTypesToClasses = mapOf<String, Class<*>>(
            "Integer" to Int::class.java
        )

        fun fromDocumentElement(element: Element): ModelAttribute {
            if (TAG_NAME != element.tagName())
                throw IllegalArgumentException("Provided element was not a '${TAG_NAME}' tag. Was ${element.tagName()} instead.")

            val type = simpleTypesToClasses[element.attr("type")]
                ?: throw IllegalArgumentException("Type not supported: '${simpleTypesToClasses[element.attr("type")]}'")

            return ModelAttribute(
                id = element.attr("id"),
                name = element.attr("name"),
                type = type
            )

        }
    }

    fun addToClassModel(classModel: ClassOrInterfaceDeclaration, fetchAllBody: BlockStmt) {
        classModel.addPrivateField(type, name)
            .addAnnotation(Variable::class.java)

        val fetchMethod = classModel.addMethod("fetch${name.replaceFirstChar { it.uppercase() }}")
            .addModifier(Modifier.Keyword.PRIVATE)
        fetchMethod.createBody()
            .addStatement("this.${name} = ${"this.webDriver.findElement(By.id(\"$id\")).getText()".asClass(type)};")

        fetchAllBody.addStatement(MethodCallExpr(ThisExpr(), fetchMethod.name))
    }

    private fun String.asClass(clazz: Class<*>): String {
        return when (clazz) {
            Int::class.java -> "Integer.parseInt($this)"
            else -> this
        }
    }

}
