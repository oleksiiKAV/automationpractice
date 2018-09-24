package com.academy.automationpractice.ddt.framework.helper;

import com.academy.automationpractice.ddt.framework.page.HomePage;
import com.academy.automationpractice.ddt.framework.page.WomenPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class WomenHelper {
    private WebDriver driver;
    public WomenHelper (WebDriver driver) {
        this.driver = driver;
    }

    public void sortProductBy(String sortBy){
        new HomePage(driver)
                         .clickWomenPageLink()
                         .clickToDressesLink()
                         .clickToList()
                         .sortProductBy(sortBy);

    }
    public void verifySort (){
        WomenPage womenPage = new WomenPage(driver);
        List<String> actualPrises =
                womenPage
                        .listProduct();
        List<String> expectedPrices = new ArrayList<>(actualPrises);
        expectedPrices.sort(String::compareTo);
        Assert.assertEquals(actualPrises, expectedPrices);
    }

}
