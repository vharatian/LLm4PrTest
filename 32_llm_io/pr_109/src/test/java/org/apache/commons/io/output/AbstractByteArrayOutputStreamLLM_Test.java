package org.apache.commons.io.output;

import org.apache.commons.io.input.ClosedInputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractByteArrayOutputStreamLLM_Test {

    @Test
    public void testToInputStreamImplWithCustomConstructor() {
        AbstractByteArrayOutputStream stream = new AbstractByteArrayOutputStream() {
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
                return toInputStreamImpl(ByteArrayInputStream::new);
            }

            @Override
            public byte[] toByteArray() {
                return toByteArrayImpl();
            }
        };

        byte[] data = "Hello, World!".getBytes();
        stream.write(data, 0, data.length);

        InputStream inputStream = stream.toInputStreamImpl(ByteArrayInputStream::new);
        assertTrue(inputStream instanceof SequenceInputStream);

        List<ByteArrayInputStream> list = new ArrayList<>();
        list.add(new ByteArrayInputStream(data));
        InputStream expectedStream = new SequenceInputStream(Collections.enumeration(list));

        assertEquals(expectedStream.read(), inputStream.read());
    }

    @Test
    public void testToInputStreamImplWithEmptyStream() {
        AbstractByteArrayOutputStream stream = new AbstractByteArrayOutputStream() {
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
                return toInputStreamImpl(ByteArrayInputStream::new);
            }

            @Override
            public byte[] toByteArray() {
                return toByteArrayImpl();
            }
        };

        InputStream inputStream = stream.toInputStreamImpl(ByteArrayInputStream::new);
        assertTrue(inputStream instanceof ClosedInputStream);
    }
}