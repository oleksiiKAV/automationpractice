package com.academy.mobile.ddt.tests.framework;

import com.academy.mobile.ddt.tests.framework.bd.helper.SubscriberBdHelper;
import com.academy.mobile.ddt.tests.framework.rest.helper.SubscriberRestHelper;
import com.academy.mobile.ddt.tests.framework.ui.helper.NavigationUiHelper;
import com.academy.mobile.ddt.tests.framework.ui.helper.SubscriberUiHelper;
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

    private UiManager uiManager = new UiManager();
    private RestManager restManager = new RestManager();
    private BdManager bdManager = new BdManager();

    public void init(String browser) throws IOException  {
        uiManager.init(browser);
    }

    public void stop() {
        uiManager.stop();
    }

    public UiManager ui() {
        return uiManager;
    }

    public RestManager rest() {
        return restManager;
    }

    public BdManager bd() {
        return bdManager;
    }

    public class UiManager {
        private final int DEFAULT_WAIT = 10;
        protected WebDriver driver;

        private NavigationUiHelper navigationHelper;
        private SubscriberUiHelper subscriberHelper;

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
            navigationHelper = new NavigationUiHelper(driver, PropertyManager.from(MOBILE).getProperty("baseurl"));
        }

        public void stop() {
            driver.quit();
        }

        public NavigationUiHelper goTo() {
            return navigationHelper;
        }

        public SubscriberUiHelper subscriber() {
            return subscriberHelper;
        }
    }

    public class RestManager {
        private SubscriberRestHelper subscriberHelper;

        public SubscriberRestHelper subscriber() {
            return subscriberHelper;
        }
    }

    public class BdManager {
        private SubscriberBdHelper subscriberHelper;

        public SubscriberBdHelper subscriber() {
            return subscriberHelper;
        }
    }
}
