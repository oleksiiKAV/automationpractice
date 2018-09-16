package com.academy.mobile.ddt.tests.framework.ui.helper;

import com.academy.automationpractice.ddt.framework.page.BasePage;
import com.academy.mobile.ddt.tests.framework.ui.page.HomePage;
import org.openqa.selenium.WebDriver;

public class NavigationUiHelper extends BaseUiHelper {

    private String baseUrl;

    public NavigationUiHelper(WebDriver driver, String baseUrl) {
        super(driver);
        this.baseUrl = baseUrl;
    }

    public void home() {
        driver.get(baseUrl);
    }

    public void subscribers() {
        new HomePage(driver)
                .clickSubscribers();
    }

    public void reloadIfOn() {
        if (isOn) {
            driver.navigate().refresh();
            BasePage.waitForPageLoadComplete(driver, 10);
        }
    }
}
