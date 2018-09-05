package com.academy.automationpractice.ddt.framework.helper;

import com.academy.automationpractice.ddt.framework.page.AccountPage;
import com.academy.automationpractice.ddt.framework.page.HomePage;
import com.academy.automationpractice.ddt.framework.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class SessionHelper {

    private WebDriver driver;
    private String login;
    private String password;

    public SessionHelper(WebDriver driver, String login, String password) {
        this.driver = driver;
        this.login = login;
        this.password = password;
    }

    public void login() {
        new HomePage(driver)
                .clickSignIn()
                .inputEmail(login)
                .inputPassword(password)
                .clickSignIn(false);
    }

    public void loginAs(String login, String password) {
        new HomePage(driver)
                .clickSignIn()
                .inputEmail(login)
                .inputPassword(password)
                .clickSignIn(false);
    }

    public String getErrMessage() {
        return new LoginPage(driver).getErrorMessage();
    }

    public void logout() {
        new AccountPage(driver).clickLogout();
    }
}
