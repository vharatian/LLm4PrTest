package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AttributeDefinitionBandsLLM_Test {

    @Test
    public void testAddHighIndices() {
        AttributeDefinitionBands bands = new AttributeDefinitionBands(null, 0, new Attribute[0]);
        
        int[] initialIndices = {1, 2, 3, 4, 5};
        int[] expectedIndices = {1, 2, 3, 4, 5, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63};
        
        int[] resultIndices = bands.addHighIndices(initialIndices);
        
        assertArrayEquals(expectedIndices, resultIndices);
    }
}