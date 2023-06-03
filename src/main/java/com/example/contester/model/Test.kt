package com.example.contester.model

import org.jsoup.nodes.Element

class Test(
    val name: String,
    val content: String
) {

    companion object {
        const val TAG_NAME = "test"

        fun fromDocumentElement(element: Element): Test =
            if (TAG_NAME != element.tagName())
                throw IllegalArgumentException("Provided element was not a '${TAG_NAME}' tag. Was ${element.tagName()} instead.")
            else
                Test(name = element.attr("name"), content = element.text())
    }

}