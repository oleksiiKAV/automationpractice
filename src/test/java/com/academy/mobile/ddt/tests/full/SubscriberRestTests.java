package com.academy.mobile.ddt.tests.full;

import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.academy.mobile.ddt.tests.framework.model.Gender.FEMALE;
import static com.academy.mobile.ddt.tests.framework.model.Gender.MALE;
import static com.academy.util.MatcherAssertExt.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class SubscriberRestTests extends BaseTest {

    @BeforeMethod
    public void prepare() {
        manager.ui().goTo().home();
        manager.ui().goTo().subscribers();
    }

    // TODO
    @Test(dataProvider = "modificationProvider")
    public void testModificationSubscriber(Subscriber subscriber, Subscriber modifiedSubscriber) {
//        if (!manager.rest().subscriber().isPresent(subscriber))
//            manager.rest().susbcriber().create(subscriber);
//
//        Subscribers uiBefore = manager.ui().subscriber().all();
//        Subscribers dbBefore = manager.ui().subscriber().all();
//        Subscribers restBefore = manager.ui().subscriber().all();
//
//        manager.rest().subscriber().modify(subscriber, modifiedSubscriber);
//
//        Subscribers uiAfter = manager.ui().subscriber().all();
//        Subscribers dbAfter = manager.ui().subscriber().all();
//        Subscribers restAfter = manager.ui().subscriber().all();
//
//        assertThat(restAfter, equalTo(restBefore.withModified(modifiedSubscriber);
    }

    @DataProvider
    private Object[][] modificationProvider() {
        return new Object[][] {
                {
                        Subscriber.newSubscriber()
                                .id(100)
                                .firstName("OldFirstName")
                                .lastName("OldLastName")
                                .age(25)
                                .gender(MALE)
                                .build(),
                        "ModifiedFirstName",
                        "ModifiedLastName",
                        26,
                        FEMALE
                }
        };
    }
}
