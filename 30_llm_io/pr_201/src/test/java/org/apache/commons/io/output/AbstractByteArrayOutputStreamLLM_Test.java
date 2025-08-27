package org.apache.commons.io.output;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractByteArrayOutputStreamLLM_Test {

    // Mock class to test the abstract class
    private static class MockByteArrayOutputStream extends AbstractByteArrayOutputStream {
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
            return toInputStream(ByteArrayInputStream::new);
        }

        @Override
        public byte[] toByteArray() {
            return toByteArrayImpl();
        }
    }

    @Test
    public void testToInputStream() throws IOException {
        MockByteArrayOutputStream stream = new MockByteArrayOutputStream();
        String testString = "Test data";
        stream.write(testString.getBytes(StandardCharsets.UTF_8));
        InputStream inputStream = stream.toInputStream();

        byte[] buffer = new byte[testString.length()];
        int bytesRead = inputStream.read(buffer);
        assertEquals(testString.length(), bytesRead);
        assertArrayEquals(testString.getBytes(StandardCharsets.UTF_8), buffer);
    }
}