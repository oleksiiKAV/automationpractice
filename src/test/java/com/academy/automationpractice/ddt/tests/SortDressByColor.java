package com.academy.automationpractice.ddt.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SortDressByColor extends BaseTest {


    @Test(dataProvider = "creationAddress")
    public void testSortDressByColor(String color, String check) {
        System.out.println("start testSortDressByColor");
        manager.goTo().home();
        manager.goTo().dress();
        manager.mark().color(color);
        manager.verifyty().choice(check);



        //manager.click().sortByColor();
        System.out.println("complete testSortDressByColor");

    }
    @DataProvider(name="creationAddress")
        public Object[][] provideData() {
            return new Object[][]{
                    {"yellow", "Showing 1 - 3 of 3 items"},
                    {"orange", "Showing 1 - 2 of 2 items"}
            };
    }
}
