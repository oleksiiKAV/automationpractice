package com.academy.automationpractice.ddt.framework;

import com.academy.automationpractice.ddt.framework.helper.*;
import com.academy.util.PropertyManager;
import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TestManager {
    protected static final Logger LOG = LogManager.getLogger(TestManager.class);
    protected static final Logger LOG_BROWSER = LogManager.getLogger("BROWSER");
    protected static final Logger LOG_PERFORMANCE = LogManager.getLogger("PERFORMANCE");
    protected static final Logger LOG_TRAFFIC = LogManager.getLogger("TRAFFIC");

    private static int DEFAULT_WAIT = 30;
    private static String AUTOMATION = "automation";
    private static String COMMON = "common";

    protected EventFiringWebDriver driver;
    private BrowserMobProxy proxy;

    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private AccountHelper accountHelper;
    private VerifyHelper verifyHelper;
    private AddressHelper addressHelper;
    private WomenHelper womenHelper;

    public void init(String browser) throws IOException {

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", PropertyManager.from(COMMON).getProperty("chrome.driver"));

                ChromeOptions options = new ChromeOptions();

                // performance
                if (Boolean.parseBoolean(PropertyManager.from(AUTOMATION).getProperty("log.performance"))) {
                    LoggingPreferences logPrefs = new LoggingPreferences();
                    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                    options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                }

                // proxy
                if (Boolean.parseBoolean(PropertyManager.from(AUTOMATION).getProperty("log.proxy"))) {
                    proxy = new BrowserMobProxyServer();
                    proxy.start(0);

                    // get the Selenium proxy object
                    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

                    // configure it as a desired capability
                    options.setCapability(CapabilityType.PROXY, seleniumProxy);
                    proxy.newHar("automation");
                }

                // start the browser up
                driver = new EventFiringWebDriver(new ChromeDriver(options));

                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", PropertyManager.from(COMMON).getProperty("firefox.driver"));
                driver = new EventFiringWebDriver(new FirefoxDriver());
                break;
        }

        driver.register(new DetailWebDriverEventListener());
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        //        driver.manage().window().maximize();
        navigationHelper = new NavigationHelper(driver, PropertyManager.from(AUTOMATION).getProperty("baseurl"));
        sessionHelper = new SessionHelper(driver, PropertyManager.from(AUTOMATION).getProperty("username"), PropertyManager.from(AUTOMATION).getProperty("password"));
        accountHelper = new AccountHelper(driver);
        addressHelper = new AddressHelper(driver);
        verifyHelper = new VerifyHelper(driver);
        womenHelper = new WomenHelper(driver);
    }

    public void stop() {
        if (Boolean.parseBoolean(PropertyManager.from(AUTOMATION).getProperty("log.proxy"))) {
            Har har = proxy.endHar();
            har.getLog().getEntries().forEach(l -> LOG_TRAFFIC.debug(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
        }
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

    public WebDriver getDriver() {
        return driver;
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

            if (Boolean.parseBoolean(PropertyManager.from(AUTOMATION).getProperty("log.browser"))) {
                LOG_BROWSER.debug("Navigated to {}", url);
                driver.manage().logs().get("browser").forEach(LOG_BROWSER::debug);
            }

            if (Boolean.parseBoolean(PropertyManager.from(AUTOMATION).getProperty("log.performance"))) {
                LOG_PERFORMANCE.debug("Navigated to {}", url);
                driver.manage().logs().get("performance").forEach(LOG_PERFORMANCE::debug);
            }
        }

        private void makeScreenshot() {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenName = "screen_" + System.currentTimeMillis()+".png";
            String screenPath = PropertyManager.from(AUTOMATION).getProperty("screenshots") + "/" + screenName;
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
