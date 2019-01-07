package com.academy.mobile.ddt.tests.framework.ui.page;

import com.academy.automation.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "body > div > div:nth-child(2) > div > a")
    private WebElement subscribersLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SubscriberPage clickSubscribers() {
        subscribersLink.click();
        return new SubscriberPage(driver);
    }
    

}
