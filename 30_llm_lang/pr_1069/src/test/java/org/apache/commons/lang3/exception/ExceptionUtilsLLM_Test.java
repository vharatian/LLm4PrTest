package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionUtilsLLM_Test {

    @Test
    @DisplayName("isChecked returns true for checked exceptions")
    public void testIsChecked() {
        assertTrue(ExceptionUtils.isChecked(new Exception()));
        assertTrue(ExceptionUtils.isChecked(new IOException()));
        assertTrue(ExceptionUtils.isChecked(new ExceptionUtilsTest.TestThrowable()));
    }

    @Test
    @DisplayName("isChecked returns false for unchecked exceptions")
    public void testIsCheckedUnchecked() {
        assertFalse(ExceptionUtils.isChecked(new RuntimeException()));
        assertFalse(ExceptionUtils.isChecked(new IllegalArgumentException()));
        assertFalse(ExceptionUtils.isChecked(new NullPointerException()));
    }

    @Test
    @DisplayName("isChecked returns false for errors")
    public void testIsCheckedError() {
        assertFalse(ExceptionUtils.isChecked(new Error()));
        assertFalse(ExceptionUtils.isChecked(new OutOfMemoryError()));
    }

    @Test
    @DisplayName("isUnchecked returns true for unchecked exceptions")
    public void testIsUnchecked() {
        assertTrue(ExceptionUtils.isUnchecked(new RuntimeException()));
        assertTrue(ExceptionUtils.isUnchecked(new IllegalArgumentException()));
        assertTrue(ExceptionUtils.isUnchecked(new NullPointerException()));
    }

    @Test
    @DisplayName("isUnchecked returns false for checked exceptions")
    public void testIsUncheckedChecked() {
        assertFalse(ExceptionUtils.isUnchecked(new Exception()));
        assertFalse(ExceptionUtils.isUnchecked(new IOException()));
        assertFalse(ExceptionUtils.isUnchecked(new ExceptionUtilsTest.TestThrowable()));
    }

    @Test
    @DisplayName("isUnchecked returns true for errors")
    public void testIsUncheckedError() {
        assertTrue(ExceptionUtils.isUnchecked(new Error()));
        assertTrue(ExceptionUtils.isUnchecked(new OutOfMemoryError()));
    }
}