package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testIsAlphanumericSpace() {
        // Test cases for isAlphanumericSpace
        assertTrue(StringUtils.isAlphanumericSpace("abc123"));
        assertTrue(StringUtils.isAlphanumericSpace("abc 123"));
        assertFalse(StringUtils.isAlphanumericSpace("abc-123"));
        assertFalse(StringUtils.isAlphanumericSpace("abc_123"));
        assertFalse(StringUtils.isAlphanumericSpace("abc@123"));
    }

    @Test
    public void testIsAlphaSpace() {
        // Test cases for isAlphaSpace
        assertTrue(StringUtils.isAlphaSpace("abc"));
        assertTrue(StringUtils.isAlphaSpace("abc def"));
        assertFalse(StringUtils.isAlphaSpace("abc123"));
        assertFalse(StringUtils.isAlphaSpace("abc-def"));
        assertFalse(StringUtils.isAlphaSpace("abc_def"));
    }

    @Test
    public void testIsNumericSpace() {
        // Test cases for isNumericSpace
        assertTrue(StringUtils.isNumericSpace("123"));
        assertTrue(StringUtils.isNumericSpace("123 456"));
        assertFalse(StringUtils.isNumericSpace("123a"));
        assertFalse(StringUtils.isNumericSpace("123-456"));
        assertFalse(StringUtils.isNumericSpace("123_456"));
    }
}