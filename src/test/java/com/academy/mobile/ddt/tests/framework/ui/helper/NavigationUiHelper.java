package com.academy.mobile.ddt.tests.framework.ui.helper;

import com.academy.mobile.ddt.tests.framework.ui.page.HomePage;
import org.openqa.selenium.WebDriver;

public class NavigationUiHelper {
    private WebDriver driver;
    private String baseUrl;

    public NavigationUiHelper(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    public void home() {
        driver.get(baseUrl);
    }

    public void subscribers() {
        new HomePage(driver)
                .clickSubscribers();
    }

    // TODO
    public void reload() {
    }
}
