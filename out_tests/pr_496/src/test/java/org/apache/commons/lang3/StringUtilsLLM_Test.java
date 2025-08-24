package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testWrapIfMissingChar() {
        // Test cases for the wrapIfMissing method with char parameter
        assertNull(StringUtils.wrapIfMissing(null, CharUtils.NUL));
        assertNull(StringUtils.wrapIfMissing(null, '1'));
        assertEquals("", StringUtils.wrapIfMissing("", CharUtils.NUL));
        assertEquals("xabx", StringUtils.wrapIfMissing("ab", 'x'));
        assertEquals("\"ab\"", StringUtils.wrapIfMissing("ab", '\"'));
        assertEquals("\"ab\"", StringUtils.wrapIfMissing("\"ab\"", '\"'));
        assertEquals("'ab'", StringUtils.wrapIfMissing("ab", '\''));
        assertEquals("'abcd'", StringUtils.wrapIfMissing("'abcd'", '\''));
        assertEquals("'\"abcd\"'", StringUtils.wrapIfMissing("\"abcd\"", '\''));
        assertEquals("\"'abcd'\"", StringUtils.wrapIfMissing("'abcd'", '\"'));
        assertEquals("/x/", StringUtils.wrapIfMissing("x", '/'));
        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z", '/'));
        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("/x/y/z", '/'));
        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z/", '/'));
        assertEquals("/", StringUtils.wrapIfMissing("/", '/'));
    }

    @Test
    public void testWrapIfMissingString() {
        // Test cases for the wrapIfMissing method with String parameter
        assertNull(StringUtils.wrapIfMissing(null, "\0"));
        assertNull(StringUtils.wrapIfMissing(null, "1"));
        assertEquals("", StringUtils.wrapIfMissing("", "\0"));
        assertEquals("xabx", StringUtils.wrapIfMissing("ab", "x"));
        assertEquals("\"ab\"", StringUtils.wrapIfMissing("ab", "\""));
        assertEquals("\"ab\"", StringUtils.wrapIfMissing("\"ab\"", "\""));
        assertEquals("'ab'", StringUtils.wrapIfMissing("ab", "\'"));
        assertEquals("'abcd'", StringUtils.wrapIfMissing("'abcd'", "\'"));
        assertEquals("'\"abcd\"'", StringUtils.wrapIfMissing("\"abcd\"", "\'"));
        assertEquals("\"'abcd'\"", StringUtils.wrapIfMissing("'abcd'", "\""));
        assertEquals("/x/", StringUtils.wrapIfMissing("x", "/"));
        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z", "/"));
        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("/x/y/z", "/"));
        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z/", "/"));
        assertEquals("/", StringUtils.wrapIfMissing("/", "/"));
        assertEquals("ab/ab", StringUtils.wrapIfMissing("/", "ab"));
        assertEquals("ab/ab", StringUtils.wrapIfMissing("ab/ab", "ab"));
    }
}