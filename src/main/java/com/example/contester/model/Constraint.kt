package com.example.contester.model

import org.jsoup.nodes.Element

data class Constraint(
    val text: String
) {
    companion object {
        val TAG_NAME = "constraint"

        fun fromDocumentElement(element: Element): Constraint =
            if (TAG_NAME != element.tagName())
                throw IllegalArgumentException("Provided element was not a '${TAG_NAME}' tag. Was ${element.tagName()} instead.")
            else
                Constraint(element.text())
    }

}