package com.example.contester.generated.signup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import com.example.contester.util.HtmlUtil;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class SignupTests {

    private String url = "http://localhost:3000/signup";

    private WebDriver webDriver;

    private Signup sut;

    @BeforeEach()
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.get(url);
        HtmlUtil.webDriver = webDriver;
        sut = new Signup();
    }

    @AfterEach()
    public void tearDown() {
        webDriver.close();
    }

    @Test()
    public void testCorrectFormValues() {
        sut.setEmail("mail@mail.com");
        sut.setPassword("mypassword");
        sut.setConfirmPassword("mypassword");
        sut.setAge("35");
    }

    @Test()
    public void testIncorrectFormValues() {
        sut.setEmail("incorrect");
        sut.setPassword("a");
        sut.setConfirmPassword("b");
        sut.setAge("-1");
    }
}
