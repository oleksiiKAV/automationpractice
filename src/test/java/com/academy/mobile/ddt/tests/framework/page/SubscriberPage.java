package com.academy.mobile.ddt.tests.framework.page;

import com.academy.automationpractice.ddt.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscriberPage extends BasePage {

    @FindBy(xpath="//input[@id='add']")
    private WebElement add;

    public SubscriberPage(WebDriver driver) {
        super(driver);
    }

    public void AddSubscriberPage add() {
        new HomePage(driver)
                .clickSubscribers();

    }
}
