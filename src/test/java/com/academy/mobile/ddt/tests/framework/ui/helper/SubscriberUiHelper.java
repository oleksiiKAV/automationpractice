package com.academy.mobile.ddt.tests.framework.ui.helper;

import com.academy.mobile.ddt.tests.framework.model.Entities;
import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import com.academy.mobile.ddt.tests.framework.ui.page.SubscriberPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.academy.automation.framework.util.MatcherAssertExt.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class SubscriberUiHelper {

    private WebDriver driver;

    public SubscriberUiHelper(WebDriver driver) {
        this.driver = driver;
    }

    public Entities<Subscriber> all() {
        SubscriberPage subscriberPage = new SubscriberPage(driver);
        List<Long> idList = subscriberPage.getIdList();
        List<String> firstNameList = subscriberPage.getFirstNameList();
        List<String> lastNameList = subscriberPage.getLastNameList();
        List<Gender> genderList = subscriberPage.getGenderList();
        List<Integer> ageList = subscriberPage.getAgeList();

        Entities<Subscriber> subscribers = new Entities<>();
        for (int i = 0; i < idList.size(); i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(idList.get(i));
            subscriber.setFirstName(firstNameList.get(i));
            subscriber.setLastName(lastNameList.get(i));
            subscriber.setGender(genderList.get(i));
            subscriber.setAge(ageList.get(i));

            subscribers.add(subscriber);
        }

        return subscribers;
    }

    public Entities<Subscriber> all(boolean uiMode) {
        return uiMode ? all() : null;
    }

    public void verifyEqualTo(Entities<Subscriber> expected) {
        assertThat(all(), equalTo(expected));
    }

    public void verifyEqualTo(Entities<Subscriber> expected, boolean uiMode) {
        if (uiMode)
            verifyEqualTo(expected);
    }

    public void verifyBySize(int actual, int expected) {
        assertThat(actual, equalTo(expected));
    }

    public void verifyBySize(int actual, int expected, boolean uiMode) {
        if (uiMode)
            verifyBySize(actual, expected);
    }

    public void deleteSubscriber(Subscriber subscriberDelete) {
        new SubscriberPage(driver)
               .clickId()
               .clickDelete();
    }

    public void deleteSubscriber(Subscriber subscriber, boolean uiMode) {
        if (uiMode)
            deleteSubscriber(subscriber);
    }
}
