package com.academy.automationpractice.ddt.framework.helper;

import com.academy.automationpractice.ddt.framework.page.DressesPage;
import org.openqa.selenium.WebDriver;


public class DressHelper {
    private WebDriver driver;
    private String color;
    public DressHelper (WebDriver driver) {
        this.driver = driver;
    }
    public void color() {
        System.out.println("Select Color");
        new DressesPage(driver)
                .clickBeige()
                .clickBlack()
                .clickGreen();
        System.out.println("Complete Color");
    }
    public void choice() {
        new DressesPage(driver)
                .check();
    }



    }

