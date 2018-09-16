package com.academy.mobile.ddt.tests.framework.ui.helper;

import org.openqa.selenium.WebDriver;

public class BaseUiHelper {
    protected boolean uiMode = false;
    protected WebDriver driver;

    public BaseUiHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void setUiMode(boolean uiMode) {
        this.uiMode = uiMode;
    }
}
