package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ObjectUtilsLLM_Test {

    @Test
    public void testIsArray() {
        // Test cases for the new isArray method
        assertFalse(ObjectUtils.isArray(null));
        assertFalse(ObjectUtils.isArray(""));
        assertFalse(ObjectUtils.isArray("ab"));
        assertTrue(ObjectUtils.isArray(new int[]{}));
        assertTrue(ObjectUtils.isArray(new int[]{1, 2, 3}));
        assertFalse(ObjectUtils.isArray(1234));
    }

    @Test
    public void testCloneWithIsArray() {
        // Test cases to ensure clone method works correctly with the new isArray method
        assertTrue(Arrays.deepEquals(new String[]{"string"}, ObjectUtils.clone(new String[]{"string"})));
        assertArrayEquals(new int[]{1}, ObjectUtils.clone(new int[]{1}));
    }

    @Test
    public void testIsEmptyWithIsArray() {
        // Test cases to ensure isEmpty method works correctly with the new isArray method
        assertTrue(ObjectUtils.isEmpty(new int[]{}));
        assertFalse(ObjectUtils.isEmpty(new int[]{1, 2, 3}));
    }
}