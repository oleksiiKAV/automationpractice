package com.academy.mobile.ddt.tests.framework;

import com.academy.mobile.ddt.tests.framework.helper.NavigationHelper;
import com.academy.util.PropertyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestManager {
    protected static final Logger LOG = LogManager.getLogger(TestManager.class);
    private static final String COMMON = "common";
    private static final String MOBILE = "mobile";

    private static int DEFAULT_WAIT = 10;
    protected WebDriver driver;

    private NavigationHelper navigationHelper;

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
        navigationHelper = new NavigationHelper(driver, PropertyManager.from(MOBILE).getProperty("baseurl"));
    }

    public void stop() {
        driver.quit();
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }
}
