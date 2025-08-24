package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testReverseBooleanRange() {
        boolean[] array = new boolean[]{false, false, true};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new boolean[]{true, false, false}, array);
        array = new boolean[]{false, false, true};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new boolean[]{false, false, true}, array);
        array = new boolean[]{false, false, true};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new boolean[]{true, false, false}, array);
        array = new boolean[]{false, false, true};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new boolean[]{true, false, false}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseByteRange() {
        byte[] array = new byte[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new byte[]{3, 2, 1}, array);
        array = new byte[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new byte[]{2, 1, 3}, array);
        array = new byte[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new byte[]{3, 2, 1}, array);
        array = new byte[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new byte[]{3, 2, 1}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseCharRange() {
        char[] array = new char[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new char[]{3, 2, 1}, array);
        array = new char[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new char[]{2, 1, 3}, array);
        array = new char[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new char[]{3, 2, 1}, array);
        array = new char[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new char[]{3, 2, 1}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseDoubleRange() {
        double[] array = new double[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new double[]{3, 2, 1}, array);
        array = new double[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new double[]{2, 1, 3}, array);
        array = new double[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new double[]{3, 2, 1}, array);
        array = new double[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new double[]{3, 2, 1}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseFloatRange() {
        float[] array = new float[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new float[]{3, 2, 1}, array);
        array = new float[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new float[]{2, 1, 3}, array);
        array = new float[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new float[]{3, 2, 1}, array);
        array = new float[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new float[]{3, 2, 1}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseIntRange() {
        int[] array = new int[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new int[]{3, 2, 1}, array);
        array = new int[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new int[]{2, 1, 3}, array);
        array = new int[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new int[]{3, 2, 1}, array);
        array = new int[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new int[]{3, 2, 1}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseLongRange() {
        long[] array = new long[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new long[]{3, 2, 1}, array);
        array = new long[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new long[]{2, 1, 3}, array);
        array = new long[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new long[]{3, 2, 1}, array);
        array = new long[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new long[]{3, 2, 1}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseObjectRange() {
        String[] array = new String[]{"1", "2", "3"};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new String[]{"3", "2", "1"}, array);
        array = new String[]{"1", "2", "3"};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new String[]{"2", "1", "3"}, array);
        array = new String[]{"1", "2", "3"};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new String[]{"3", "2", "1"}, array);
        array = new String[]{"1", "2", "3"};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new String[]{"3", "2", "1"}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }

    @Test
    public void testReverseShortRange() {
        short[] array = new short[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 3);
        assertArrayEquals(new short[]{3, 2, 1}, array);
        array = new short[]{1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertArrayEquals(new short[]{2, 1, 3}, array);
        array = new short[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertArrayEquals(new short[]{3, 2, 1}, array);
        array = new short[]{1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertArrayEquals(new short[]{3, 2, 1}, array);
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertNull(array);
    }
}