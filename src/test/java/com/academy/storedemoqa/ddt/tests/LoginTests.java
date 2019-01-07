package com.academy.storedemoqa.ddt.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @DataProvider
    public static Object[][] testLoginData() {
        return new Object[][]{
                {"OLEG AFANASIEV"}
        };
    }

    @Test(dataProvider = "testLoginData")
    public void TestLogin (String userNameExpected){
        manager.goTo().home();
        manager.session().login();

        manager.verify().userIsLoggedIn(userNameExpected);
        manager.session().logout();
    }

}
