package com.academy.automationpractice.ddt.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;

public class MatcherAssertExt {

    public static Logger log = LogManager.getLogger(MatcherAssertExt.class);

    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        try {
            org.hamcrest.MatcherAssert.assertThat("", actual, matcher);
        } catch (AssertionError e) {
            log.error(e.getMessage());
            throw new AssertionError(e);
        }
    }

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        try {
            org.hamcrest.MatcherAssert.assertThat(reason, actual, matcher);
        } catch (AssertionError e) {
            log.error(e.getMessage());
            throw new AssertionError(e);
        }
    }

    public static void assertThat(String reason, boolean assertion) {
        try {
            org.hamcrest.MatcherAssert.assertThat(reason, assertion);
        } catch (AssertionError e) {
            log.error(e.getMessage());
            throw new AssertionError(e);
        }
    }
}
