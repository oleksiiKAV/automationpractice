package com.academy.storedemoqa.ddt.tests;

import com.academy.storedemoqa.ddt.framerwork.TestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class BaseTest {
    protected static final Logger LOG = LogManager.getLogger(BaseTest.class);
    protected final TestManager manager = new TestManager();

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome")String browser) throws Exception {
        manager.init(browser);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        manager.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] params) {
        LOG.info("Start test {} with parameters {}",
                method.getName(), Arrays.toString(params));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        LOG.info("Stop test {}", method.getName());
    }
}
