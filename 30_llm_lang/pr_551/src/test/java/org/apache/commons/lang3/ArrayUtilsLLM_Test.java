package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testIsArrayIndexValid() {
        // Test cases for the modified isArrayIndexValid method
        assertFalse(ArrayUtils.isArrayIndexValid(null, 0)); // null array
        assertFalse(ArrayUtils.isArrayIndexValid(new String[]{}, 0)); // empty array
        assertTrue(ArrayUtils.isArrayIndexValid(new String[]{"a"}, 0)); // valid index
        assertFalse(ArrayUtils.isArrayIndexValid(new String[]{"a"}, 1)); // index out of bounds
        assertFalse(ArrayUtils.isArrayIndexValid(new String[]{"a"}, -1)); // negative index
    }
}