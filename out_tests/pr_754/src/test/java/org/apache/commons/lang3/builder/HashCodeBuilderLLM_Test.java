package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.ObjectUtils;

public class HashCodeBuilderLLM_Test {

    @Test
    public void testAppendObjectWithArrayUsingObjectUtils() {
        // Test with null object
        Object obj = null;
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());

        // Test with non-null object
        obj = new Object();
        assertEquals(17 * 37 + obj.hashCode(), new HashCodeBuilder(17, 37).append(obj).toHashCode());

        // Test with array object
        obj = new int[]{1, 2, 3};
        int expectedHashCode = new HashCodeBuilder(17, 37).append((int[]) obj).toHashCode();
        assertEquals(expectedHashCode, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testAppendArrayUsingObjectUtils() {
        // Test with different types of arrays
        int[] intArray = {1, 2, 3};
        assertEquals(new HashCodeBuilder(17, 37).append(intArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) intArray).toHashCode());

        long[] longArray = {1L, 2L, 3L};
        assertEquals(new HashCodeBuilder(17, 37).append(longArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) longArray).toHashCode());

        short[] shortArray = {1, 2, 3};
        assertEquals(new HashCodeBuilder(17, 37).append(shortArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) shortArray).toHashCode());

        char[] charArray = {'a', 'b', 'c'};
        assertEquals(new HashCodeBuilder(17, 37).append(charArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) charArray).toHashCode());

        byte[] byteArray = {1, 2, 3};
        assertEquals(new HashCodeBuilder(17, 37).append(byteArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) byteArray).toHashCode());

        double[] doubleArray = {1.1, 2.2, 3.3};
        assertEquals(new HashCodeBuilder(17, 37).append(doubleArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) doubleArray).toHashCode());

        float[] floatArray = {1.1f, 2.2f, 3.3f};
        assertEquals(new HashCodeBuilder(17, 37).append(floatArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) floatArray).toHashCode());

        boolean[] booleanArray = {true, false, true};
        assertEquals(new HashCodeBuilder(17, 37).append(booleanArray).toHashCode(), new HashCodeBuilder(17, 37).append((Object) booleanArray).toHashCode());
    }
}