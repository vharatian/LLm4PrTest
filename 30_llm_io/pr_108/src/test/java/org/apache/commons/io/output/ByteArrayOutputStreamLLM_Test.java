package org.apache.commons.io.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ByteArrayOutputStreamLLM_Test {

    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Test
    public void testWriteByteArray() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        assertArrayEquals(data, byteArrayOutputStream.toByteArray());
    }

    @Test
    public void testWriteSingleByte() throws IOException {
        byteArrayOutputStream.write('H');
        byteArrayOutputStream.write('i');
        assertArrayEquals("Hi".getBytes(StandardCharsets.UTF_8), byteArrayOutputStream.toByteArray());
    }

    @Test
    public void testWriteInputStream() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(data);
        byteArrayOutputStream.write(inputStream);
        assertArrayEquals(data, byteArrayOutputStream.toByteArray());
    }

    @Test
    public void testSize() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        assertEquals(data.length, byteArrayOutputStream.size());
    }

    @Test
    public void testReset() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        byteArrayOutputStream.reset();
        assertEquals(0, byteArrayOutputStream.size());
        assertArrayEquals(new byte[0], byteArrayOutputStream.toByteArray());
    }

    @Test
    public void testWriteTo() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        ByteArrayOutputStream anotherStream = new ByteArrayOutputStream();
        byteArrayOutputStream.writeTo(anotherStream);
        assertArrayEquals(data, anotherStream.toByteArray());
    }

    @Test
    public void testToInputStream() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        InputStream inputStream = byteArrayOutputStream.toInputStream();
        byte[] result = new byte[data.length];
        int bytesRead = inputStream.read(result);
        assertEquals(data.length, bytesRead);
        assertArrayEquals(data, result);
    }

    @Test
    public void testToByteArray() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        assertArrayEquals(data, byteArrayOutputStream.toByteArray());
    }

    @Test
    public void testToStringWithCharset() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        assertEquals("Hello, World!", byteArrayOutputStream.toString(StandardCharsets.UTF_8));
    }

    @Test
    public void testToStringWithEncoding() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        byteArrayOutputStream.write(data, 0, data.length);
        assertEquals("Hello, World!", byteArrayOutputStream.toString("UTF-8"));
    }

    @Test
    public void testWriteByteArrayWithInvalidOffsetAndLength() {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        assertThrows(IndexOutOfBoundsException.class, () -> byteArrayOutputStream.write(data, -1, data.length));
        assertThrows(IndexOutOfBoundsException.class, () -> byteArrayOutputStream.write(data, 0, data.length + 1));
        assertThrows(IndexOutOfBoundsException.class, () -> byteArrayOutputStream.write(data, data.length, 1));
    }
}