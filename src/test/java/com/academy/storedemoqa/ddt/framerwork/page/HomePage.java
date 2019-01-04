package com.academy.storedemoqa.ddt.framerwork.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final WebDriver driver;

    @FindBy(css="#account > a")
    private WebElement myAccount;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AccountPage clickMyAccount(){
        myAccount.click();
        return new AccountPage(driver);
    }
}
