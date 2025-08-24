package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HashCodeBuilderLLM_Test {

    @Test
    public void testAppendArrayCommentChange() {
        // This test ensures that the functionality of appendArray method remains unchanged
        // despite the comment modification in the source code.

        // Test with long array
        long[] longArray = {1L, 2L, 3L};
        assertEquals(new HashCodeBuilder(17, 37).append(longArray).toHashCode(),
                     new HashCodeBuilder(17, 37).append(longArray).toHashCode());

        // Test with int array
        int[] intArray = {1, 2, 3};
        assertEquals(new HashCodeBuilder(17, 37).append(intArray).toHashCode(),
                     new HashCodeBuilder(17, 37).append(intArray).toHashCode());

        // Test with multidimensional array
        int[][] multiArray = {{1, 2}, {3, 4}};
        assertEquals(new HashCodeBuilder(17, 37).append(multiArray).toHashCode(),
                     new HashCodeBuilder(17, 37).append(multiArray).toHashCode());
    }
}