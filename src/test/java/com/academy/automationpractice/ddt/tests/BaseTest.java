package com.academy.automationpractice.ddt.tests;

import com.academy.automation.framework.test.BaseInitializer;
import com.academy.automationpractice.ddt.framework.TestManager;

public class BaseTest extends BaseInitializer {
    protected final TestManager manager = new TestManager();

    public BaseTest() {
        registerManager(manager);
    }
}
