package org.apache.commons.compress.compressors.deflate64;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HuffmanDecoderLLM_Test {

    @Test
    public void decodeUncompressedBlockWithFinalBlock() throws Exception {
        final byte[] data = {
            0b1, 11, 0, -12, -1, 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'
        };
        final HuffmanDecoder decoder = new HuffmanDecoder(new ByteArrayInputStream(data));
        final byte[] result = new byte[100];
        final int len = decoder.decode(result);
        assertEquals(11, len);
        assertEquals("Hello World", new String(result, 0, len));
    }

    @Test
    public void decodeUncompressedBlockWithFinalBlockAndInvalidLenNLenValue() throws Exception {
        final byte[] data = {
            0b1, 11, 0, -12, -2, 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'
        };
        final HuffmanDecoder decoder = new HuffmanDecoder(new ByteArrayInputStream(data));
        final byte[] result = new byte[100];
        try {
            final int len = decoder.decode(result);
            fail("Should have failed but returned " + len + " entries: " + Arrays.toString(Arrays.copyOf(result, len)));
        } catch (final IllegalStateException e) {
            assertEquals("Illegal LEN / NLEN values", e.getMessage());
        }
    }

    @Test
    public void decodeFixedHuffmanBlockWithFinalBlock() throws Exception {
        final byte[] data = {
            0b11111111111111111111111111110011, 0b00000000000000000000000001001000, 0b11111111111111111111111111001101, 0b11111111111111111111111111001001, 0b11111111111111111111111111001001, 0b00000000000000000000000001010111, 0b00000000000000000000000000001000, 0b11111111111111111111111111001111, 0b00000000000000000000000000101111, 0b11111111111111111111111111001010, 0b00000000000000000000000001001001, 0b00000000000000000000000000000001, 0b11111111111111111111111111111100
        };
        final HuffmanDecoder decoder = new HuffmanDecoder(new ByteArrayInputStream(data));
        final byte[] result = new byte[100];
        final int len = decoder.decode(result);
        assertEquals(11, len);
        assertEquals("Hello World", new String(result, 0, len));
    }

    @Test
    public void decodeFixedHuffmanBlockWithFinalBlockToSmallBuffer() throws Exception {
        final byte[] data = {
            0b11111111111111111111111111110011, 0b00000000000000000000000001001000, 0b11111111111111111111111111001101, 0b11111111111111111111111111001001, 0b11111111111111111111111111001001, 0b00000000000000000000000001010111, 0b00000000000000000000000000001000, 0b11111111111111111111111111001111, 0b00000000000000000000000000101111, 0b11111111111111111111111111001010, 0b00000000000000000000000001001001, 0b00000000000000000000000000000001, 0b11111111111111111111111111111100
        };
        final HuffmanDecoder decoder = new HuffmanDecoder(new ByteArrayInputStream(data));
        final byte[] result = new byte[10];
        int len;
        len = decoder.decode(result);
        assertEquals(10, len);
        assertEquals("Hello Worl", new String(result, 0, len));
        len = decoder.decode(result);
        assertEquals(1, len);
        assertEquals("d", new String(result, 0, len));
    }
}