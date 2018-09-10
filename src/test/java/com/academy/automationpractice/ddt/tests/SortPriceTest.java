package com.academy.automationpractice.ddt.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class SortPriceTest extends BaseTest {

    @Test(dataProvider = "sortByLower")
    public void sortPriceToLowerFirstTest(String sortBy) throws Exception{
        manager.goTo().home();
        manager.goTo().womenPage();
        manager.women().sortByPrice(sortBy);
        manager.women().verifySort(sortBy);

    }

    @DataProvider(name="sortByLower")
    private Object[]sortBy(){
        return new Object[]
                { "Price: Lowest first"};
    }

}