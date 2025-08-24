package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ArrayUtilsLLM_Test {

    @Test
    public void testIndexOfFloatWithNaN() {
        float[] array = new float[] { Float.NEGATIVE_INFINITY, Float.NaN, Float.POSITIVE_INFINITY, Float.NaN };
        assertEquals(1, ArrayUtils.indexOf(array, Float.NaN));
        assertEquals(3, ArrayUtils.indexOf(array, Float.NaN, 2));
    }

    @Test
    public void testIndexOfFloatWithStartIndex() {
        float[] array = new float[] { 0f, 1f, 2f, 3f, 0f };
        assertEquals(4, ArrayUtils.indexOf(array, 0f, 2));
        assertEquals(-1, ArrayUtils.indexOf(array, 1f, 2));
        assertEquals(2, ArrayUtils.indexOf(array, 2f, 2));
        assertEquals(3, ArrayUtils.indexOf(array, 3f, 2));
        assertEquals(-1, ArrayUtils.indexOf(array, 99f, 0));
        assertEquals(-1, ArrayUtils.indexOf(array, 0f, 6));
    }
}