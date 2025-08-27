package org.apache.commons.io.input.buffer;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class PeekableInputStreamLLM_Test {

    @Test
    public void testPeekWithValidBuffer() throws IOException {
        byte[] inputData = "Hello, World!".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputData);
        PeekableInputStream peekableInputStream = new PeekableInputStream(byteArrayInputStream, 1024);

        byte[] peekBuffer = new byte[5];
        assertTrue(peekableInputStream.peek(peekBuffer));
        assertArrayEquals("Hello".getBytes(), peekBuffer);
    }

    @Test
    public void testPeekWithOffsetAndLength() throws IOException {
        byte[] inputData = "Hello, World!".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputData);
        PeekableInputStream peekableInputStream = new PeekableInputStream(byteArrayInputStream, 1024);

        byte[] peekBuffer = new byte[13];
        assertTrue(peekableInputStream.peek(peekBuffer, 7, 6));
        assertArrayEquals(", World".getBytes(), new byte[]{peekBuffer[7], peekBuffer[8], peekBuffer[9], peekBuffer[10], peekBuffer[11], peekBuffer[12]});
    }

    @Test
    public void testPeekWithNullBuffer() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
        PeekableInputStream peekableInputStream = new PeekableInputStream(byteArrayInputStream, 1024);

        assertThrows(NullPointerException.class, () -> peekableInputStream.peek(null));
    }

    @Test
    public void testPeekWithBufferExceedingSize() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
        PeekableInputStream peekableInputStream = new PeekableInputStream(byteArrayInputStream, 5);

        byte[] peekBuffer = new byte[10];
        assertThrows(IllegalArgumentException.class, () -> peekableInputStream.peek(peekBuffer));
    }

    @Test
    public void testPeekWithBufferExceedingSizeWithOffsetAndLength() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
        PeekableInputStream peekableInputStream = new PeekableInputStream(byteArrayInputStream, 5);

        byte[] peekBuffer = new byte[10];
        assertThrows(IllegalArgumentException.class, () -> peekableInputStream.peek(peekBuffer, 0, 10));
    }
}