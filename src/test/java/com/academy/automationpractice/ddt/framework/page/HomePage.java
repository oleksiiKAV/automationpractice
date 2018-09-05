package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage  extends BasePage {
    @FindBy(linkText = "Sign in")
    private WebElement signInLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public LoginPage clickSingIn() {
        signInLink.click();
        return new LoginPage(driver);
    }
}
