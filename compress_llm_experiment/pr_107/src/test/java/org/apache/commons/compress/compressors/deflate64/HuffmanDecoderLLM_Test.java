package org.apache.commons.compress.compressors.deflate64;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HuffmanDecoderLLM_Test {

    /**
     * Test to ensure that the InitialState class is properly instantiated and behaves correctly.
     */
    @Test
    public void testInitialStateBehavior() throws Exception {
        HuffmanDecoder decoder = new HuffmanDecoder(new ByteArrayInputStream(new byte[0]));
        byte[] result = new byte[10];
        try {
            decoder.decode(result);
            fail("Should have thrown an IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Cannot read in this state", e.getMessage());
        }
    }

    /**
     * Test to ensure that the InitialState class is properly reset when the decoder is closed.
     */
    @Test
    public void testInitialStateAfterClose() throws Exception {
        HuffmanDecoder decoder = new HuffmanDecoder(new ByteArrayInputStream(new byte[0]));
        decoder.close();
        byte[] result = new byte[10];
        try {
            decoder.decode(result);
            fail("Should have thrown an IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Cannot read in this state", e.getMessage());
        }
    }
}