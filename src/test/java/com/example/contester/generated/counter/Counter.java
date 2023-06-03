package com.example.contester.generated.counter;

import com.example.contester.util.HtmlUtil;
import osmo.tester.annotation.TestStep;

public class Counter {

    private void fetchAllAttributes() {
        this.fetchCount();
    }

    public Counter() {
        this.fetchAllAttributes();
    }

    private String count;

    private void fetchCount() {
        this.count = HtmlUtil.getElementValue("count-span");
    }

    @TestStep("decreaseCount")
    public void decreaseCount() {
        HtmlUtil.clickElement("decrease-count-btn");
        this.fetchAllAttributes();
    }

    @TestStep("increaseCount")
    public void increaseCount() {
        HtmlUtil.clickElement("increase-count-btn");
        this.fetchAllAttributes();
    }

    @TestStep("resetState")
    public void resetState() {
        HtmlUtil.clickElement("reset-state-btn");
        this.fetchAllAttributes();
    }

    private String getElementAttribute(String elementId, String attributeName) {
        return HtmlUtil.getElementAttribute(elementId, attributeName);
    }
}
