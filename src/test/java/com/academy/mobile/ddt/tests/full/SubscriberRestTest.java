package com.academy.mobile.ddt.tests.full;

import com.academy.mobile.ddt.tests.framework.model.Entities;
import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.academy.automation.framework.util.MatcherAssertExt.assertThat;
import static com.academy.mobile.ddt.tests.framework.model.Gender.FEMALE;
import static com.academy.mobile.ddt.tests.framework.model.Gender.MALE;
import static org.hamcrest.CoreMatchers.equalTo;

public class SubscriberRestTest extends BaseTest {

    @Test(dataProvider = "subscriber")
    public void testDeleteSubscriber(Subscriber subscriber) {
        manager.rest().subscriber().createIfNotPresent(subscriber);

        Entities<Subscriber> before = manager.rest().subscriber().all();

        manager.ui().goTo().home(ifUiMode());
        manager.ui().goTo().subscribers(ifUiMode());
        manager.ui().subscriber().verifyEqualTo(before, ifUiMode());
        manager.bd().subscriber().verifyEqualTo(before, ifBdMode());

        manager.rest().subscriber().deleteIfPresent(subscriber);
        Entities<Subscriber> after  = manager.rest().subscriber().all();
        assertThat(after, equalTo(before.without(subscriber)));
        manager.ui().goTo().reload(ifUiMode());
        manager.ui().subscriber().verifyEqualTo(after, ifUiMode());
        manager.bd().subscriber().verifyEqualTo(after, ifBdMode());
    }

    @Test(dataProvider = "modificationProvider")
    public void testModificationSubscriber(Subscriber subscriberBefore, Subscriber subscriberAfter) {
        manager.rest().subscriber().createIfNotPresent(subscriberBefore);

        Entities<Subscriber> before = manager.rest().subscriber().all();

        manager.ui().goTo().home();
        manager.ui().goTo().subscribers(ifUiMode());
        manager.ui().subscriber().verifyEqualTo(before, ifUiMode());
        manager.bd().subscriber().verifyEqualTo(before, ifBdMode());

        manager.rest().subscriber().modify(subscriberBefore, subscriberAfter);

        Entities<Subscriber> after  = manager.rest().subscriber().all();
        assertThat(after, equalTo(before.withModified(subscriberBefore, subscriberAfter)));
        manager.ui().goTo().reload(ifUiMode());
        manager.ui().subscriber().verifyEqualTo(after, ifUiMode());
        manager.bd().subscriber().verifyEqualTo(after, ifBdMode());
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

    @DataProvider
    private Object[][] modificationProvider() {
        return new Object[][] {
                {
                        Subscriber.newSubscriber()
                                .id(100)
                                .firstName("Evgeniy")
                                .lastName("Onegin")
                                .age(25)
                                .gender(MALE)
                                .build(),

                        Subscriber.newSubscriber()
                                .id(100)
                                .firstName("Maria")
                                .lastName("Pechkina")
                                .age(28)
                                .gender(FEMALE)
                                .build(),
                }
        };
    }
}
