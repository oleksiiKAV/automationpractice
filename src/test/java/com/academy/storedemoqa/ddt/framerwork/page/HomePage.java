package com.academy.storedemoqa.ddt.framerwork.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final WebDriver driver;
    private final String baseUrl;
    @FindBy(css="#account > a")
    private WebElement myAccount;

    public HomePage(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        PageFactory.initElements(driver, this);
    }

    public HomePage goToHome(){
        driver.get(baseUrl);
        return this;
    }

    public AccountPage clickMyAccount(){
        myAccount.click();
        return new AccountPage(driver);
    }
}
