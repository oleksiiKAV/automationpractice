package com.academy.mobile.ddt.tests.full;

import com.academy.mobile.ddt.tests.framework.model.Entities;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.academy.automation.framework.util.MatcherAssertExt.assertThat;
import static com.academy.mobile.ddt.tests.framework.model.Gender.MALE;
import static org.hamcrest.CoreMatchers.equalTo;

public class SubscriberUiTests extends BaseTest {

    // TODO fix
    @Test (dataProvider ="deleteProvider")
    public void testDeleteSubscriber(Subscriber subscriberDelete) {
        manager.rest().subscriber().createIfNotPresent(subscriberDelete);
        Entities<Subscriber> beforeRest = manager.rest().subscriber().all();
        manager.ui().goTo().home(ifUiMode());
        manager.ui().goTo().subscribers(ifUiMode());
        Entities<Subscriber> beforeUi = manager.ui().subscriber().all(ifUiMode());
        Entities<Subscriber> beforeBd = manager.bd().subscriber().all(ifBdMode());
        manager.ui().subscriber().deleteSubscriber(subscriberDelete, ifUiMode());
        Entities<Subscriber> afterRest = manager.rest().subscriber().all();
        Entities<Subscriber> afterUi = manager.ui().subscriber().all(ifUiMode());
        Entities<Subscriber> afterBd = manager.bd().subscriber().all();
        manager.ui().subscriber().verifyBySize(afterUi.size(), beforeUi.size()-1, ifUiMode());
        assertThat(beforeRest, equalTo(beforeBd));
        assertThat(beforeUi, equalTo(beforeBd));
        assertThat(afterBd, equalTo(afterRest));
        assertThat(afterRest, equalTo(afterUi));
    }
    @DataProvider
    private Object [][] deleteProvider(){
        return new Object[][]{
                {
                        Subscriber.newSubscriber()
                                .id(242)
                                .firstName("Dfcbkbq")
                                .lastName("Gtnhjd")
                                .age(25)
                                .gender(MALE)
                                .build(),
                }
        };

    }
}
