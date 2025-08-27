package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testIsAnyBlank() {
        // Test cases for the modified isAnyBlank method
        assertFalse(StringUtils.isAnyBlank((CharSequence[]) null));
        assertFalse(StringUtils.isAnyBlank());
        assertFalse(StringUtils.isAnyBlank(""));
        assertTrue(StringUtils.isAnyBlank(" ", "foo"));
        assertTrue(StringUtils.isAnyBlank("foo", " "));
        assertTrue(StringUtils.isAnyBlank("foo", null));
        assertFalse(StringUtils.isAnyBlank("foo", "bar"));
    }

    @Test
    public void testIsAnyEmpty() {
        // Test cases for the modified isAnyEmpty method
        assertFalse(StringUtils.isAnyEmpty((CharSequence[]) null));
        assertFalse(StringUtils.isAnyEmpty());
        assertTrue(StringUtils.isAnyEmpty(""));
        assertTrue(StringUtils.isAnyEmpty("", "foo"));
        assertTrue(StringUtils.isAnyEmpty("foo", ""));
        assertTrue(StringUtils.isAnyEmpty("foo", null));
        assertFalse(StringUtils.isAnyEmpty("foo", "bar"));
    }
}