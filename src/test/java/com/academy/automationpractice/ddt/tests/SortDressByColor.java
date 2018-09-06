package com.academy.automationpractice.ddt.tests;

import org.testng.annotations.Test;

public class SortDressByColor extends BaseTest {


    @Test()
    public void testSortDressByColor() {
        System.out.println("start testSortDressByColor");
        manager.goTo().home();
        manager.goTo().dress();
        manager.mark().color();
        manager.verifyty().choice();



        //manager.click().sortByColor();
        System.out.println("complete testSortDressByColor");

    }
}
