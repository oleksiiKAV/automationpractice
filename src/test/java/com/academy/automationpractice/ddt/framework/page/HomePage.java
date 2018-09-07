package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage  extends BasePage {

    @FindBy(linkText = "Sign in")
    private WebElement signInLink;

    @FindBy(css = "li.sfHoverForce a[title='Dresses']")
    private  WebElement dressesLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickSignIn() {
        signInLink.click();
        return new LoginPage(driver);
    }

    public DressesPage clickDressesLink(){
        dressesLink.click();
        return new DressesPage(driver);
    }
}
