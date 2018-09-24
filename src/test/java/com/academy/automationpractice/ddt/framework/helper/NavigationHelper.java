package com.academy.automationpractice.ddt.framework.helper;

import com.academy.automationpractice.ddt.framework.page.AccountPage;
import com.academy.automationpractice.ddt.framework.page.HomePage;
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

    public void address() {
        new AccountPage(driver)
                .clickAddress();
    }
    public void womenPage (){
        new HomePage(driver)
                .clickWomenPageLink();
    }

    public void dresses(){
        new HomePage(driver)
                .clickDressesLink();
    }
}
