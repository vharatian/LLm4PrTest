package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testAddFirstShort() {
        short[] array = {2, 3, 4};
        short[] result = ArrayUtils.addFirst(array, (short) 1);
        assertEquals((short) 1, result[0]);
        assertEquals((short) 2, result[1]);
        assertEquals((short) 3, result[2]);
        assertEquals((short) 4, result[3]);
    }

    @Test
    public void testHashCodeMultidimensional() {
        long[][] array1 = {{2, 5}, {4, 5}};
        long[][] array2 = {{2, 5}, {4, 6}};
        assertEquals(ArrayUtils.hashCode(array1), ArrayUtils.hashCode(array1));
        assertThrows(AssertionError.class, () -> assertEquals(ArrayUtils.hashCode(array1), ArrayUtils.hashCode(array2)));
    }

    @Test
    public void testIndexesOfBoolean() {
        boolean[] array = {true, false, true, false, true};
        BitSet expected = new BitSet();
        expected.set(0);
        expected.set(2);
        expected.set(4);
        assertEquals(expected, ArrayUtils.indexesOf(array, true));
    }

    @Test
    public void testRemoveAllOccurrencesBoolean() {
        boolean[] array = {true, false, true, false, true};
        boolean[] result = ArrayUtils.removeAllOccurrences(array, true);
        assertEquals(2, result.length);
        assertEquals(false, result[0]);
        assertEquals(false, result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesByte() {
        byte[] array = {1, 2, 1, 2, 1};
        byte[] result = ArrayUtils.removeAllOccurrences(array, (byte) 1);
        assertEquals(2, result.length);
        assertEquals((byte) 2, result[0]);
        assertEquals((byte) 2, result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesChar() {
        char[] array = {'a', 'b', 'a', 'b', 'a'};
        char[] result = ArrayUtils.removeAllOccurrences(array, 'a');
        assertEquals(2, result.length);
        assertEquals('b', result[0]);
        assertEquals('b', result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesDouble() {
        double[] array = {1.1, 2.2, 1.1, 2.2, 1.1};
        double[] result = ArrayUtils.removeAllOccurrences(array, 1.1);
        assertEquals(2, result.length);
        assertEquals(2.2, result[0]);
        assertEquals(2.2, result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesFloat() {
        float[] array = {1.1f, 2.2f, 1.1f, 2.2f, 1.1f};
        float[] result = ArrayUtils.removeAllOccurrences(array, 1.1f);
        assertEquals(2, result.length);
        assertEquals(2.2f, result[0]);
        assertEquals(2.2f, result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesInt() {
        int[] array = {1, 2, 1, 2, 1};
        int[] result = ArrayUtils.removeAllOccurrences(array, 1);
        assertEquals(2, result.length);
        assertEquals(2, result[0]);
        assertEquals(2, result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesLong() {
        long[] array = {1L, 2L, 1L, 2L, 1L};
        long[] result = ArrayUtils.removeAllOccurrences(array, 1L);
        assertEquals(2, result.length);
        assertEquals(2L, result[0]);
        assertEquals(2L, result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesShort() {
        short[] array = {1, 2, 1, 2, 1};
        short[] result = ArrayUtils.removeAllOccurrences(array, (short) 1);
        assertEquals(2, result.length);
        assertEquals((short) 2, result[0]);
        assertEquals((short) 2, result[1]);
    }

    @Test
    public void testRemoveAllOccurrencesObject() {
        String[] array = {"a", "b", "a", "b", "a"};
        String[] result = ArrayUtils.removeAllOccurrences(array, "a");
        assertEquals(2, result.length);
        assertEquals("b", result[0]);
        assertEquals("b", result[1]);
    }
}