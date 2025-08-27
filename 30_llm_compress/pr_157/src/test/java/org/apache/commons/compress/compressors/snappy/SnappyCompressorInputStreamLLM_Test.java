package org.apache.commons.compress.compressors.snappy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnappyCompressorInputStreamLLM_Test {

    @Test
    public void testReadLiteralLength() throws IOException {
        byte[] input = new byte[]{(byte) 0xF0}; // 0xF0 >> 2 = 60, should trigger case 60
        InputStream is = new ByteArrayInputStream(input);
        SnappyCompressorInputStream snappyStream = new SnappyCompressorInputStream(is);

        int result = snappyStream.readLiteralLength(0xF0);
        assertEquals(1, result, "Expected literal length to be 1");
    }

    @Test
    public void testReadLiteralLengthCase61() throws IOException {
        byte[] input = new byte[]{(byte) 0xFC, 0x01, 0x00}; // 0xFC >> 2 = 61, should trigger case 61
        InputStream is = new ByteArrayInputStream(input);
        SnappyCompressorInputStream snappyStream = new SnappyCompressorInputStream(is);

        int result = snappyStream.readLiteralLength(0xFC);
        assertEquals(257, result, "Expected literal length to be 257");
    }

    @Test
    public void testReadLiteralLengthCase62() throws IOException {
        byte[] input = new byte[]{(byte) 0xF8, 0x01, 0x00, 0x00}; // 0xF8 >> 2 = 62, should trigger case 62
        InputStream is = new ByteArrayInputStream(input);
        SnappyCompressorInputStream snappyStream = new SnappyCompressorInputStream(is);

        int result = snappyStream.readLiteralLength(0xF8);
        assertEquals(65537, result, "Expected literal length to be 65537");
    }

    @Test
    public void testReadLiteralLengthCase63() throws IOException {
        byte[] input = new byte[]{(byte) 0xFC, 0x01, 0x00, 0x00, 0x00}; // 0xFC >> 2 = 63, should trigger case 63
        InputStream is = new ByteArrayInputStream(input);
        SnappyCompressorInputStream snappyStream = new SnappyCompressorInputStream(is);

        int result = snappyStream.readLiteralLength(0xFC);
        assertEquals(16777217, result, "Expected literal length to be 16777217");
    }
}