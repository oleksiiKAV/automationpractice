package com.academy.mobile.ddt.tests.full;

import com.academy.automation.framework.test.BaseTestInitializer;
import com.academy.mobile.ddt.tests.framework.TestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest extends BaseTestInitializer {
    protected static final Logger LOG = LogManager.getLogger(BaseTestInitializer.class);
    protected final TestManager manager = new TestManager();

    public BaseTest() {
        registerUIManager(manager.ui());
        registerBDManager(manager.bd());
        registerRestManager(manager.rest());
    }
}
