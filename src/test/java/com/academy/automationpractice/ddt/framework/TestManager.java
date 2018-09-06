package com.academy.automationpractice.ddt.framework;

import com.academy.automationpractice.ddt.framework.helper.*;
import com.academy.automationpractice.ddt.util.PropertyManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestManager {
    private static int DEFAULT_WAIT = 30;
    private WebDriver driver;

    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private AccountHelper accountHelper;
    private VerifyHelper verifyHelper;
    private AddressHelper addressHelper;
    private WomenHelper womenHelper;

    public void init(String browser) throws IOException {

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", PropertyManager.getProperty("chrome.driver"));
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", PropertyManager.getProperty("firefox.driver"));
                driver = new FirefoxDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        //        driver.manage().window().maximize();
        navigationHelper = new NavigationHelper(driver, PropertyManager.getProperty("automation.baseurl"));
        sessionHelper = new SessionHelper(driver, PropertyManager.getProperty("automation.username"), PropertyManager.getProperty("automation.password"));
        accountHelper = new AccountHelper(driver);
        addressHelper = new AddressHelper(driver);
        verifyHelper = new VerifyHelper(driver);
        womenHelper = new WomenHelper(driver);
    }

    public void stop() {
        driver.quit();
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper session() {
        return sessionHelper;
    }

    public AccountHelper account() {
        return accountHelper;
    }

    public VerifyHelper verify() {
        return verifyHelper;
    }

    public AddressHelper address() {
        return addressHelper;
    }

    public  WomenHelper women(){return  womenHelper;}
}
