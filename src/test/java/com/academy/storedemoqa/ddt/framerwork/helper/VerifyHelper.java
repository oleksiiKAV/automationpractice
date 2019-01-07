package com.academy.storedemoqa.ddt.framerwork.helper;

import com.academy.storedemoqa.ddt.framerwork.page.AccountPage;
import org.openqa.selenium.WebDriver;

import static com.academy.automation.framework.util.MatcherAssertExt.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class VerifyHelper {
    private WebDriver driver;

    public VerifyHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void userIsLoggedIn(String userNameExpected) {
        String userNameActual = new AccountPage(driver).getUserNameCapture();
        assertThat(userNameActual, equalTo(userNameExpected));
    }
}
