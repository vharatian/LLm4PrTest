package org.apache.commons.compress.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class ArchiveUtilsLLM_Test {

    @Test
    public void testMatchAsciiBufferWithOffsetAndLength() {
        String expected = "test";
        byte[] buffer = "test buffer".getBytes(StandardCharsets.US_ASCII);
        assertTrue(ArchiveUtils.matchAsciiBuffer(expected, buffer, 0, 4));
    }

    @Test
    public void testMatchAsciiBuffer() {
        String expected = "test";
        byte[] buffer = "test".getBytes(StandardCharsets.US_ASCII);
        assertTrue(ArchiveUtils.matchAsciiBuffer(expected, buffer));
    }

    @Test
    public void testToAsciiBytes() {
        String inputString = "test";
        byte[] expectedBytes = inputString.getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expectedBytes, ArchiveUtils.toAsciiBytes(inputString));
    }

    @Test
    public void testToAsciiString() {
        byte[] inputBytes = "test".getBytes(StandardCharsets.US_ASCII);
        String expectedString = new String(inputBytes, StandardCharsets.US_ASCII);
        assertEquals(expectedString, ArchiveUtils.toAsciiString(inputBytes));
    }

    @Test
    public void testToAsciiStringWithOffsetAndLength() {
        byte[] inputBytes = "test buffer".getBytes(StandardCharsets.US_ASCII);
        String expectedString = new String(inputBytes, 0, 4, StandardCharsets.US_ASCII);
        assertEquals(expectedString, ArchiveUtils.toAsciiString(inputBytes, 0, 4));
    }
}