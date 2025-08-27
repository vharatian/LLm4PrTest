package org.apache.commons.io.input;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class UnsynchronizedByteArrayInputStreamLLM_Test {

    @Test
    public void testConstructorWithData() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        assertEquals(3, stream.available());
    }

    @Test
    public void testConstructorWithDataAndOffset() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data, 1);
        assertEquals(2, stream.available());
    }

    @Test
    public void testConstructorWithDataOffsetAndLength() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data, 1, 1);
        assertEquals(1, stream.available());
    }

    @Test
    public void testReadSingleByte() throws IOException {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        assertEquals(1, stream.read());
        assertEquals(2, stream.read());
        assertEquals(3, stream.read());
        assertEquals(-1, stream.read());
    }

    @Test
    public void testReadIntoByteArray() throws IOException {
        byte[] data = {1, 2, 3};
        byte[] buffer = new byte[2];
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        int bytesRead = stream.read(buffer);
        assertEquals(2, bytesRead);
        assertArrayEquals(new byte[]{1, 2}, buffer);
    }

    @Test
    public void testReadIntoByteArrayWithOffsetAndLength() throws IOException {
        byte[] data = {1, 2, 3};
        byte[] buffer = new byte[2];
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        int bytesRead = stream.read(buffer, 0, 2);
        assertEquals(2, bytesRead);
        assertArrayEquals(new byte[]{1, 2}, buffer);
    }

    @Test
    public void testSkip() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        long skipped = stream.skip(2);
        assertEquals(2, skipped);
        assertEquals(3, stream.read());
    }

    @Test
    public void testMarkAndReset() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        stream.read();
        stream.mark(0);
        stream.read();
        stream.reset();
        assertEquals(2, stream.read());
    }

    @Test
    public void testAvailable() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        assertEquals(3, stream.available());
        stream.read();
        assertEquals(2, stream.available());
    }

    @Test
    public void testMarkSupported() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        assertTrue(stream.markSupported());
    }

    @Test
    public void testConstructorWithNegativeOffset() {
        byte[] data = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> new UnsynchronizedByteArrayInputStream(data, -1));
    }

    @Test
    public void testConstructorWithNegativeLength() {
        byte[] data = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> new UnsynchronizedByteArrayInputStream(data, 0, -1));
    }

    @Test
    public void testSkipNegative() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        assertThrows(IllegalArgumentException.class, () -> stream.skip(-1));
    }

    @Test
    public void testReadWithNullByteArray() {
        byte[] data = {1, 2, 3};
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        assertThrows(NullPointerException.class, () -> stream.read(null));
    }

    @Test
    public void testReadWithInvalidOffsetAndLength() {
        byte[] data = {1, 2, 3};
        byte[] buffer = new byte[2];
        UnsynchronizedByteArrayInputStream stream = new UnsynchronizedByteArrayInputStream(data);
        assertThrows(IndexOutOfBoundsException.class, () -> stream.read(buffer, -1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> stream.read(buffer, 0, 3));
    }
}