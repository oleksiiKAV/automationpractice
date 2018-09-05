package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage {
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement singInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage inputEmail(String email) {
        inputTextField(emailField, email);
        return this;
    }

    public LoginPage inputPassword(String password) {
        inputTextField(passwordField, password);
        return this;
    }

    public AccountPage clickSingIn() {
        singInButton.click();
        return new AccountPage(driver);
    }
}
