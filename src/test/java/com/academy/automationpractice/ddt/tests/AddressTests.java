package com.academy.automationpractice.ddt.tests;

import com.academy.automationpractice.ddt.framework.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class AddressTests extends BaseTest {

    @BeforeMethod
    public void prepare() {
        manager.goTo().home();
        manager.session().login();
        manager.goTo().address();
    }

    @Test(dataProvider = "creationAddress")
    public void testAddAddress( AddressData address) {
        System.out.println("start 'testAddAddress'");

        if (manager.address().isPresentAlias(address.getAlias())) {
            manager.address().remove(address.getAlias());
        }

        List<AddressData> before = manager.address().all();
        manager.address().create(address);

        // verify
        List<AddressData> after = manager.address().all();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add(address.withAddressAlias(address.getAlias().toUpperCase()));
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

        System.out.println("complete 'testAddAddress'");
    }

    @DataProvider(name="creationAddress")
    private Object[] getCreationAddressData() {
        return new Object[]{
                new AddressData()
                        .withFirstName("Kolya")
                        .withLastName("Ivanov")
                        .withAddress("Petrovskogo st. 35")
                        .withCity("Kharkov")
                        .withState("Alaska")
                        .withZipCode("61033")
                        .withCountry("United States")
                        .withHomePhone("+3809353613437")
                        .withMobilePhone("093234567")
                        .withAddressAlias("addressRef")
        };
    }
}
