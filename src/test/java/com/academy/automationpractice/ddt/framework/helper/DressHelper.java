package com.academy.automationpractice.ddt.framework.helper;

import com.academy.automationpractice.ddt.framework.page.DressesPage;
import org.openqa.selenium.WebDriver;


public class DressHelper {
    private WebDriver driver;
    private String color;
    public DressHelper (WebDriver driver) {
        this.driver = driver;
    }
    public void color(String color) {
        System.out.println("Select Color");
        new DressesPage(driver);
        switch (color) {
            case "pink":
                new DressesPage(driver).clickPink();
                break;
            case "green":
                new DressesPage(driver).clickGreen();
                break;
            case "orange":
                new DressesPage(driver).clickOrange();
                break;
            case "white":
                new DressesPage(driver).clickWhite();
                break;
            case "yellow":
                new DressesPage(driver).clickYellow();
                break;
            case "blue":
                new DressesPage(driver).clickBlue();
                break;
            case "black":
                new DressesPage(driver).clickBlack();
                break;
            case "beige":
                new DressesPage(driver).clickBeige();
                break;
            default:
                System.out.println("Incorrect Color");
                break;
        }
        System.out.println("Complete Color");
    }
    public void choice(String check) {
        new DressesPage(driver)
                .check(check);
    }



    }

