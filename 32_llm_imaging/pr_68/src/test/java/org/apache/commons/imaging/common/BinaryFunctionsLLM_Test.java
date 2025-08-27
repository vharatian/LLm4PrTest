package org.apache.commons.imaging.common;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryFunctionsLLM_Test {

    @Test
    public void testQuadsToByteArray() {
        int quad = 0x12345678;
        byte[] expected = {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78};
        byte[] result = BinaryFunctions.quadsToByteArray(quad);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSearchQuadFound() throws IOException {
        int quad = 0x12345678;
        byte[] haystack = {(byte) 0x00, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0x00};
        InputStream bis = new ByteArrayInputStream(haystack);
        assertTrue(BinaryFunctions.searchQuad(quad, bis));
    }

    @Test
    public void testSearchQuadNotFound() throws IOException {
        int quad = 0x12345678;
        byte[] haystack = {(byte) 0x00, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x79, (byte) 0x00};
        InputStream bis = new ByteArrayInputStream(haystack);
        assertFalse(BinaryFunctions.searchQuad(quad, bis));
    }

    @Test
    public void testSearchQuadEmptyStream() throws IOException {
        int quad = 0x12345678;
        byte[] haystack = {};
        InputStream bis = new ByteArrayInputStream(haystack);
        assertFalse(BinaryFunctions.searchQuad(quad, bis));
    }

    @Test
    public void testSearchQuadPartialMatch() throws IOException {
        int quad = 0x12345678;
        byte[] haystack = {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x00, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78};
        InputStream bis = new ByteArrayInputStream(haystack);
        assertTrue(BinaryFunctions.searchQuad(quad, bis));
    }
}