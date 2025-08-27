package org.apache.commons.imaging.palette;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorSpaceSubsetLLM_Test {

    @Test
    void testConstructorInitializesMinsCorrectly() {
        int total = 100;
        int precision = 8;
        ColorSpaceSubset subset = new ColorSpaceSubset(total, precision);
        
        // Ensure mins array is initialized correctly
        for (int min : subset.mins) {
            assertEquals(0, min, "Expected mins array to be initialized to 0");
        }
    }

    @Test
    void testConstructorWithParamsInitializesMinsCorrectly() {
        int total = 100;
        int precision = 8;
        int[] mins = {1, 2, 3};
        int[] maxs = {4, 5, 6};
        ColorSpaceSubset subset = new ColorSpaceSubset(total, precision, mins, maxs);
        
        // Ensure mins array is initialized correctly
        assertArrayEquals(mins, subset.mins, "Expected mins array to be initialized to the provided values");
    }
}