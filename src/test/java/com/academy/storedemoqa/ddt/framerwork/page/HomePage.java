package com.academy.storedemoqa.ddt.framerwork.page;

import com.academy.automation.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css="#account > a")
    private WebElement myAccount;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public AccountPage clickMyAccount(){
        myAccount.click();
        return new AccountPage(driver);
    }
}
