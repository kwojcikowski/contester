package com.example.contester.provider

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.io.IOException

object DocumentProvider {

    val COUNTER_HTML_FILE_PATH = this::class.java.getResource("/static/counter.html")!!.path

    fun getDocumentFromFile(filePath: String): Document {
        return try {
            Jsoup.parse(File(filePath))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}