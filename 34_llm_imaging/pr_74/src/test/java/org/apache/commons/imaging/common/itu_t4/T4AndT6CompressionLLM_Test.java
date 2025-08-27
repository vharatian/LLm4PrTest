package org.apache.commons.imaging.common.itu_t4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class T4AndT6CompressionLLM_Test {

    @Test
    public void testNextChangingElement() {
        int[] line = {0, 0, 1, 1, 0, 0, 1, 1};
        int currentColour = 0;
        int start = 0;

        // Test when the next changing element is within the array bounds
        int result = T4AndT6Compression.nextChangingElement(line, currentColour, start);
        assertEquals(2, result);

        // Test when the next changing element is at the end of the array
        start = 6;
        result = T4AndT6Compression.nextChangingElement(line, currentColour, start);
        assertEquals(8, result);

        // Test when the next changing element is beyond the array bounds
        start = 8;
        result = T4AndT6Compression.nextChangingElement(line, currentColour, start);
        assertEquals(8, result);
    }
}