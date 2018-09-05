package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected void inputTextField(WebElement field, String value) {
        field.click();
        field.clear();
        field.sendKeys(value);
    }
}
