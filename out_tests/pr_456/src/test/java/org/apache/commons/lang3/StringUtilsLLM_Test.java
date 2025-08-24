package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    /**
     * Test for the toRootLowerCase method.
     */
    @Test
    public void testToRootLowerCase() {
        // Test null input
        assertNull(StringUtils.toRootLowerCase(null));

        // Test empty string
        assertEquals("", StringUtils.toRootLowerCase(""));

        // Test string with mixed case
        assertEquals("abc", StringUtils.toRootLowerCase("ABC"));
        assertEquals("abc", StringUtils.toRootLowerCase("aBc"));
        assertEquals("abc", StringUtils.toRootLowerCase("abc"));

        // Test string with special characters
        assertEquals("abc123!@#", StringUtils.toRootLowerCase("ABC123!@#"));
    }

    /**
     * Test for the toRootUpperCase method.
     */
    @Test
    public void testToRootUpperCase() {
        // Test null input
        assertNull(StringUtils.toRootUpperCase(null));

        // Test empty string
        assertEquals("", StringUtils.toRootUpperCase(""));

        // Test string with mixed case
        assertEquals("ABC", StringUtils.toRootUpperCase("abc"));
        assertEquals("ABC", StringUtils.toRootUpperCase("aBc"));
        assertEquals("ABC", StringUtils.toRootUpperCase("ABC"));

        // Test string with special characters
        assertEquals("ABC123!@#", StringUtils.toRootUpperCase("abc123!@#"));
    }
}