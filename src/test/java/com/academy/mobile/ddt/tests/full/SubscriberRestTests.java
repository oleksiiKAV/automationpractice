package com.academy.mobile.ddt.tests.full;

import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.academy.mobile.ddt.tests.framework.model.Gender.FEMALE;
import static com.academy.mobile.ddt.tests.framework.model.Gender.MALE;

public class SubscriberRestTests extends BaseTest {

    @Test(dataProvider = "modificationProvider")
    public void testModificationSubscriber(Subscriber subscriber, String newFirstName, String newLastName, int newAge, Gender newGender) {

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
