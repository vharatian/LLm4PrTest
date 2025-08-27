package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IndexUtilsLLM_Test {

    @Test
    void testEnsureCapacityForAddWhenIndexWithinArrayLength() {
        int[] array = {1, 2, 3, 4, 5};
        int index = 3;
        int[] result = IndexUtils.ensureCapacityForAdd(array, index);
        assertSame(array, result, "Array should not be resized when index is within array length");
    }

    @Test
    void testEnsureCapacityForAddWhenIndexExceedsArrayLength() {
        int[] array = {1, 2, 3, 4, 5};
        int index = 10;
        int[] result = IndexUtils.ensureCapacityForAdd(array, index);
        assertNotSame(array, result, "Array should be resized when index exceeds array length");
        assertEquals(11, result.length, "New array length should be index + 1");
    }

    @Test
    void testEnsureCapacityForAddWhenArrayNeedsDoubling() {
        int[] array = {1, 2};
        int index = 3;
        int[] result = IndexUtils.ensureCapacityForAdd(array, index);
        assertNotSame(array, result, "Array should be resized when index exceeds array length");
        assertEquals(4, result.length, "New array length should be double the original length");
    }

    @Test
    void testEnsureCapacityForAddWhenArrayReachesMaxSize() {
        int[] array = new int[IndexUtils.MAX_ARRAY_SIZE - 1];
        int index = IndexUtils.MAX_ARRAY_SIZE - 1;
        int[] result = IndexUtils.ensureCapacityForAdd(array, index);
        assertSame(array, result, "Array should not be resized when index is within max array size");
    }

    @Test
    void testEnsureCapacityForAddWhenArrayExceedsMaxSize() {
        int[] array = new int[IndexUtils.MAX_ARRAY_SIZE - 1];
        int index = IndexUtils.MAX_ARRAY_SIZE;
        int[] result = IndexUtils.ensureCapacityForAdd(array, index);
        assertNotSame(array, result, "Array should be resized when index exceeds max array size");
        assertEquals(IndexUtils.MAX_ARRAY_SIZE, result.length, "New array length should be max array size");
    }
}