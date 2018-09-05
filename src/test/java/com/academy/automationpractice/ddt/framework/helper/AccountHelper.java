package com.academy.automationpractice.ddt.framework.helper;

import com.academy.automationpractice.ddt.framework.page.AccountPage;
import org.openqa.selenium.WebDriver;

public class AccountHelper {

    private WebDriver driver;

    public AccountHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return new AccountPage(driver).getUserNameCapture();
    }
}
