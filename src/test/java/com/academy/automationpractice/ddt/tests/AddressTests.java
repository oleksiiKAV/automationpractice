package com.academy.automationpractice.ddt.tests;

import com.academy.automationpractice.ddt.framework.model.AddressData;
import org.testng.annotations.Test;

public class AddressTests extends BaseTest {

    @Test
    // TODO
    public void testAddAddress() {
        System.out.println("start 'testAddAddress'");

        AddressData addressData = new AddressData()
                .withFirstName("Kolya")
                .withLastName("Ivanov")
                .withAddress("Petrovskogo st. 35")
                .withCity("Kharkov")
                .withState("Alaska")
                .withZipCode("61033")
                .withCountry("United States")
                .withHomePhone("+3809353613437")
                .withMobilePhone("093234567")
                .withAddressAlias("addressAddedRef");

        manager.goTo().home();
        manager.session().login();
        manager.goTo().address();

        System.out.println("complete 'testAddAddress'");
    }
}
