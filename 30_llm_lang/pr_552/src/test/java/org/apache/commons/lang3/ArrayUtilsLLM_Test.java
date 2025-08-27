package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testGetWithDefaultValue() {
        // Test with null array
        assertEquals("default", ArrayUtils.get(null, 0, "default"));
        assertEquals("default", ArrayUtils.get(null, -1, "default"));
        assertEquals("default", ArrayUtils.get(null, 1, "default"));

        // Test with empty array
        String[] emptyArray = {};
        assertEquals("default", ArrayUtils.get(emptyArray, 0, "default"));
        assertEquals("default", ArrayUtils.get(emptyArray, -1, "default"));
        assertEquals("default", ArrayUtils.get(emptyArray, 1, "default"));

        // Test with non-empty array
        String[] array = {"a", "b", "c"};
        assertEquals("a", ArrayUtils.get(array, 0, "default"));
        assertEquals("b", ArrayUtils.get(array, 1, "default"));
        assertEquals("c", ArrayUtils.get(array, 2, "default"));
        assertEquals("default", ArrayUtils.get(array, 3, "default"));
        assertEquals("default", ArrayUtils.get(array, -1, "default"));
    }
}