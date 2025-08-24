package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testEquals() {
        // Test case-sensitive comparison
        assertTrue(StringUtils.equals("abc", "abc"));
        assertFalse(StringUtils.equals("abc", "ABC"));
        assertFalse(StringUtils.equals("abc", null));
        assertFalse(StringUtils.equals(null, "abc"));
        assertTrue(StringUtils.equals(null, null));
        assertTrue(StringUtils.equals("", ""));
        assertFalse(StringUtils.equals("abc", "abcd"));
    }

    @Test
    public void testEqualsIgnoreCase() {
        // Test case-insensitive comparison
        assertTrue(StringUtils.equalsIgnoreCase("abc", "abc"));
        assertTrue(StringUtils.equalsIgnoreCase("abc", "ABC"));
        assertFalse(StringUtils.equalsIgnoreCase("abc", null));
        assertFalse(StringUtils.equalsIgnoreCase(null, "abc"));
        assertTrue(StringUtils.equalsIgnoreCase(null, null));
        assertTrue(StringUtils.equalsIgnoreCase("", ""));
        assertFalse(StringUtils.equalsIgnoreCase("abc", "abcd"));
    }
}