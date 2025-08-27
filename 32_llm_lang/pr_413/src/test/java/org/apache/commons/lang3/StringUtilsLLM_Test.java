package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testWrapIfMissing_StringChar() {
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
        assertEquals("/", StringUtils.wrapIfMissing("/", '/'));
        assertEquals("/a/b/c/", StringUtils.wrapIfMissing("a/b/c", '/'));
        assertEquals("/a/b/c/", StringUtils.wrapIfMissing("/a/b/c", '/'));
        assertEquals("/a/b/c/", StringUtils.wrapIfMissing("a/b/c/", '/'));
    }

    @Test
    public void testWrapIfMissing_StringString() {
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
        assertEquals("/", StringUtils.wrapIfMissing("/", "/"));
        assertEquals("/a/b/c/", StringUtils.wrapIfMissing("a/b/c", "/"));
        assertEquals("/a/b/c/", StringUtils.wrapIfMissing("/a/b/c", "/"));
        assertEquals("/a/b/c/", StringUtils.wrapIfMissing("a/b/c/", "/"));
    }
}