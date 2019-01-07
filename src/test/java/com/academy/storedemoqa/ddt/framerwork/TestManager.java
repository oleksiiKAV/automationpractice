package com.academy.storedemoqa.ddt.framerwork;

import com.academy.automation.framework.util.PropertyManager;
import com.academy.storedemoqa.ddt.framerwork.helper.NavigationHelper;
import com.academy.storedemoqa.ddt.framerwork.helper.SessionHelper;
import com.academy.storedemoqa.ddt.framerwork.helper.VerifyHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestManager {

    protected static final Logger LOG = LogManager.getLogger(com.academy.automationpractice.ddt.framework.TestManager.class);
    protected static final Logger LOG_BROWSER = LogManager.getLogger("BROWSER");
    protected static final Logger LOG_PERFORMANCE = LogManager.getLogger("PERFORMANCE");
    protected static final Logger LOG_TRAFFIC = LogManager.getLogger("TRAFFIC");

    private static int DEFAULT_WAIT = 30;
    private static String STOREDEMOQA = "storedemoqa";
    private static String COMMON = "common";

    protected WebDriver driver;

    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private VerifyHelper verifyHelper;

    public void init(String browser) throws IOException {

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", PropertyManager.from(COMMON).getProperty("chrome.driver"));

                // start the browser up
                driver = new ChromeDriver();

                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", PropertyManager.from(COMMON).getProperty("firefox.driver"));
                driver = new FirefoxDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        //        driver.manage().window().maximize();
        navigationHelper = new NavigationHelper(driver, PropertyManager.from(STOREDEMOQA).getProperty("baseurl"));
        sessionHelper = new SessionHelper(driver, PropertyManager.from(STOREDEMOQA).getProperty("username"), PropertyManager.from(STOREDEMOQA).getProperty("password"));
        verifyHelper = new VerifyHelper(driver);
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

    public VerifyHelper verify() {
        return verifyHelper;
    }



    public WebDriver getDriver() {
        return driver;
    }
}
