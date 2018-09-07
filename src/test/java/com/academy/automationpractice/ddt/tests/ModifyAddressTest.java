package com.academy.automationpractice.ddt.tests;

import com.academy.automationpractice.ddt.framework.model.AddressData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ModifyAddressTest extends BaseTest {

     @BeforeMethod
    public void prepare() {
        manager.goTo().home();
        manager.session().login();
        manager.goTo().address();
    }

    @Test(dataProvider = "modificationAddress")
    public void testModifyAddress(AddressData address) {
        System.out.println("start 'testModifyAddress'");

        manager.address().modify(address);

        //verify
       Assert.assertEquals("Kristin", address.getFirstName());
       Assert.assertEquals("McGuire", address.getLastName());
       Assert.assertEquals("590 Griffin Street", address.getAddress());
       Assert.assertEquals("Phoenix", address.getCity());
       Assert.assertEquals("Arizona", address.getState());
       Assert.assertEquals("85012", address.getZipCode());
       Assert.assertEquals("United States", address.getCountry());
       Assert.assertEquals("602-287-1132", address.getHomePhone());
       Assert.assertEquals("555-287-1777", address.getMobilePhone());
       Assert.assertEquals("ModifiedAddress", address.getAlias());

        System.out.println("complete 'testModifyAddress'");

    }

    @DataProvider(name="modificationAddress")
    private Object[] getModifiedAddressData() {
        return new Object[]{
                new AddressData()
                        .withFirstName("Kristin")
                        .withLastName("McGuire")
                        .withAddress("590 Griffin Street")
                        .withCity("Phoenix")
                        .withState("Arizona")
                        .withZipCode("85012")
                        .withCountry("United States")
                        .withHomePhone("602-287-1132")
                        .withMobilePhone("555-287-1777")
                        .withAddressAlias("ModifiedAddress")
        };
    }
}
