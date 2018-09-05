package com.academy.automationpractice.ddt.tests;

import com.academy.automationpractice.ddt.framework.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

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

        if (manager.address().isPresentAlias(addressData.getAlias())) {
            manager.address().removeAddress(addressData.getAlias());
        }

        List<AddressData> beforeListAddr = manager.address().getAddresses();

        manager.address().create(addressData);

        // verify
        List<AddressData> afterListAddr = manager.address().getAddresses();
        Assert.assertEquals(afterListAddr.size(), beforeListAddr.size()+1);
        beforeListAddr.add(addressData.withAddressAlias(addressData.getAlias().toUpperCase()));
        Assert.assertEquals(new HashSet<>(beforeListAddr), new HashSet<>(afterListAddr));

        System.out.println("complete 'testAddAddress'");
    }
}
