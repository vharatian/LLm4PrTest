package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class CharSequenceInputStreamLLM_Test {

    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private static final String TEST_STRING = "\u00e0 peine arriv\u00e9s nous entr\u00e2mes dans sa chambre";

    /**
     * Test to ensure that the available() method correctly estimates the number of bytes
     * remaining in the buffer and the character buffer.
     */
    @Test
    public void testAvailableEstimation() throws Exception {
        final String input = "test";
        try (InputStream r = new CharSequenceInputStream(input, UTF_8)) {
            int available = r.available();
            assertTrue(available >= input.length(), "Available bytes should be at least the length of the input string");
            r.read();
            int newAvailable = r.available();
            assertTrue(newAvailable < available, "Available bytes should decrease after reading");
        }
    }

    /**
     * Test to ensure that the mark() method correctly marks the position in both the character buffer
     * and the byte buffer.
     */
    @Test
    public void testMarkPositioning() throws Exception {
        final String input = "test";
        try (InputStream r = new CharSequenceInputStream(input, UTF_8)) {
            r.read();
            r.mark(0);
            int availableAfterMark = r.available();
            r.read();
            r.reset();
            int availableAfterReset = r.available();
            assertEquals(availableAfterMark, availableAfterReset, "Available bytes after reset should match the available bytes after mark");
        }
    }

    /**
     * Test to ensure that the reset() method correctly resets the position in both the character buffer
     * and the byte buffer.
     */
    @Test
    public void testResetFunctionality() throws Exception {
        final String input = "test";
        try (InputStream r = new CharSequenceInputStream(input, UTF_8)) {
            r.read();
            r.mark(0);
            int firstRead = r.read();
            r.read();
            r.reset();
            int resetRead = r.read();
            assertEquals(firstRead, resetRead, "Byte read after reset should match the byte read after mark");
        }
    }

    /**
     * Test to ensure that the fillBuffer() method correctly fills the byte buffer from the character buffer.
     */
    @Test
    public void testFillBuffer() throws Exception {
        final String input = "test";
        try (InputStream r = new CharSequenceInputStream(input, UTF_8)) {
            int initialAvailable = r.available();
            r.read(new byte[initialAvailable]);
            int availableAfterRead = r.available();
            assertTrue(availableAfterRead < initialAvailable, "Available bytes should decrease after reading");
            r.read();
            int availableAfterFill = r.available();
            assertTrue(availableAfterFill > 0, "Available bytes should be greater than zero after filling buffer");
        }
    }
}