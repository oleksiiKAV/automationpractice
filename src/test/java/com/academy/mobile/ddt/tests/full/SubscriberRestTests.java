package com.academy.mobile.ddt.tests.full;

import com.academy.mobile.ddt.tests.framework.model.Entities;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.academy.mobile.ddt.tests.framework.model.Gender.FEMALE;
import static com.academy.mobile.ddt.tests.framework.model.Gender.MALE;
import static com.academy.util.MatcherAssertExt.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class SubscriberRestTests extends BaseTest {

    @Test(dataProvider = "modificationProvider")
    public void testModificationSubscriber(Subscriber subscriberBefore, Subscriber subscriberAfter) {
        manager.rest().subscriber().createIfNotPresent(subscriberBefore);

        Entities<Subscriber> before = manager.rest().subscriber().all();

        manager.ui().goTo().home();
        manager.ui().goTo().subscribers();
        manager.ui().subscriber().verifyEqualTo(before);
        manager.bd().subscriber().verifyEqualTo(before);

        manager.rest().subscriber().modify(subscriberBefore, subscriberAfter);

        Entities<Subscriber> after  = manager.rest().subscriber().all();
        assertThat(after, equalTo(before.withModified(subscriberBefore, subscriberAfter)));
        manager.ui().goTo().reload();
        manager.ui().subscriber().verifyEqualTo(after);
        manager.bd().subscriber().verifyEqualTo(after);
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
