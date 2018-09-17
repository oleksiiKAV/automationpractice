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
        if (!uiMode)
            return;

        driver.get(baseUrl);
    }

    public void subscribers() {
        if (!uiMode)
            return;

        new HomePage(driver)
                .clickSubscribers();
    }

    public void reload() {
        if (!uiMode)
            return;

        driver.navigate().refresh();
        BasePage.waitForPageLoadComplete(driver, 10);

    }
}
