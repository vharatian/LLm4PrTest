package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testJoinBooleanArrayWithDelimiter() {
        assertNull(StringUtils.join((boolean[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new boolean[]{}, ';', 0, 1));
        assertEquals("true;false", StringUtils.join(new boolean[]{true, false}, ';', 0, 2));
        assertEquals("true", StringUtils.join(new boolean[]{true, false}, ';', 0, 1));
        assertEquals("false", StringUtils.join(new boolean[]{true, false}, ';', 1, 2));
    }

    @Test
    public void testJoinByteArrayWithDelimiter() {
        assertNull(StringUtils.join((byte[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new byte[]{}, ';', 0, 1));
        assertEquals("1;2", StringUtils.join(new byte[]{1, 2}, ';', 0, 2));
        assertEquals("1", StringUtils.join(new byte[]{1, 2}, ';', 0, 1));
        assertEquals("2", StringUtils.join(new byte[]{1, 2}, ';', 1, 2));
    }

    @Test
    public void testJoinCharArrayWithDelimiter() {
        assertNull(StringUtils.join((char[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new char[]{}, ';', 0, 1));
        assertEquals("a;b", StringUtils.join(new char[]{'a', 'b'}, ';', 0, 2));
        assertEquals("a", StringUtils.join(new char[]{'a', 'b'}, ';', 0, 1));
        assertEquals("b", StringUtils.join(new char[]{'a', 'b'}, ';', 1, 2));
    }

    @Test
    public void testJoinDoubleArrayWithDelimiter() {
        assertNull(StringUtils.join((double[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new double[]{}, ';', 0, 1));
        assertEquals("1.0;2.0", StringUtils.join(new double[]{1.0, 2.0}, ';', 0, 2));
        assertEquals("1.0", StringUtils.join(new double[]{1.0, 2.0}, ';', 0, 1));
        assertEquals("2.0", StringUtils.join(new double[]{1.0, 2.0}, ';', 1, 2));
    }

    @Test
    public void testJoinFloatArrayWithDelimiter() {
        assertNull(StringUtils.join((float[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new float[]{}, ';', 0, 1));
        assertEquals("1.0;2.0", StringUtils.join(new float[]{1.0f, 2.0f}, ';', 0, 2));
        assertEquals("1.0", StringUtils.join(new float[]{1.0f, 2.0f}, ';', 0, 1));
        assertEquals("2.0", StringUtils.join(new float[]{1.0f, 2.0f}, ';', 1, 2));
    }

    @Test
    public void testJoinIntArrayWithDelimiter() {
        assertNull(StringUtils.join((int[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new int[]{}, ';', 0, 1));
        assertEquals("1;2", StringUtils.join(new int[]{1, 2}, ';', 0, 2));
        assertEquals("1", StringUtils.join(new int[]{1, 2}, ';', 0, 1));
        assertEquals("2", StringUtils.join(new int[]{1, 2}, ';', 1, 2));
    }

    @Test
    public void testJoinLongArrayWithDelimiter() {
        assertNull(StringUtils.join((long[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new long[]{}, ';', 0, 1));
        assertEquals("1;2", StringUtils.join(new long[]{1L, 2L}, ';', 0, 2));
        assertEquals("1", StringUtils.join(new long[]{1L, 2L}, ';', 0, 1));
        assertEquals("2", StringUtils.join(new long[]{1L, 2L}, ';', 1, 2));
    }

    @Test
    public void testJoinShortArrayWithDelimiter() {
        assertNull(StringUtils.join((short[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new short[]{}, ';', 0, 1));
        assertEquals("1;2", StringUtils.join(new short[]{1, 2}, ';', 0, 2));
        assertEquals("1", StringUtils.join(new short[]{1, 2}, ';', 0, 1));
        assertEquals("2", StringUtils.join(new short[]{1, 2}, ';', 1, 2));
    }

    @Test
    public void testJoinObjectArrayWithDelimiter() {
        assertNull(StringUtils.join((Object[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new Object[]{}, ';', 0, 1));
        assertEquals("a;b", StringUtils.join(new Object[]{"a", "b"}, ';', 0, 2));
        assertEquals("a", StringUtils.join(new Object[]{"a", "b"}, ';', 0, 1));
        assertEquals("b", StringUtils.join(new Object[]{"a", "b"}, ';', 1, 2));
    }
}