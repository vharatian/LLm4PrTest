package org.apache.commons.io.input.buffer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircularBufferInputStreamLLM_Test {

    private final Random rnd = new Random(1530960934483l);

    @Test
    public void testConstructorWithNegativeBufferSize() {
        try {
            new CircularBufferInputStream(new ByteArrayInputStream(new byte[10]), -1);
            fail("Expected IllegalArgumentException for negative buffer size");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid buffer size: -1", e.getMessage());
        }
    }

    @Test
    public void testConstructorWithZeroBufferSize() {
        try {
            new CircularBufferInputStream(new ByteArrayInputStream(new byte[10]), 0);
            fail("Expected IllegalArgumentException for zero buffer size");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid buffer size: 0", e.getMessage());
        }
    }

    @Test
    public void testConstructorWithNullInputStream() {
        try {
            new CircularBufferInputStream(null, 10);
            fail("Expected NullPointerException for null InputStream");
        } catch (NullPointerException e) {
            assertEquals("InputStream", e.getMessage());
        }
    }

    @Test
    public void testReadWithNegativeOffset() {
        CircularBufferInputStream cbis = new CircularBufferInputStream(new ByteArrayInputStream(new byte[10]), 10);
        try {
            cbis.read(new byte[10], -1, 5);
            fail("Expected IllegalArgumentException for negative offset");
        } catch (IllegalArgumentException e) {
            assertEquals("Offset must not be negative", e.getMessage());
        }
    }

    @Test
    public void testReadWithNegativeLength() {
        CircularBufferInputStream cbis = new CircularBufferInputStream(new ByteArrayInputStream(new byte[10]), 10);
        try {
            cbis.read(new byte[10], 0, -1);
            fail("Expected IllegalArgumentException for negative length");
        } catch (IllegalArgumentException e) {
            assertEquals("Length must not be negative", e.getMessage());
        }
    }

    @Test
    public void testFillBuffer() throws IOException {
        byte[] inputBuffer = new byte[10];
        for (int i = 0; i < inputBuffer.length; i++) {
            inputBuffer[i] = (byte) i;
        }
        CircularBufferInputStream cbis = new CircularBufferInputStream(new ByteArrayInputStream(inputBuffer), 5);
        cbis.fillBuffer();
        assertTrue(cbis.haveBytes(5));
    }

    @Test
    public void testHaveBytes() throws IOException {
        byte[] inputBuffer = new byte[10];
        for (int i = 0; i < inputBuffer.length; i++) {
            inputBuffer[i] = (byte) i;
        }
        CircularBufferInputStream cbis = new CircularBufferInputStream(new ByteArrayInputStream(inputBuffer), 5);
        assertTrue(cbis.haveBytes(1));
        assertFalse(cbis.haveBytes(11));
    }

    @Test
    public void testClose() throws IOException {
        byte[] inputBuffer = new byte[10];
        CircularBufferInputStream cbis = new CircularBufferInputStream(new ByteArrayInputStream(inputBuffer), 5);
        cbis.close();
        assertEquals(-1, cbis.read());
    }
}