package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class DressesPage extends BasePage{
    private StringBuffer verificationErrors = new StringBuffer();
    @FindBy(xpath="//input[@id='layered_id_attribute_group_24']")
    private WebElement pink;

    @FindBy(xpath="//input[@id='layered_id_attribute_group_15']")
    private WebElement green;

    @FindBy(xpath="//input[@id='layered_id_attribute_group_13']")
    private WebElement orange;

    @FindBy(xpath="//input[@id='layered_id_attribute_group_8']")
    private WebElement white;

    @FindBy(xpath="//input[@id='layered_id_attribute_group_16']")
    private WebElement yellow;

    @FindBy(xpath="//input[@id='layered_id_attribute_group_14']")
    private WebElement blue;

    @FindBy(xpath="//input[@id='layered_id_attribute_group_11']")
    private WebElement black;

    @FindBy(xpath="//input[@id='layered_id_attribute_group_7']")
    private WebElement beige;


        public DressesPage(WebDriver driver) {
            super(driver);
        }

    public DressesPage clickPink() {
        pink.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public DressesPage clickGreen() {
        green.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public DressesPage clickOrange() {
        orange.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public DressesPage clickWhite() {
        white.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public DressesPage clickYellow() {
        yellow.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public DressesPage clickBlue() {
        blue.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public DressesPage clickBlack() {
        black.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public DressesPage clickBeige() {
        beige.click();
        waitForJSandJQueryToLoad();
        return new DressesPage(driver);
    }
    public void check () {

        try {
            assertEquals(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='--'])[1]/following::div[3]")).getText(), "Showing 1 - 8 of 3 items");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }


    }



}

