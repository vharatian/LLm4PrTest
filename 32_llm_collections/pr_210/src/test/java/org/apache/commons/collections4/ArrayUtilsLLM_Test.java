package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsLLM_Test {

    @Test
    void testContainsWithNullArray() {
        Object[] array = null;
        Object objectToFind = "test";
        assertFalse(ArrayUtils.contains(array, objectToFind));
    }

    @Test
    void testContainsWithObjectNotFound() {
        Object[] array = {"one", "two", "three"};
        Object objectToFind = "four";
        assertFalse(ArrayUtils.contains(array, objectToFind));
    }

    @Test
    void testContainsWithObjectFound() {
        Object[] array = {"one", "two", "three"};
        Object objectToFind = "two";
        assertTrue(ArrayUtils.contains(array, objectToFind));
    }

    @Test
    void testIndexOfWithNullArray() {
        Object[] array = null;
        Object objectToFind = "test";
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, ArrayUtils.indexOf(array, objectToFind));
    }

    @Test
    void testIndexOfWithObjectNotFound() {
        Object[] array = {"one", "two", "three"};
        Object objectToFind = "four";
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, ArrayUtils.indexOf(array, objectToFind));
    }

    @Test
    void testIndexOfWithObjectFound() {
        Object[] array = {"one", "two", "three"};
        Object objectToFind = "two";
        assertEquals(1, ArrayUtils.indexOf(array, objectToFind));
    }

    @Test
    void testIndexOfWithNullObjectToFind() {
        Object[] array = {"one", null, "three"};
        Object objectToFind = null;
        assertEquals(1, ArrayUtils.indexOf(array, objectToFind));
    }

    @Test
    void testIndexOfWithStartIndex() {
        Object[] array = {"one", "two", "three", "two"};
        Object objectToFind = "two";
        assertEquals(3, ArrayUtils.indexOf(array, objectToFind, 2));
    }

    @Test
    void testIndexOfWithNegativeStartIndex() {
        Object[] array = {"one", "two", "three"};
        Object objectToFind = "two";
        assertEquals(1, ArrayUtils.indexOf(array, objectToFind, -1));
    }

    @Test
    void testIndexOfWithStartIndexGreaterThanArrayLength() {
        Object[] array = {"one", "two", "three"};
        Object objectToFind = "two";
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, ArrayUtils.indexOf(array, objectToFind, 5));
    }
}