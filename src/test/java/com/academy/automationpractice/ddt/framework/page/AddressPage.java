package com.academy.automationpractice.ddt.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class AddressPage extends BasePage {

    @FindBy(css="#center_column > div.addresses ul > li:nth-child(1) > h3")
    private List<WebElement> addressAliasElements;

    private String deleteAddressByAliasXPath = "//div[@id='center_column']/div[@class='addresses']//ul[li/h3[text()='%s']]//li[last()]/a[2]";

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>(addressAliasElements.size());
        for(WebElement el : addressAliasElements)
            aliases.add(el.getText().trim());

        return aliases;
    }

    public AddressPage clickDeleteButton(String alias) {
        findDeleteButtonByAlias(alias).click();
        return new AddressPage(driver);
    }

    public AddressPage acceptDeletion() {
        driver.switchTo().alert().accept();
        return new AddressPage(driver);
    }

    private WebElement findDeleteButtonByAlias(String alias) {
        return driver.findElement(By.xpath(String.format(deleteAddressByAliasXPath, alias.trim())));
    }
}
