package org.apache.commons.io.output;

import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class UnsynchronizedByteArrayOutputStreamLLM_Test {

    @Test
    public void testToInputStream() throws IOException {
        UnsynchronizedByteArrayOutputStream outputStream = new UnsynchronizedByteArrayOutputStream();
        byte[] data = "test data".getBytes();
        outputStream.write(data);
        InputStream inputStream = outputStream.toInputStream();
        assertTrue(inputStream instanceof UnsynchronizedByteArrayInputStream);
        byte[] result = new byte[data.length];
        int bytesRead = inputStream.read(result);
        assertEquals(data.length, bytesRead);
        assertArrayEquals(data, result);
    }

    @Test
    public void testToBufferedInputStream() throws IOException {
        byte[] data = "test data".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        InputStream bufferedInput = UnsynchronizedByteArrayOutputStream.toBufferedInputStream(input);
        assertTrue(bufferedInput instanceof UnsynchronizedByteArrayInputStream);
        byte[] result = new byte[data.length];
        int bytesRead = bufferedInput.read(result);
        assertEquals(data.length, bytesRead);
        assertArrayEquals(data, result);
    }

    @Test
    public void testToBufferedInputStreamWithSize() throws IOException {
        byte[] data = "test data".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        InputStream bufferedInput = UnsynchronizedByteArrayOutputStream.toBufferedInputStream(input, 2048);
        assertTrue(bufferedInput instanceof UnsynchronizedByteArrayInputStream);
        byte[] result = new byte[data.length];
        int bytesRead = bufferedInput.read(result);
        assertEquals(data.length, bytesRead);
        assertArrayEquals(data, result);
    }
}