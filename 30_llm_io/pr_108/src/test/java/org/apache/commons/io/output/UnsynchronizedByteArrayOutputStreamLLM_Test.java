package org.apache.commons.io.output;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static org.junit.jupiter.api.Assertions.*;

public class UnsynchronizedByteArrayOutputStreamLLM_Test {

    @Test
    public void testConstructorWithDefaultSize() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        assertEquals(0, stream.size());
    }

    @Test
    public void testConstructorWithInitialSize() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream(2048);
        assertEquals(0, stream.size());
    }

    @Test
    public void testConstructorWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UnsynchronizedByteArrayOutputStream(-1);
        });
    }

    @Test
    public void testWriteByteArray() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        byte[] data = "hello".getBytes();
        stream.write(data, 0, data.length);
        assertArrayEquals(data, stream.toByteArray());
    }

    @Test
    public void testWriteSingleByte() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        stream.write('a');
        assertArrayEquals(new byte[]{'a'}, stream.toByteArray());
    }

    @Test
    public void testWriteInputStream() throws IOException {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("hello".getBytes());
        stream.write(input);
        assertArrayEquals("hello".getBytes(), stream.toByteArray());
    }

    @Test
    public void testSize() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        stream.write('a');
        assertEquals(1, stream.size());
    }

    @Test
    public void testReset() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        stream.write('a');
        stream.reset();
        assertEquals(0, stream.size());
        assertArrayEquals(new byte[0], stream.toByteArray());
    }

    @Test
    public void testWriteTo() throws IOException {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        stream.write('a');
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        stream.writeTo(out);
        assertArrayEquals(new byte[]{'a'}, out.toByteArray());
    }

    @Test
    public void testToBufferedInputStream() throws IOException {
        InputStream input = new ByteArrayInputStream("hello".getBytes());
        InputStream bufferedInput = UnsynchronizedByteArrayOutputStream.toBufferedInputStream(input);
        byte[] buffer = new byte[5];
        int bytesRead = bufferedInput.read(buffer);
        assertEquals(5, bytesRead);
        assertArrayEquals("hello".getBytes(), buffer);
    }

    @Test
    public void testToBufferedInputStreamWithSize() throws IOException {
        InputStream input = new ByteArrayInputStream("hello".getBytes());
        InputStream bufferedInput = UnsynchronizedByteArrayOutputStream.toBufferedInputStream(input, 2048);
        byte[] buffer = new byte[5];
        int bytesRead = bufferedInput.read(buffer);
        assertEquals(5, bytesRead);
        assertArrayEquals("hello".getBytes(), buffer);
    }

    @Test
    public void testToInputStream() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        stream.write('a');
        InputStream inputStream = stream.toInputStream();
        byte[] buffer = new byte[1];
        try {
            int bytesRead = inputStream.read(buffer);
            assertEquals(1, bytesRead);
            assertArrayEquals(new byte[]{'a'}, buffer);
        } catch (IOException e) {
            fail("IOException should not occur");
        }
    }

    @Test
    public void testToByteArray() {
        UnsynchronizedByteArrayOutputStream stream = new UnsynchronizedByteArrayOutputStream();
        stream.write('a');
        assertArrayEquals(new byte[]{'a'}, stream.toByteArray());
    }
}