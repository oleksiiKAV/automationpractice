package com.academy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

import java.lang.reflect.Field;

public class MatcherAssertExt {

    public static Logger LOG = LogManager.getLogger(MatcherAssertExt.class);

    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        try {
            IsEqual isEqualInstanceFromMatcher = (IsEqual) matcher;
            Field field = isEqualInstanceFromMatcher.getClass().getDeclaredField("expectedValue");
            field.setAccessible(true);
            Object expected = field.get(isEqualInstanceFromMatcher);
            LOG.info("assert that '{}' equal to '{}'", actual, expected);
            org.hamcrest.MatcherAssert.assertThat("", actual, matcher);
        } catch (AssertionError e) {
            LOG.error(e.getMessage());
            throw new AssertionError(e);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOG.warn("Assertion log error. Couldn't get field from matcher. Details: {}", e);
        }
    }

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        try {
            org.hamcrest.MatcherAssert.assertThat(reason, actual, matcher);
        } catch (AssertionError e) {
            LOG.error(e.getMessage());
            throw new AssertionError(e);
        }
    }

    public static <T> org.hamcrest.Matcher<T> equalTo(T operand) {
        return org.hamcrest.core.IsEqual.<T>equalTo(operand);
    }

    public static void assertThat(String reason, boolean assertion) {
        try {
            org.hamcrest.MatcherAssert.assertThat(reason, assertion);
        } catch (AssertionError e) {
            LOG.error(e.getMessage());
            throw new AssertionError(e);
        }
    }
}
