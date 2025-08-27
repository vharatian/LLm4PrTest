package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testAddFirstBoolean() {
        assertArrayEquals(new boolean[]{true}, ArrayUtils.addFirst(null, true));
        assertArrayEquals(new boolean[]{false, true}, ArrayUtils.addFirst(new boolean[]{true}, false));
        assertArrayEquals(new boolean[]{true, true, false}, ArrayUtils.addFirst(new boolean[]{true, false}, true));
    }

    @Test
    public void testAddFirstByte() {
        assertArrayEquals(new byte[]{1}, ArrayUtils.addFirst(null, (byte) 1));
        assertArrayEquals(new byte[]{0, 1}, ArrayUtils.addFirst(new byte[]{1}, (byte) 0));
        assertArrayEquals(new byte[]{1, 1, 0}, ArrayUtils.addFirst(new byte[]{1, 0}, (byte) 1));
    }

    @Test
    public void testAddFirstChar() {
        assertArrayEquals(new char[]{'1'}, ArrayUtils.addFirst(null, '1'));
        assertArrayEquals(new char[]{'0', '1'}, ArrayUtils.addFirst(new char[]{'1'}, '0'));
        assertArrayEquals(new char[]{'1', '1', '0'}, ArrayUtils.addFirst(new char[]{'1', '0'}, '1'));
    }

    @Test
    public void testAddFirstDouble() {
        assertArrayEquals(new double[]{1.0}, ArrayUtils.addFirst(null, 1.0));
        assertArrayEquals(new double[]{0.0, 1.0}, ArrayUtils.addFirst(new double[]{1.0}, 0.0));
        assertArrayEquals(new double[]{1.0, 1.0, 0.0}, ArrayUtils.addFirst(new double[]{1.0, 0.0}, 1.0));
    }

    @Test
    public void testAddFirstFloat() {
        assertArrayEquals(new float[]{1.0f}, ArrayUtils.addFirst(null, 1.0f));
        assertArrayEquals(new float[]{0.0f, 1.0f}, ArrayUtils.addFirst(new float[]{1.0f}, 0.0f));
        assertArrayEquals(new float[]{1.0f, 1.0f, 0.0f}, ArrayUtils.addFirst(new float[]{1.0f, 0.0f}, 1.0f));
    }

    @Test
    public void testAddFirstInt() {
        assertArrayEquals(new int[]{1}, ArrayUtils.addFirst(null, 1));
        assertArrayEquals(new int[]{0, 1}, ArrayUtils.addFirst(new int[]{1}, 0));
        assertArrayEquals(new int[]{1, 1, 0}, ArrayUtils.addFirst(new int[]{1, 0}, 1));
    }

    @Test
    public void testAddFirstLong() {
        assertArrayEquals(new long[]{1L}, ArrayUtils.addFirst(null, 1L));
        assertArrayEquals(new long[]{0L, 1L}, ArrayUtils.addFirst(new long[]{1L}, 0L));
        assertArrayEquals(new long[]{1L, 1L, 0L}, ArrayUtils.addFirst(new long[]{1L, 0L}, 1L));
    }

    @Test
    public void testAddFirstShort() {
        assertArrayEquals(new short[]{1}, ArrayUtils.addFirst(null, (short) 1));
        assertArrayEquals(new short[]{0, 1}, ArrayUtils.addFirst(new short[]{1}, (short) 0));
        assertArrayEquals(new short[]{1, 1, 0}, ArrayUtils.addFirst(new short[]{1, 0}, (short) 1));
    }

    @Test
    public void testAddFirstObject() {
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.addFirst(null, null));
        assertArrayEquals(new String[]{"a"}, ArrayUtils.addFirst(null, "a"));
        assertArrayEquals(new String[]{null, "a"}, ArrayUtils.addFirst(new String[]{"a"}, null));
        assertArrayEquals(new String[]{"b", "a"}, ArrayUtils.addFirst(new String[]{"a"}, "b"));
        assertArrayEquals(new String[]{"c", "a", "b"}, ArrayUtils.addFirst(new String[]{"a", "b"}, "c"));
    }
}