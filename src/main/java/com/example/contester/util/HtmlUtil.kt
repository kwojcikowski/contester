package com.example.contester.util

import org.openqa.selenium.*

class HtmlUtil {

    companion object {

        lateinit var webDriver: WebDriver

        @JvmStatic
        fun getElementAttribute(elementId: String?, attributeName: String?): String {
            return webDriver.findElement(By.id(elementId)).getAttribute(attributeName)
        }

        @JvmStatic
        fun getElementValue(elementId: String): String {
            val element = webDriver.findElement(By.id(elementId))
            return when(element.tagName) {
                "p" -> element.text
                "span" -> element.text
                else -> element.getAttribute("value")
            }
        }

        @JvmStatic
        fun setElementValue(elementId: String, content: String) {
            val elem: WebElement = webDriver.findElement(By.id(elementId))
            elem.sendKeys(Keys.CONTROL.toString() + "a")
            elem.sendKeys(Keys.DELETE)
            elem.clear()
            elem.sendKeys(content)
            blur()
        }

        @JvmStatic
        fun clickElement(elementId: String) {
            val element = webDriver.findElement(By.id(elementId))
            element.click()
        }

        private fun blur() {
            (webDriver as JavascriptExecutor).executeScript("!!document.activeElement ? document.activeElement.blur() : 0")
        }

    }

}