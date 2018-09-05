package com.academy.automationpractice.ddt.tests;

import com.academy.automationpractice.ddt.framework.TestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
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
}
