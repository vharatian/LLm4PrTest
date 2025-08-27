package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testJoin_ObjectArrayStringIntInt() {
        // Test for the change in join method signature
        assertEquals("foo,bar", StringUtils.join(new Object[]{"foo", "bar"}, ",", 0, 2));
        assertEquals("bar", StringUtils.join(new Object[]{"foo", "bar"}, ",", 1, 2));
        assertEquals("", StringUtils.join(new Object[]{"foo", "bar"}, ",", 2, 2));
        assertEquals("foo", StringUtils.join(new Object[]{"foo", "bar"}, ",", 0, 1));
        assertEquals("", StringUtils.join(new Object[]{"foo", "bar"}, ",", 0, 0));
        assertEquals("foo,bar,baz", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ",", 0, 3));
        assertEquals("bar,baz", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ",", 1, 3));
        assertEquals("baz", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ",", 2, 3));
    }

    @Test
    public void testMatches() {
        // Test for the change in matches method signature
        assertArrayEquals(new int[]{3, 0, 3, 6}, StringUtils.matches("abcdef", "abc"));
        assertArrayEquals(new int[]{3, 0, 3, 6}, StringUtils.matches("abc", "abcdef"));
        assertArrayEquals(new int[]{0, 0, 0, 0}, StringUtils.matches("", ""));
        assertArrayEquals(new int[]{0, 0, 0, 3}, StringUtils.matches("", "abc"));
        assertArrayEquals(new int[]{0, 0, 0, 3}, StringUtils.matches("abc", ""));
        assertArrayEquals(new int[]{1, 0, 1, 1}, StringUtils.matches("a", "a"));
        assertArrayEquals(new int[]{0, 0, 0, 1}, StringUtils.matches("a", "b"));
    }
}