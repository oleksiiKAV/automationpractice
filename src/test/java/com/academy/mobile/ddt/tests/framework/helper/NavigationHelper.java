package com.academy.mobile.ddt.tests.framework.helper;

import com.academy.automationpractice.ddt.framework.page.AccountPage;
import com.academy.mobile.ddt.tests.framework.page.HomePage;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver driver;
    private String baseUrl;

    public NavigationHelper(WebDriver driver, String baseUrl) {
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

}
