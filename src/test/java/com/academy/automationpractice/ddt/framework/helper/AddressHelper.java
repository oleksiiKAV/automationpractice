package com.academy.automationpractice.ddt.framework.helper;

import com.academy.automationpractice.ddt.framework.model.AddressData;
import com.academy.automationpractice.ddt.framework.page.AddressPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class AddressHelper {

    private WebDriver driver;

    public AddressHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPresentAlias(String alias) {
        return new AddressPage(driver)
                .getAliases()
                .contains(alias.toUpperCase());
    }

    public void remove(String alias) {
        new AddressPage(driver)
                .clickDeleteButton(alias)
                .acceptDeletion();
    }

    public List<AddressData> all() {

        List<AddressData> addressList  = new ArrayList<>();

        List<String> firstNames = new AddressPage(driver).getFirstNameList();
        List<String> lastNames = new AddressPage(driver).getLastNameList();
        List<String> addresses = new AddressPage(driver).getAddressList();
        List<String> cities = new AddressPage(driver).getCityList();
        List<String> states = new AddressPage(driver).getStateList();
        List<String> zipCodes = new AddressPage(driver).getZipCodeList();
        List<String> countries = new AddressPage(driver).getCountryList();
        List<String> homePhones = new AddressPage(driver).getHomePhoneList();
        List<String> mobilePhones = new AddressPage(driver).getMobilePhoneList();
        List<String> addressAliases = new AddressPage(driver).getAddressAliasList();

        for (int i = 0; i < addressAliases.size(); i++) {
            addressList.add(new AddressData()
                    .withFirstName(firstNames.get(i))
                    .withLastName(lastNames.get(i))
                    .withAddress(addresses.get(i))
                    .withCity(cities.get(i).substring(0, cities.get(i).length()-1))
                    .withState(states.get(i))
                    .withZipCode(zipCodes.get(i))
                    .withCountry(countries.get(i))
                    .withHomePhone(homePhones.get(i))
                    .withMobilePhone(mobilePhones.get(i))
                    .withAddressAlias(addressAliases.get(i))
            );
        }

        return addressList;
    }

    public void create(AddressData address) {
        initCreation();
        fillForm(address);
        submit();
    }

    private AddressPage submit() {
        return new FormAddressPage(driver)
                .clickSaveButton();
    }

    private FormAddressPage fillForm(AddressData address) {
        return new FormAddressPage(driver)
                .inputFirstName(address.getFirstName())
                .inputsLastName(address.getLastName())
                .inputAddress(address.getAddress())
                .inputCity(address.getCity())
                .inputState(address.getState())
                .inputZipCode(address.getZipCode())
                .inputCountry(address.getCountry())
                .inputHomePhone(address.getHomePhone())
                .inputMobilePhone(address.getMobilePhone())
                .inputAddressAlias(address.getAlias());
    }

    private void initCreation() {
        new AddressPage(driver)
                .clickByAddNewAddress();
    }
}
