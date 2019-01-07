package com.academy.storedemoqa.ddt.tests;

import com.academy.automation.framework.test.BaseTestInitializer;
import com.academy.storedemoqa.ddt.framerwork.TestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest extends BaseTestInitializer {
    protected static final Logger LOG = LogManager.getLogger(BaseTest.class);
    protected final TestManager manager = new TestManager();

    public BaseTest() {
        registerUIManager(manager);
    }
}
