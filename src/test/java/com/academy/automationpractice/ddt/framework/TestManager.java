package com.academy.automationpractice.ddt.framework;

import com.academy.automationpractice.ddt.framework.helper.*;
import com.academy.automationpractice.ddt.util.PropertyManager;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TestManager {
    protected static final Logger LOG = LogManager.getLogger(TestManager.class);

    private static int DEFAULT_WAIT = 30;
    protected EventFiringWebDriver driver;

    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private AccountHelper accountHelper;
    private VerifyHelper verifyHelper;
    private AddressHelper addressHelper;

    public void init(String browser) throws IOException {

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", PropertyManager.getProperty("chrome.driver"));
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                driver = new EventFiringWebDriver(new ChromeDriver(options));
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", PropertyManager.getProperty("firefox.driver"));
                driver = new EventFiringWebDriver(new FirefoxDriver());
                break;
        }

        driver.register(new DetailWebDriverEventListener());
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        //        driver.manage().window().maximize();
        navigationHelper = new NavigationHelper(driver, PropertyManager.getProperty("automation.baseurl"));
        sessionHelper = new SessionHelper(driver, PropertyManager.getProperty("automation.username"), PropertyManager.getProperty("automation.password"));
        accountHelper = new AccountHelper(driver);
        addressHelper = new AddressHelper(driver);
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

    public AccountHelper account() {
        return accountHelper;
    }

    public VerifyHelper verify() {
        return verifyHelper;
    }

    public AddressHelper address() {
        return addressHelper;
    }

    class DetailWebDriverEventListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            LOG.debug("Try find by {}", by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            LOG.debug("Found by {}", by);
        }

        @Override
        public void onException(Throwable err, WebDriver driver) {
            LOG.error("Error occurs: {}", err);

            makeScreenshot();
        }

        @Override
        public void afterNavigateTo(String url, WebDriver driver) {
            LOG.debug("Navigated to {}", url);
            driver.manage().logs().get("browser").forEach(LOG::debug);
            driver.manage().logs().get("performance").forEach(LOG::debug);
        }

        private void makeScreenshot() {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenName = "screen_" + System.currentTimeMillis()+".png";
            String screenPath = PropertyManager.getProperty("screenshots") + "/" + screenName;
            File screen = new File(screenPath);
            try {
                Files.copy(tmp, screen);
            } catch (IOException exc) {
                LOG.error("Error copying screenshot from '{}' to '{}'. Details: {}",
                        tmp, screen, exc);
            }
        }
    }
}
