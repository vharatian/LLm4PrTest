package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testUnwrap_StringString() {
        // Test cases for the updated unwrap method
        assertNull(StringUtils.unwrap(null, null));
        assertNull(StringUtils.unwrap(null, ""));
        assertNull(StringUtils.unwrap(null, "1"));
        assertEquals("abc", StringUtils.unwrap("abc", null));
        assertEquals("abc", StringUtils.unwrap("abc", ""));
        assertEquals("a", StringUtils.unwrap("a", "a"));
        assertEquals("ababa", StringUtils.unwrap("ababa", "aba"));
        assertEquals("", StringUtils.unwrap("aa", "a"));
        assertEquals("abc", StringUtils.unwrap("\'abc\'", "\'"));
        assertEquals("abc", StringUtils.unwrap("\"abc\"", "\""));
        assertEquals("abc\"xyz", StringUtils.unwrap("\"abc\"xyz\"", "\""));
        assertEquals("abc\"xyz\"", StringUtils.unwrap("\"abc\"xyz\"\"", "\""));
        assertEquals("abc\'xyz\'", StringUtils.unwrap("\"abc\'xyz\'\"", "\""));
        assertEquals("\"abc\'xyz\'\"", StringUtils.unwrap("AA\"abc\'xyz\'\"AA", "AA"));
        assertEquals("\"abc\'xyz\'\"", StringUtils.unwrap("123\"abc\'xyz\'\"123", "123"));
        assertEquals("AA\"abc\'xyz\'\"", StringUtils.unwrap("AA\"abc\'xyz\'\"", "AA"));
        assertEquals("AA\"abc\'xyz\'\"AA", StringUtils.unwrap("AAA\"abc\'xyz\'\"AAA", "A"));
        assertEquals("\"abc\'xyz\'\"AA", StringUtils.unwrap("\"abc\'xyz\'\"AA", "AA"));
    }
}