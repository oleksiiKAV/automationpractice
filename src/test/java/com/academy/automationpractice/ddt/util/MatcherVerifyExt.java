package com.academy.automationpractice.ddt.util;

import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;

public class MatcherVerifyExt {
    public static Logger log;

    public static <T> void verifyThat(T actual, Matcher<? super T> matcher) {
        try {
            org.hamcrest.MatcherAssert.assertThat("", actual, matcher);
        } catch (AssertionError e) {
            log.error(e.getMessage());
        }
    }

    // TODO
    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        try {
            org.hamcrest.MatcherAssert.assertThat(reason, actual, matcher);
        } catch (AssertionError e) {
            log.error(e.getMessage());
        }
    }

    public static void assertThat(String reason, boolean assertion) {
        try {
            org.hamcrest.MatcherAssert.assertThat(reason, assertion);
        } catch (AssertionError e) {
            log.error(e.getMessage());
        }
    }
}
