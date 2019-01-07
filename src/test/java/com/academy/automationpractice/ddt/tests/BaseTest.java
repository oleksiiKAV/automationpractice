package com.academy.automationpractice.ddt.tests;

import com.academy.automation.framework.test.BaseTestInitializer;
import com.academy.automationpractice.ddt.framework.TestManager;

public class BaseTest extends BaseTestInitializer {
    protected final TestManager manager = new TestManager();

    public BaseTest() {
        registerUIManager(manager);
    }
}
