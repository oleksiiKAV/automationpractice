package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    @FindBy(linkText = "Sign out")
    private WebElement signOutLink;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Sign out'])[1]/preceding::span[1]")
    private WebElement userLink;

    @FindBy(css="#header > div.nav > div > div > nav > div:nth-child(1) > a > span")
    private WebElement userNameLink;

    @FindBy(css="#header > div.nav > div > div > nav > div:nth-child(2) > a")
    private WebElement logoutLink;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserNameLinkText() {
        return userLink.getText().trim();
    }

    public HomePage clickSignOut() {
        signOutLink.click();
        return new HomePage(driver);
    }

    public String getUserNameCapture() {
        return userNameLink.getText().trim();
    }

    public HomePage clickLogout() {
        logoutLink.click();
        return new HomePage(driver);
    }
}
