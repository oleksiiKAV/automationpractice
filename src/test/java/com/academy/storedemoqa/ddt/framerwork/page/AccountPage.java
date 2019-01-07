package com.academy.storedemoqa.ddt.framerwork.page;

import com.academy.automation.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePage {

    private WebElement loginLink;
    private WebElement logoutLink;
    private WebElement errDiv;
    private WebElement userNameLink;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage inputLogin(String login) {
        return this;
    }

    public AccountPage inputPassword(String password) {
        return this;
    }

    public void clickLogin() {
        loginLink.click();
    }

    public void clickLogout() {
        logoutLink.click();
    }

    public String getErrorMessage() {
        return errDiv.getText();
    }

    public String getUserNameCapture() {
        return userNameLink.getText();
    }
}
