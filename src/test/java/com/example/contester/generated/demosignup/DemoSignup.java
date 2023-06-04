package com.example.contester.generated.demosignup;

import com.example.contester.util.HtmlUtil;

public class DemoSignup {

    private void fetchAllAttributes() {
        this.fetchEmail();
        this.fetchEmailErrorMessage();
        this.fetchAge();
        this.fetchAgeErrorMessage();
    }

    public DemoSignup() {
        this.fetchAllAttributes();
    }

    private String email;

    public void setEmail(String email) {
        HtmlUtil.setElementValue("email", email);
        this.fetchAllAttributes();
    }

    private void fetchEmail() {
        this.email = HtmlUtil.getElementValue("email");
    }

    private String emailErrorMessage;

    private void fetchEmailErrorMessage() {
        this.emailErrorMessage = HtmlUtil.getElementValue("emailErrorMessage");
    }

    private String age;

    public void setAge(String age) {
        HtmlUtil.setElementValue("age", age);
        this.fetchAllAttributes();
    }

    private void fetchAge() {
        this.age = HtmlUtil.getElementValue("age");
    }

    private String ageErrorMessage;

    private void fetchAgeErrorMessage() {
        this.ageErrorMessage = HtmlUtil.getElementValue("ageErrorMessage");
    }

    private String getElementAttribute(String elementId, String attributeName) {
        return HtmlUtil.getElementAttribute(elementId, attributeName);
    }
}
