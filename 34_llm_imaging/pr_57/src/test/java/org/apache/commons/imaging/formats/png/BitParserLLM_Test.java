package org.apache.commons.imaging.formats.png;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitParserLLM_Test {

    @Test
    void testBitParserClonesByteArray() {
        byte[] originalBytes = {0x01, 0x02, 0x03, 0x04};
        BitParser bitParser = new BitParser(originalBytes, 8, 8);
        
        // Modify the original array
        originalBytes[0] = 0x0A;
        
        // Ensure the BitParser's byte array is not affected
        assertEquals(0x01, bitParser.getSample(0, 0));
    }

    @Test
    void testGetSample() throws ImageReadException {
        byte[] bytes = {0x01, 0x02, 0x03, 0x04};
        BitParser bitParser = new BitParser(bytes, 8, 8);
        
        assertEquals(0x01, bitParser.getSample(0, 0));
        assertEquals(0x02, bitParser.getSample(1, 0));
    }

    @Test
    void testGetSampleAsByte() throws ImageReadException {
        byte[] bytes = {0x01, 0x02, 0x03, 0x04};
        BitParser bitParser = new BitParser(bytes, 8, 8);
        
        assertEquals(0x01, bitParser.getSampleAsByte(0, 0));
        assertEquals(0x02, bitParser.getSampleAsByte(1, 0));
    }
}