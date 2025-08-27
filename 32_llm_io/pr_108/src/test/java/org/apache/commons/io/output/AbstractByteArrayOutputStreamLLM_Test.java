package org.apache.commons.io.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractByteArrayOutputStreamLLM_Test {

    private AbstractByteArrayOutputStreamTestImpl stream;

    @BeforeEach
    public void setUp() {
        stream = new AbstractByteArrayOutputStreamTestImpl();
    }

    @Test
    public void testWriteByteArray() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        assertArrayEquals(data, stream.toByteArray());
    }

    @Test
    public void testWriteSingleByte() throws IOException {
        stream.write('H');
        stream.write('i');
        assertArrayEquals("Hi".getBytes(StandardCharsets.UTF_8), stream.toByteArray());
    }

    @Test
    public void testWriteInputStream() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(data);
        int bytesRead = stream.write(inputStream);
        assertEquals(data.length, bytesRead);
        assertArrayEquals(data, stream.toByteArray());
    }

    @Test
    public void testSize() {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        assertEquals(data.length, stream.size());
    }

    @Test
    public void testReset() {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        stream.reset();
        assertEquals(0, stream.size());
        assertArrayEquals(new byte[0], stream.toByteArray());
    }

    @Test
    public void testWriteTo() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        stream.writeTo(outputStream);
        assertArrayEquals(data, outputStream.toByteArray());
    }

    @Test
    public void testToInputStream() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        InputStream inputStream = stream.toInputStream();
        byte[] result = new byte[data.length];
        int bytesRead = inputStream.read(result);
        assertEquals(data.length, bytesRead);
        assertArrayEquals(data, result);
    }

    @Test
    public void testToStringDefaultCharset() {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        assertEquals("Hello, World!", stream.toString());
    }

    @Test
    public void testToStringWithCharset() {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        assertEquals("Hello, World!", stream.toString(StandardCharsets.UTF_8));
    }

    @Test
    public void testToStringWithEncoding() throws IOException {
        byte[] data = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        stream.write(data, 0, data.length);
        assertEquals("Hello, World!", stream.toString("UTF-8"));
    }

    private static class AbstractByteArrayOutputStreamTestImpl extends AbstractByteArrayOutputStream {

        @Override
        public void write(byte[] b, int off, int len) {
            writeImpl(b, off, len);
        }

        @Override
        public void write(int b) {
            writeImpl(b);
        }

        @Override
        public int write(InputStream in) throws IOException {
            return writeImpl(in);
        }

        @Override
        public int size() {
            return count;
        }

        @Override
        public void reset() {
            resetImpl();
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            writeToImpl(out);
        }

        @Override
        public InputStream toInputStream() {
            return toInputStreamImpl();
        }

        @Override
        public byte[] toByteArray() {
            return toByteArrayImpl();
        }
    }
}