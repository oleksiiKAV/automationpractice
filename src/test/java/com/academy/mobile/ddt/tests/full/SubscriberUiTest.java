package com.academy.mobile.ddt.tests.full;
import com.academy.mobile.ddt.tests.framework.TestManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubscriberUiTest extends BaseTest {
    @Test()
    public void testAddSubscriber() {
        manager.ui().goTo().home();
        manager.ui().goTo().subscribers();
        manager.ui().click().add();


    }

   /* @DataProvider(name = "creationAddress")
    public Object[][] provideData() {
        return new Object[][]{
                {"yellow", "Showing 1 - 3 of 3 items"},
                {"orange", "Showing 1 - 2 of 2 items"}
        };
    }*/
}