package com.academy.storedemoqa.ddt.tests;

import com.academy.storedemoqa.ddt.framerwork.page.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginTests {
    protected static final Logger LOG = LogManager.getLogger(LoginTests.class);
    private WebDriver driver;

    @BeforeClass
    public void init(){
        System.setProperty("webdriver.chrome.driver", "d:/distribs/selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        driver.manage().window().maximize();
    }

    @DataProvider
    public static Object[][] testLoginData() {
        return new Object[][]{
                {"oleg.kh81@gmail.com", "123qwerty", "OLEG AFANASIEV"}
        };
    }


    @Test(dataProvider = "testLoginData")
    public void TestLogin (String login, String password, String username){
        LOG.info("Start logintest {} {} {}", login, password, username);
        HomePage homePage = new HomePage(driver, "http://store.demoqa.com/");
        homePage.goToHome();
        homePage.clickMyAccount();
        driver.close();
    }

}
