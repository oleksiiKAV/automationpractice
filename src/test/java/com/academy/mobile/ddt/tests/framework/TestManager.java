package com.academy.mobile.ddt.tests.framework;

import com.academy.automation.framework.BaseBDManager;
import com.academy.automation.framework.BaseRestManager;
import com.academy.automation.framework.BaseUiManager;
import com.academy.automation.framework.Configuration;
import com.academy.automation.framework.util.PropertyManager;
import com.academy.mobile.ddt.tests.framework.bd.helper.SubscriberBdHelper;
import com.academy.mobile.ddt.tests.framework.rest.helper.SubscriberRestHelper;
import com.academy.mobile.ddt.tests.framework.ui.helper.NavigationUiHelper;
import com.academy.mobile.ddt.tests.framework.ui.helper.SubscriberUiHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TestManager  {
    protected static final Logger LOG = LogManager.getLogger(TestManager.class);

    private static final String COMMON = "common";
    private static final String MOBILE = "mobile";

    private UiManager uiManager = new UiManager();
    private RestManager restManager = new RestManager();
    private BdManager bdManager = new BdManager();

    public void init(String browser) throws IOException  {
        uiManager.init(browser);
        restManager.init();
        bdManager.init();
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

    public class UiManager extends BaseUiManager {
        private final static int IMPLICITLY_WAIT = 10;

        private NavigationUiHelper navigationHelper;
        private SubscriberUiHelper subscriberHelper;

        public UiManager() {
            super(new Configuration()
                    .withChromeDriverLocation(PropertyManager.from("common").getProperty("chrome.driver"))
                    .withGeckoDriverLocation(PropertyManager.from("common").getProperty("gecko.driver"))
                    .withBaseUrl(PropertyManager.from("mobile").getProperty("baseurl"))
                    .withImplicitlyWait(IMPLICITLY_WAIT)
                    .withScreenShotLocation(PropertyManager.from("mobile").getProperty("screenshots"))
                    .withUiMode(PropertyManager.from("mobile").getBoolean("ui.mode"))
                    .withBdMode(PropertyManager.from("mobile").getBoolean("bd.mode"))
                    .withRestMode(PropertyManager.from("mobile").getBoolean("rest.mode"))
            );
        }

        @Override
        protected void initHelpers() {
            navigationHelper = new NavigationUiHelper(driver, PropertyManager.from("mobile").getProperty("baseurl"));
            subscriberHelper = new SubscriberUiHelper(driver);
        }

        public NavigationUiHelper goTo() {
            return navigationHelper;
        }

        public SubscriberUiHelper subscriber() {
            return subscriberHelper;
        }
    }

    public class RestManager extends BaseRestManager {
        private SubscriberRestHelper subscriberHelper;

        public RestManager() {
            super(new Configuration()
                    .withRestMode(PropertyManager.from(MOBILE).getBoolean("rest.mode"))
                    .withBaseUrl(PropertyManager.from(MOBILE).getProperty("baseurl"))
                    .withRestPath(PropertyManager.from(MOBILE).getProperty("restpath"))
            );
        }

        @Override
        protected void initHelpers() {
            subscriberHelper = new SubscriberRestHelper()
            ;
        }

        public SubscriberRestHelper subscriber() {
            return subscriberHelper;
        }
    }

    public class BdManager extends BaseBDManager {

        private SubscriberBdHelper subscriberHelper;

        public BdManager() {
            super(new Configuration()
                    .withBdMode(PropertyManager.from(MOBILE).getBoolean("bd.mode"))
                    .withJdbcDriver(PropertyManager.from(MOBILE).getProperty("jdbc.driver"))
                    .withJdbcUrl(PropertyManager.from(MOBILE).getProperty("jdbc.url"))
            );
        }

        @Override
        protected void initHelpers() {
            subscriberHelper = new SubscriberBdHelper(jdbcUrl())
                    .withBdMode(isBdMode());
        }

        public SubscriberBdHelper subscriber() {
            return subscriberHelper;
        }
    }
}
