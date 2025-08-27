package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExceptionUtilsLLM_Test {

    @Test
    public void testIsUnchecked_null() {
        // Test for null input
        assertFalse(ExceptionUtils.isUnchecked(null));
    }

    @Test
    public void testIsUnchecked_checked() {
        // Test for checked exception
        assertFalse(ExceptionUtils.isUnchecked(new IOException()));
    }

    @Test
    public void testIsUnchecked_error() {
        // Test for Error type
        assertTrue(ExceptionUtils.isUnchecked(new StackOverflowError()));
    }

    @Test
    public void testIsUnchecked_unchecked() {
        // Test for RuntimeException type
        assertTrue(ExceptionUtils.isUnchecked(new IllegalArgumentException()));
    }

    @Test
    public void testIsUnchecked_customThrowable() {
        // Test for custom Throwable type
        assertFalse(ExceptionUtils.isUnchecked(new ExceptionUtilsTest.TestThrowable()));
    }
}