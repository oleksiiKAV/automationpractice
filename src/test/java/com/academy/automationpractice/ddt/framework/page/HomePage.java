package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage  extends BasePage {

    @FindBy(linkText = "Sign in")
    private WebElement signInLink;

    @FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='Summer Dresses'])[1]/following::a[1]")
    private WebElement dress;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickSignIn() {
        signInLink.click();
        return new LoginPage(driver);
    }

    public DressesPage clickDress() {
        dress.click();
        return new DressesPage(driver);
    }
}
