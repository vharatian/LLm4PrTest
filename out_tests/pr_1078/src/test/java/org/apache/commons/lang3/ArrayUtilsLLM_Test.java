package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testInsertBooleanArrayNull() {
        assertNull(ArrayUtils.insert(0, (boolean[]) null, true));
    }

    @Test
    public void testInsertByteArrayNull() {
        assertNull(ArrayUtils.insert(0, (byte[]) null, (byte) 1));
    }

    @Test
    public void testInsertCharArrayNull() {
        assertNull(ArrayUtils.insert(0, (char[]) null, 'a'));
    }

    @Test
    public void testInsertDoubleArrayNull() {
        assertNull(ArrayUtils.insert(0, (double[]) null, 1.0));
    }

    @Test
    public void testInsertFloatArrayNull() {
        assertNull(ArrayUtils.insert(0, (float[]) null, 1.0f));
    }

    @Test
    public void testInsertIntArrayNull() {
        assertNull(ArrayUtils.insert(0, (int[]) null, 1));
    }

    @Test
    public void testInsertLongArrayNull() {
        assertNull(ArrayUtils.insert(0, (long[]) null, 1L));
    }

    @Test
    public void testInsertShortArrayNull() {
        assertNull(ArrayUtils.insert(0, (short[]) null, (short) 1));
    }

    @Test
    public void testInsertBooleanArray() {
        boolean[] array = {true, false};
        boolean[] result = ArrayUtils.insert(1, array, true);
        assertArrayEquals(new boolean[]{true, true, false}, result);
    }

    @Test
    public void testInsertByteArray() {
        byte[] array = {1, 2};
        byte[] result = ArrayUtils.insert(1, array, (byte) 3);
        assertArrayEquals(new byte[]{1, 3, 2}, result);
    }

    @Test
    public void testInsertCharArray() {
        char[] array = {'a', 'b'};
        char[] result = ArrayUtils.insert(1, array, 'c');
        assertArrayEquals(new char[]{'a', 'c', 'b'}, result);
    }

    @Test
    public void testInsertDoubleArray() {
        double[] array = {1.0, 2.0};
        double[] result = ArrayUtils.insert(1, array, 3.0);
        assertArrayEquals(new double[]{1.0, 3.0, 2.0}, result);
    }

    @Test
    public void testInsertFloatArray() {
        float[] array = {1.0f, 2.0f};
        float[] result = ArrayUtils.insert(1, array, 3.0f);
        assertArrayEquals(new float[]{1.0f, 3.0f, 2.0f}, result);
    }

    @Test
    public void testInsertIntArray() {
        int[] array = {1, 2};
        int[] result = ArrayUtils.insert(1, array, 3);
        assertArrayEquals(new int[]{1, 3, 2}, result);
    }

    @Test
    public void testInsertLongArray() {
        long[] array = {1L, 2L};
        long[] result = ArrayUtils.insert(1, array, 3L);
        assertArrayEquals(new long[]{1L, 3L, 2L}, result);
    }

    @Test
    public void testInsertShortArray() {
        short[] array = {1, 2};
        short[] result = ArrayUtils.insert(1, array, (short) 3);
        assertArrayEquals(new short[]{1, 3, 2}, result);
    }
}