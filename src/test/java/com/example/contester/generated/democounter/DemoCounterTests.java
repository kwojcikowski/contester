package com.example.contester.generated.democounter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import com.example.contester.util.HtmlUtil;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class DemoCounterTests {

    private String url = "http://localhost:3000/demo-counter";

    private WebDriver webDriver;

    private DemoCounter sut;

    @BeforeEach()
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.get(url);
        HtmlUtil.webDriver = webDriver;
        sut = new DemoCounter();
    }

    @AfterEach()
    public void tearDown() {
        webDriver.close();
    }

    @Test()
    public void Test1() throws InterruptedException {
        this.sut.increaseCount();
        this.sut.resetState();
        this.sut.increaseCount();
        this.sut.increaseCount();
        this.sut.increaseCount();
    }

    @Test()
    public void Test2() throws InterruptedException {
        this.sut.resetState();
        this.sut.resetState();
        this.sut.increaseCount();
        this.sut.resetState();
        this.sut.increaseCount();
    }

    @Test()
    public void Test3() throws InterruptedException {
        this.sut.resetState();
        this.sut.resetState();
        this.sut.increaseCount();
        this.sut.resetState();
        this.sut.increaseCount();
        this.sut.increaseCount();
        this.sut.increaseCount();
        this.sut.increaseCount();
    }

    @Test()
    public void Test4() throws InterruptedException {
        this.sut.increaseCount();
        this.sut.resetState();
        this.sut.resetState();
        this.sut.resetState();
        this.sut.resetState();
        this.sut.increaseCount();
        this.sut.resetState();
        this.sut.resetState();
    }

    @Test()
    public void Test5() throws InterruptedException {
        this.sut.increaseCount();
        this.sut.increaseCount();
        this.sut.resetState();
        this.sut.increaseCount();
        this.sut.increaseCount();
    }
}
