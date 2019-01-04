package com.academy.storedemoqa.ddt.framerwork.helper;

import com.academy.storedemoqa.ddt.framerwork.page.AccountPage;
import com.academy.storedemoqa.ddt.framerwork.page.HomePage;
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
        loginAs(login, password);
    }

    public void loginAs(String login, String password) {
        new HomePage(driver)
                .clickMyAccount()
                .inputLogin(login)
                .inputPassword(password)
                .clickLogin();
    }

    public String getErrMessage() {
        return new AccountPage(driver).getErrorMessage();
    }

    public void logout() {
        new AccountPage(driver).clickLogout();
    }
}
