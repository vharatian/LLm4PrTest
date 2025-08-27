package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsLLM_Test {

    @Test
    void testContains_NullArray() {
        Object[] array = null;
        assertFalse(ArrayUtils.contains(array, "test"));
    }

    @Test
    void testContains_ObjectFound() {
        Object[] array = {"a", "b", "c"};
        assertTrue(ArrayUtils.contains(array, "b"));
    }

    @Test
    void testContains_ObjectNotFound() {
        Object[] array = {"a", "b", "c"};
        assertFalse(ArrayUtils.contains(array, "d"));
    }

    @Test
    void testContains_NullObjectInArray() {
        Object[] array = {"a", null, "c"};
        assertTrue(ArrayUtils.contains(array, null));
    }

    @Test
    void testIndexOf_NullArray() {
        Object[] array = null;
        assertEquals(ArrayUtils.INDEX_NOT_FOUND, ArrayUtils.indexOf(array, "test"));
    }

    @Test
    void testIndexOf_ObjectFound() {
        Object[] array = {"a", "b", "c"};
        assertEquals(1, ArrayUtils.indexOf(array, "b"));
    }

    @Test
    void testIndexOf_ObjectNotFound() {
        Object[] array = {"a", "b", "c"};
        assertEquals(ArrayUtils.INDEX_NOT_FOUND, ArrayUtils.indexOf(array, "d"));
    }

    @Test
    void testIndexOf_NullObjectInArray() {
        Object[] array = {"a", null, "c"};
        assertEquals(1, ArrayUtils.indexOf(array, null));
    }

    @Test
    void testIndexOf_WithStartIndex_ObjectFound() {
        Object[] array = {"a", "b", "c", "b"};
        assertEquals(3, ArrayUtils.indexOf(array, "b", 2));
    }

    @Test
    void testIndexOf_WithStartIndex_ObjectNotFound() {
        Object[] array = {"a", "b", "c"};
        assertEquals(ArrayUtils.INDEX_NOT_FOUND, ArrayUtils.indexOf(array, "b", 2));
    }

    @Test
    void testIndexOf_WithStartIndex_Negative() {
        Object[] array = {"a", "b", "c"};
        assertEquals(1, ArrayUtils.indexOf(array, "b", -1));
    }

    @Test
    void testIndexOf_WithStartIndex_LargerThanArrayLength() {
        Object[] array = {"a", "b", "c"};
        assertEquals(ArrayUtils.INDEX_NOT_FOUND, ArrayUtils.indexOf(array, "b", 5));
    }
}