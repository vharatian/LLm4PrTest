package org.apache.commons.io.input.buffer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircularByteBufferLLM_Test {

    private CircularByteBuffer buffer;

    @BeforeEach
    public void setUp() {
        buffer = new CircularByteBuffer();
    }

    @Test
    public void testReadFromEmptyBufferThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> buffer.read());
        assertEquals("No bytes available.", exception.getMessage());
    }

    @Test
    public void testReadMultipleBytesFromEmptyBufferThrowsException() {
        byte[] readBuffer = new byte[10];
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> buffer.read(readBuffer, 0, 10));
        assertEquals("Currently, there are only 0in the buffer, not 10", exception.getMessage());
    }

    @Test
    public void testAddAndReadSingleByte() {
        buffer.add((byte) 1);
        assertEquals(1, buffer.read());
    }

    @Test
    public void testAddAndReadMultipleBytes() {
        byte[] data = {1, 2, 3, 4, 5};
        buffer.add(data, 0, data.length);
        byte[] readBuffer = new byte[5];
        buffer.read(readBuffer, 0, 5);
        assertArrayEquals(data, readBuffer);
    }

    @Test
    public void testPeekBytes() {
        byte[] data = {1, 2, 3, 4, 5};
        buffer.add(data, 0, data.length);
        assertTrue(buffer.peek(data, 0, data.length));
    }

    @Test
    public void testPeekBytesWithDifferentData() {
        byte[] data = {1, 2, 3, 4, 5};
        byte[] differentData = {1, 2, 3, 4, 6};
        buffer.add(data, 0, data.length);
        assertFalse(buffer.peek(differentData, 0, differentData.length));
    }

    @Test
    public void testHasSpace() {
        assertTrue(buffer.hasSpace());
    }

    @Test
    public void testHasSpaceWithBytes() {
        buffer.add((byte) 1);
        assertTrue(buffer.hasSpace(8191));
        assertFalse(buffer.hasSpace(8192));
    }

    @Test
    public void testHasBytes() {
        assertFalse(buffer.hasBytes());
        buffer.add((byte) 1);
        assertTrue(buffer.hasBytes());
    }

    @Test
    public void testGetSpace() {
        assertEquals(8192, buffer.getSpace());
        buffer.add((byte) 1);
        assertEquals(8191, buffer.getSpace());
    }

    @Test
    public void testGetCurrentNumberOfBytes() {
        assertEquals(0, buffer.getCurrentNumberOfBytes());
        buffer.add((byte) 1);
        assertEquals(1, buffer.getCurrentNumberOfBytes());
    }

    @Test
    public void testClear() {
        buffer.add((byte) 1);
        buffer.clear();
        assertEquals(0, buffer.getCurrentNumberOfBytes());
        assertTrue(buffer.hasSpace(8192));
    }
}