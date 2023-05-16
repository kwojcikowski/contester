package com.example.contester.provider

import io.github.bonigarcia.wdm.WebDriverManager
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.io.IOException

interface DocumentProvider {

    fun get(location: String): Document

}

class FileDocumentProvider: DocumentProvider {

    companion object {
        val COUNTER_HTML_FILE_PATH = this::class.java.getResource("/static/counter.html")!!.path
        val SIGNUP_HTML_FILE_PATH = this::class.java.getResource("/static/signup.html")!!.path
    }
    override fun get(location: String): Document {
        return try {
            Jsoup.parse(File(location))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

}

class WebDocumentProvider: DocumentProvider {
    override fun get(location: String): Document {
        WebDriverManager.chromedriver().setup()
        val options = ChromeOptions()
        options.addArguments("--remote-allow-origins=*")
        val webDriver = ChromeDriver(options)
        webDriver.get(location)
        val source = webDriver.pageSource
        return try {
            Jsoup.parse(source)
        } catch (e: IOException) {
            throw RuntimeException(e)
        } finally {
            webDriver.close()
        }
    }

}