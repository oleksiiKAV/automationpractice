package com.academy.automationpractice.ddt.framework.page;

import com.academy.automation.framework.page.BasePage;
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

    @FindBy(css="#center_column > div.alert.alert-danger > ol > li")
    private WebElement authMessageBlock;

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

    public BasePage clickSignIn(boolean success) {
        singInButton.click();
        if (success)
            return new AccountPage(driver);
        else
            return this;
    }

    public String getErrorMessage() {
        return authMessageBlock.getText().trim();
    }
}
