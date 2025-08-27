package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testAddWithFinalClassType() {
        // Test adding an element to a non-null array
        String[] array = {"a", "b", "c"};
        String element = "d";
        String[] result = ArrayUtils.add(array, element);
        assertEquals(4, result.length);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
        assertEquals("c", result[2]);
        assertEquals("d", result[3]);

        // Test adding an element to a null array
        array = null;
        result = ArrayUtils.add(array, element);
        assertEquals(1, result.length);
        assertEquals("d", result[0]);

        // Test adding a null element to a non-null array
        array = new String[]{"a", "b", "c"};
        element = null;
        result = ArrayUtils.add(array, element);
        assertEquals(4, result.length);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
        assertEquals("c", result[2]);
        assertEquals(null, result[3]);

        // Test adding a null element to a null array
        array = null;
        result = ArrayUtils.add(array, element);
        assertEquals(1, result.length);
        assertEquals(null, result[0]);

        // Test adding an element to an empty array
        array = new String[0];
        element = "a";
        result = ArrayUtils.add(array, element);
        assertEquals(1, result.length);
        assertEquals("a", result[0]);

        // Test adding a null element to an empty array
        array = new String[0];
        element = null;
        result = ArrayUtils.add(array, element);
        assertEquals(1, result.length);
        assertEquals(null, result[0]);
    }
}