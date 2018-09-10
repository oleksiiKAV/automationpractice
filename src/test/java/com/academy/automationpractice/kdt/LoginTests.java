package com.academy.automationpractice.kdt;

import com.academy.automationpractice.ddt.tests.BaseTest;
import com.academy.automationpractice.kdt.engine.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginTests extends BaseTest {
    private KeywordIdentity baseKW;
    private KeywordHomeIdentity homeKW;
    private KeywordLoginIdentity loginKW;
    private KeywordAccountIdentity accountKW;

    @BeforeMethod
    public void setUpMethod() {
        baseKW = new KeywordIdentity(manager.getDriver());
        homeKW = new KeywordHomeIdentity(manager.getDriver());
        loginKW = new KeywordLoginIdentity(manager.getDriver());
        accountKW = new KeywordAccountIdentity(manager.getDriver());
    }

    @Test(groups={"kdt"}, dataProvider = "kdtProvider")
    public void testIncorrectLogin(List<KeywordStep> steps) {
        for (KeywordStep step : steps) {
            LOG.info("page: {}, action: {}, obj: {}, value: {}",
                    step.getPage(), step.getAction(), step.getObject(), step.getValue());

            baseKW.doAction(step.getPage(), step.getAction(), step.getObject(), step.getValue());
        }
    }

    @DataProvider(name="kdtProvider")
    public Object[][] loginTestProvider() {

        List<KeywordStep> steps = new ArrayList<>();

        // TODO read from excel
        steps.add(new KeywordStep()
                .withPage("")
                .withAction("GOTOURL")
                .withObject("")
                .withValue("http://automationpractice.com/index.php"));

        steps.add(new KeywordStep()
                .withPage("Home")
                .withAction("CLICK")
                .withObject("signInLink")
                .withValue(""));

        steps.add(new KeywordStep()
                .withPage("Login")
                .withAction("INPUT")
                .withObject("login")
                .withValue("oleg.kh81@gmail.com"));

        steps.add(new KeywordStep()
                .withPage("Login")
                .withAction("INPUT")
                .withObject("password")
                .withValue("123qwerty"));

        steps.add(new KeywordStep()
                .withPage("Login")
                .withAction("CLICK")
                .withObject("signInButton")
                .withValue(""));

        steps.add(new KeywordStep()
                .withPage("account")
                .withAction("ASSERT")
                .withObject("usernameLinkText")
                .withValue("Oleg Afanasiev"));

        steps.add(new KeywordStep()
                .withPage("account")
                .withAction("CLICK")
                .withObject("signOutButton")
                .withValue(""));

        return new Object[][] {
                {steps},
        };
    }
}
