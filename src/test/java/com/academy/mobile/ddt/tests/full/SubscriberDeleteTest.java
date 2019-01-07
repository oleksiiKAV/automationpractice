package com.academy.mobile.ddt.tests.full;

import com.academy.mobile.ddt.tests.framework.model.Entities;
import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.academy.automation.framework.util.MatcherAssertExt.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class SubscriberDeleteTest extends BaseTest {
    @Test(dataProvider = "subscriber")
    public void testDeleteSubscriber(Subscriber subscriber) {
        manager.rest().subscriber().createIfNotPresent(subscriber);

        Entities<Subscriber> before = manager.rest().subscriber().all();

        manager.ui().goTo().home();
        manager.ui().goTo().subscribers();
        manager.ui().subscriber().verifyEqualTo(before);
        manager.bd().subscriber().verifyEqualTo(before);

        manager.rest().subscriber().deleteIfPresent(subscriber);
        Entities<Subscriber> after  = manager.rest().subscriber().all();
        assertThat(after, equalTo(before.without(subscriber)));
        manager.ui().goTo().reload();
        manager.ui().subscriber().verifyEqualTo(after);
        manager.bd().subscriber().verifyEqualTo(after);
    }
    @DataProvider
    private Object[] subscriber() {
        return new Object[] {
                Subscriber.newSubscriber()
                        .id(20L)
                        .firstName("testName")
                        .lastName("lastLastName")
                        .age(15)
                        .gender(Gender.MALE)
                        .build()
        };
    }
}
