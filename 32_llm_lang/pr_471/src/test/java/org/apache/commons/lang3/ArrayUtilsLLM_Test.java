package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.BitSet;

import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testIndexesOfObjectArray() {
        Object[] array = {"a", "b", "a", "c", "a"};
        BitSet bitSet = ArrayUtils.indexesOf(array, "a");
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, "b");
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, "d");
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, null);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, "a");
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfObjectArrayWithStartIndex() {
        Object[] array = {"a", "b", "a", "c", "a"};
        BitSet bitSet = ArrayUtils.indexesOf(array, "a", 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, "a", 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, "a", 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, "a", -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfLongArray() {
        long[] array = {1L, 2L, 1L, 3L, 1L};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1L);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 2L);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, 4L);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, 1L);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfLongArrayWithStartIndex() {
        long[] array = {1L, 2L, 1L, 3L, 1L};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1L, 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1L, 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1L, 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, 1L, -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfIntArray() {
        int[] array = {1, 2, 1, 3, 1};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 2);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, 4);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, 1);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfIntArrayWithStartIndex() {
        int[] array = {1, 2, 1, 3, 1};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1, 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1, 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1, 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, 1, -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfShortArray() {
        short[] array = {1, 2, 1, 3, 1};
        BitSet bitSet = ArrayUtils.indexesOf(array, (short) 1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, (short) 2);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, (short) 4);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, (short) 1);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfShortArrayWithStartIndex() {
        short[] array = {1, 2, 1, 3, 1};
        BitSet bitSet = ArrayUtils.indexesOf(array, (short) 1, 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, (short) 1, 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, (short) 1, 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, (short) 1, -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfCharArray() {
        char[] array = {'a', 'b', 'a', 'c', 'a'};
        BitSet bitSet = ArrayUtils.indexesOf(array, 'a');
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 'b');
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, 'd');
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, 'a');
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfCharArrayWithStartIndex() {
        char[] array = {'a', 'b', 'a', 'c', 'a'};
        BitSet bitSet = ArrayUtils.indexesOf(array, 'a', 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 'a', 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 'a', 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, 'a', -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfByteArray() {
        byte[] array = {1, 2, 1, 3, 1};
        BitSet bitSet = ArrayUtils.indexesOf(array, (byte) 1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, (byte) 2);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, (byte) 4);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, (byte) 1);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfByteArrayWithStartIndex() {
        byte[] array = {1, 2, 1, 3, 1};
        BitSet bitSet = ArrayUtils.indexesOf(array, (byte) 1, 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, (byte) 1, 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, (byte) 1, 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, (byte) 1, -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfDoubleArray() {
        double[] array = {1.0, 2.0, 1.0, 3.0, 1.0};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1.0);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 2.0);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, 4.0);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, 1.0);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfDoubleArrayWithStartIndex() {
        double[] array = {1.0, 2.0, 1.0, 3.0, 1.0};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1.0, 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1.0, 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1.0, 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, 1.0, -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfDoubleArrayWithTolerance() {
        double[] array = {1.0, 2.0, 1.0, 3.0, 1.0};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1.0, 0.1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1.1, 0.2);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 2.0, 0.1);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, 4.0, 0.1);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, 1.0, 0.1);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfDoubleArrayWithStartIndexAndTolerance() {
        double[] array = {1.0, 2.0, 1.0, 3.0, 1.0};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1.0, 1, 0.1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1.0, 3, 0.1);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1.0, 5, 0.1);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, 1.0, -1, 0.1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfFloatArray() {
        float[] array = {1.0f, 2.0f, 1.0f, 3.0f, 1.0f};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1.0f);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 2.0f);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(1));

        bitSet = ArrayUtils.indexesOf(array, 4.0f);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(null, 1.0f);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfFloatArrayWithStartIndex() {
        float[] array = {1.0f, 2.0f, 1.0f, 3.0f, 1.0f};
        BitSet bitSet = ArrayUtils.indexesOf(array, 1.0f, 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1.0f, 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(array, 1.0f, 5);
        assertEquals(0, bitSet.cardinality());

        bitSet = ArrayUtils.indexesOf(array, 1.0f, -1);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(4));
    }

    @Test
    public void testIndexesOfBooleanArray() {
        boolean[] array = {true, false, true, true, false};
        BitSet bitSet = ArrayUtils.indexesOf(array, true);
        assertEquals(3, bitSet.cardinality());
        assertTrue(bitSet.get(0));
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(3));

        bitSet = ArrayUtils.indexesOf(array, false);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(1));
        assertTrue(bitSet.get(4));

        bitSet = ArrayUtils.indexesOf(null, true);
        assertEquals(0, bitSet.cardinality());
    }

    @Test
    public void testIndexesOfBooleanArrayWithStartIndex() {
        boolean[] array = {true, false, true, true, false};
        BitSet bitSet = ArrayUtils.indexesOf(array, true, 1);
        assertEquals(2, bitSet.cardinality());
        assertTrue(bitSet.get(2));
        assertTrue(bitSet.get(3));

        bitSet = ArrayUtils.indexesOf(array, true, 3);
        assertEquals(1, bitSet.cardinality());
        assertTrue(bitSet.get(3));

