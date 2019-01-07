package com.academy.storedemoqa.ddt.framerwork;

import com.academy.automation.framework.BaseUiManager;
import com.academy.automation.framework.Configuration;
import com.academy.automation.framework.util.PropertyManager;
import com.academy.storedemoqa.ddt.framerwork.helper.NavigationHelper;
import com.academy.storedemoqa.ddt.framerwork.helper.SessionHelper;
import com.academy.storedemoqa.ddt.framerwork.helper.VerifyHelper;

public class TestManager extends BaseUiManager {
    private static int IMPLICITLY_WAIT = 30;

    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private VerifyHelper verifyHelper;

    public TestManager() {
        super(new Configuration()
                .withChromeDriverLocation(PropertyManager.from("common").getProperty("chrome.driver"))
                .withGeckoDriverLocation(PropertyManager.from("common").getProperty("gecko.driver"))
                .withBaseUrl(PropertyManager.from("storedemoqa").getProperty("baseurl"))
                .withImplicitlyWait(IMPLICITLY_WAIT)
                .withLogin(PropertyManager.from("storedemoqa").getProperty("username"))
                .withPassword(PropertyManager.from("storedemoqa").getProperty("password"))
                .withScreenShotLocation(PropertyManager.from("storedemoqa").getProperty("screenshots"))
                .withLogBrowser(false)
                .withLogHttp(false)
                .withLogPerformance(false));
    }

    @Override
    protected void initHelpers() {
        navigationHelper = new NavigationHelper(driver, cfg.getBaseUrl());
        sessionHelper = new SessionHelper(driver, cfg.getLogin(), cfg.getPassword());
        verifyHelper = new VerifyHelper(driver);
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
}
