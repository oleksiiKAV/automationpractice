package com.academy.mobile.ddt.tests.framework.ui.helper;

import com.academy.automation.framework.page.BasePage;
import com.academy.mobile.ddt.tests.framework.ui.page.HomePage;
import org.openqa.selenium.WebDriver;

public class NavigationUiHelper {

    private WebDriver driver;
    private String baseUrl;

    public NavigationUiHelper(WebDriver driver, String baseUrl) {
        this.baseUrl = baseUrl;
        this.driver = driver;
    }

    public void home() {
        driver.get(baseUrl);
    }


    public void home(boolean uiMode) {
        if (uiMode)
            home();
    }

    public void subscribers() {
        new HomePage(driver)
                .clickSubscribers();
    }

    public void subscribers(boolean uiMode) {
        if (uiMode)
            subscribers();
    }

    public void reload() {
        driver.navigate().refresh();
        BasePage.waitForPageLoadComplete(driver, 10);
    }

    public void reload(boolean uiMode) {
        if (uiMode)
            reload();
    }
}
