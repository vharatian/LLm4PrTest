package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testJoin_byteArrayWithSeparator() {
        assertNull(StringUtils.join((byte[]) null, ','));
        assertEquals("1;2", StringUtils.join(new byte[]{1, 2}, ';'));
        assertEquals("2", StringUtils.join(new byte[]{1, 2}, ';', 1, 2));
        assertEquals("", StringUtils.join(new byte[]{1, 2}, ';', 0, 0));
        assertEquals("", StringUtils.join(new byte[]{1, 2}, ';', 1, 0));
    }

    @Test
    public void testJoin_charArrayWithSeparator() {
        assertNull(StringUtils.join((char[]) null, ','));
        assertEquals("1;2", StringUtils.join(new char[]{'1', '2'}, ';'));
        assertEquals("2", StringUtils.join(new char[]{'1', '2'}, ';', 1, 2));
        assertEquals("", StringUtils.join(new char[]{'1', '2'}, ';', 0, 0));
        assertEquals("", StringUtils.join(new char[]{'1', '2'}, ';', 1, 0));
    }

    @Test
    public void testJoin_doubleArrayWithSeparator() {
        assertNull(StringUtils.join((double[]) null, ','));
        assertEquals("1.0;2.0", StringUtils.join(new double[]{1.0, 2.0}, ';'));
        assertEquals("2.0", StringUtils.join(new double[]{1.0, 2.0}, ';', 1, 2));
        assertEquals("", StringUtils.join(new double[]{1.0, 2.0}, ';', 0, 0));
        assertEquals("", StringUtils.join(new double[]{1.0, 2.0}, ';', 1, 0));
    }

    @Test
    public void testJoin_floatArrayWithSeparator() {
        assertNull(StringUtils.join((float[]) null, ','));
        assertEquals("1.0;2.0", StringUtils.join(new float[]{1.0f, 2.0f}, ';'));
        assertEquals("2.0", StringUtils.join(new float[]{1.0f, 2.0f}, ';', 1, 2));
        assertEquals("", StringUtils.join(new float[]{1.0f, 2.0f}, ';', 0, 0));
        assertEquals("", StringUtils.join(new float[]{1.0f, 2.0f}, ';', 1, 0));
    }

    @Test
    public void testJoin_intArrayWithSeparator() {
        assertNull(StringUtils.join((int[]) null, ','));
        assertEquals("1;2", StringUtils.join(new int[]{1, 2}, ';'));
        assertEquals("2", StringUtils.join(new int[]{1, 2}, ';', 1, 2));
        assertEquals("", StringUtils.join(new int[]{1, 2}, ';', 0, 0));
        assertEquals("", StringUtils.join(new int[]{1, 2}, ';', 1, 0));
    }

    @Test
    public void testJoin_longArrayWithSeparator() {
        assertNull(StringUtils.join((long[]) null, ','));
        assertEquals("1;2", StringUtils.join(new long[]{1L, 2L}, ';'));
        assertEquals("2", StringUtils.join(new long[]{1L, 2L}, ';', 1, 2));
        assertEquals("", StringUtils.join(new long[]{1L, 2L}, ';', 0, 0));
        assertEquals("", StringUtils.join(new long[]{1L, 2L}, ';', 1, 0));
    }

    @Test
    public void testJoin_ObjectArrayWithSeparator() {
        assertNull(StringUtils.join((Object[]) null, ','));
        assertEquals("foo;bar;baz", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ';'));
        assertEquals("bar;baz", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ';', 1, 3));
        assertEquals("", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ';', 0, 0));
        assertEquals("", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ';', 1, 1));
    }

    @Test
    public void testJoin_ObjectArrayWithStringSeparator() {
        assertNull(StringUtils.join((Object[]) null, ","));
        assertEquals("foo,bar,baz", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ","));
        assertEquals("bar,baz", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ",", 1, 3));
        assertEquals("", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ",", 0, 0));
        assertEquals("", StringUtils.join(new Object[]{"foo", "bar", "baz"}, ",", 1, 1));
    }

    @Test
    public void testJoin_shortArrayWithSeparator() {
        assertNull(StringUtils.join((short[]) null, ','));
        assertEquals("1;2", StringUtils.join(new short[]{1, 2}, ';'));
        assertEquals("2", StringUtils.join(new short[]{1, 2}, ';', 1, 2));
        assertEquals("", StringUtils.join(new short[]{1, 2}, ';', 0, 0));
        assertEquals("", StringUtils.join(new short[]{1, 2}, ';', 1, 0));
    }
}