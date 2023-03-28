package com.example.contester.model.generated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import osmo.tester.annotation.Variable;
import osmo.tester.annotation.TestStep;

public class CounterModel {

    private final WebDriver webDriver;

    public CounterModel(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void fetchAllAttributes() {
        this.fetchCount();
    }

    @Variable()
    private int count;

    private void fetchCount() {
        this.count = Integer.parseInt(this.webDriver.findElement(By.id("ma1")).getText());
    }

    @TestStep("decreaseCount")
    public void decreaseCount() throws InterruptedException {
        this.webDriver.findElement(By.id("mf1")).click();
        Thread.sleep(200);
        this.fetchAllAttributes();
    }

    @TestStep("increaseCount")
    public void increaseCount() throws InterruptedException {
        this.webDriver.findElement(By.id("mf2")).click();
        Thread.sleep(200);
        this.fetchAllAttributes();
    }

    @TestStep("resetState")
    public void resetState() throws InterruptedException {
        this.webDriver.findElement(By.id("mf3")).click();
        Thread.sleep(200);
        this.fetchAllAttributes();
    }
}
