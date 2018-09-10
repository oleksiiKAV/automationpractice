package com.academy.automationpractice.kdt;

import com.academy.automationpractice.ddt.tests.BaseTest;
import com.academy.automationpractice.kdt.engine.KeywordHomeIdentity;
import com.academy.automationpractice.kdt.engine.KeywordIdentity;
import com.academy.automationpractice.kdt.engine.KeywordLoginIdentity;
import com.academy.automationpractice.kdt.engine.KeywordMyAccountIdentity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    private KeywordIdentity baseKW;
    private KeywordHomeIdentity homeKW;
    private KeywordLoginIdentity loginKW;
    private KeywordMyAccountIdentity accountKW;

    @BeforeMethod
    public void setUpMethod() {
        baseKW = new KeywordIdentity(manager.getDriver());
        homeKW = new KeywordHomeIdentity(manager.getDriver());
        loginKW = new KeywordLoginIdentity(manager.getDriver());
        accountKW = new KeywordMyAccountIdentity(manager.getDriver());
    }

    @Test(groups={"kdt"}, dataProvider = "kdtProvider")
    public void testIncorrectLogin(String page, String action, String object, String value) {
        baseKW.doAction(page, action, object, value);
    }

    @DataProvider(name="kdtProvider")
    public Object[][] loginTestProvider() {
        return new Object[][] {
                {"", "GOTOURL", "", "http://automationpractice.com/index.php"},
                {"Home", "CLICK", "signInLink", ""},
                {"Login", "INPUT", "login", "oleg.kh81@gmail.com"},
                {"Login", "INPUT", "password", "123qwerty"},
                {"Login", "CLICK", "signInButton", ""},
                {"MyAccount", "ASSERT", "usernameLinkText", "Oleg Afanasiev"},
                {"MyAccount", "CLICK", "signOutButton", ""},
        };
    }
}
