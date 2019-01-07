package com.academy.automationpractice.ddt.framework.page;

import com.academy.automation.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormAddressPage extends BasePage {

    @FindBy(id="firstname")
    private WebElement firstNameInput;

    @FindBy(id="lastname")
    private WebElement lastNameInput;

    @FindBy(id="address1")
    private WebElement addressInput;

    @FindBy(id="city")
    private WebElement cityInput;

    @FindBy(id="id_state")
    private WebElement stateSelect;

    @FindBy(id="postcode")
    private WebElement zipCodeInput;

    @FindBy(id="id_country")
    private WebElement countrySelect;

    @FindBy(id="phone")
    private WebElement homePhoneInput;

    @FindBy(id="phone_mobile")
    private WebElement mobilePhoneInput;

    @FindBy(id="submitAddress")
    private WebElement saveButton;

    @FindBy(id="alias")
    private WebElement addressAlias;

    public FormAddressPage(WebDriver driver) {
        super(driver);
    }

    public FormAddressPage inputFirstName(String firstName) {
        inputTextField(firstNameInput, firstName);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputsLastName(String lastName) {
        inputTextField(lastNameInput, lastName);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputAddress(String address) {
        inputTextField(addressInput, address);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputCity(String city) {
        inputTextField(cityInput, city);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputState(String state) {
        selectByText(stateSelect, state);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputZipCode(String zipCode) {
        inputTextField(zipCodeInput, zipCode);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputCountry(String country) {
        selectByText(countrySelect, country);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputHomePhone(String homePhone) {
        inputTextField(homePhoneInput, homePhone);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputMobilePhone(String mobilePhone) {
        inputTextField(mobilePhoneInput, mobilePhone);
        return new FormAddressPage(driver);
    }

    public FormAddressPage inputAddressAlias(String alias) {
        inputTextField(addressAlias, alias);
        return new FormAddressPage(driver);
    }

    public AddressPage clickSaveButton() {
        saveButton.click();
        return new AddressPage(driver);
    }
}
