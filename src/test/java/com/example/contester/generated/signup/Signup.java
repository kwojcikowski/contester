package com.example.contester.generated.signup;

import com.example.contester.util.HtmlUtil;

public class Signup {

    private void fetchAllAttributes() {
        this.fetchEmail();
        this.fetchEmailErrorMessage();
        this.fetchPassword();
        this.fetchPasswordErrorMessage();
        this.fetchConfirmPassword();
        this.fetchConfirmPasswordErrorMessage();
        this.fetchAge();
        this.fetchAgeErrorMessage();
    }

    public Signup() {
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

    private String password;

    public void setPassword(String password) {
        HtmlUtil.setElementValue("password", password);
        this.fetchAllAttributes();
    }

    private void fetchPassword() {
        this.password = HtmlUtil.getElementValue("password");
    }

    private String passwordErrorMessage;

    private void fetchPasswordErrorMessage() {
        this.passwordErrorMessage = HtmlUtil.getElementValue("passwordErrorMessage");
    }

    private String confirmPassword;

    public void setConfirmPassword(String confirmPassword) {
        HtmlUtil.setElementValue("confirmPassword", confirmPassword);
        this.fetchAllAttributes();
    }

    private void fetchConfirmPassword() {
        this.confirmPassword = HtmlUtil.getElementValue("confirmPassword");
    }

    private String confirmPasswordErrorMessage;

    private void fetchConfirmPasswordErrorMessage() {
        this.confirmPasswordErrorMessage = HtmlUtil.getElementValue("confirmPasswordErrorMessage");
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
