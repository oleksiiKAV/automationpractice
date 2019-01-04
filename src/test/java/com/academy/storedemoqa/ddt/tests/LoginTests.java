package com.academy.storedemoqa.ddt.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @DataProvider
    public static Object[][] testLoginData() {
        return new Object[][]{
                {"oleg.kh81@gmail.com", "123qwerty", "OLEG AFANASIEV"}
        };
    }


    @Test(dataProvider = "testLoginData")
    public void TestLogin (String login, String password, String userNameExpected){
//        LOG.info("Start logintest {} {} {}", login, password, userNameExpected);
//        HomePage homePage = new HomePage(driver, "http://store.demoqa.com/");
//        homePage.goToHome();
//        homePage.clickMyAccount();
//        driver.close();

        manager.goTo().home();
        manager.session().login();

        manager.verify().userIsLoggedIn(userNameExpected);
        manager.session().logout();
    }

}
