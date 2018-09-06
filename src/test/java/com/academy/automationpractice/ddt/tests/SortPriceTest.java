package com.academy.automationpractice.ddt.tests;


import com.academy.automationpractice.ddt.util.PropertyManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SortPriceTest extends BaseTest {

    @Test(dataProvider = "sortByLower")
    public void sortPriceToLowerFirstTest(String sortBy) throws Exception{
        manager.women().sortByLowerPrice(sortBy);

    }

    @DataProvider(name="sortByLower")
    private Object[]sortBy(){
        return new Object[]
                { "Price: Lowest first"};
    }

}