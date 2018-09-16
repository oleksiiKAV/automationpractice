package com.academy.mobile.ddt.tests.framework.ui.helper;

import org.openqa.selenium.WebDriver;

public class BaseUiHelper {
    protected boolean isOn = false;
    protected WebDriver driver;

    public BaseUiHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }
}
