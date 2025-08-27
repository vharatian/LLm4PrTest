package org.apache.commons.io.output;

import org.junit.jupiter.api.Test;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractByteArrayOutputStreamLLM_Test {

    @Test
    public void testToStringWithCharset() {
        AbstractByteArrayOutputStream stream = new ConcreteByteArrayOutputStream();
        stream.write(new byte[]{65, 66, 67, 68}, 0, 4);
        String result = stream.toString(StandardCharsets.UTF_8);
        assertEquals("ABCD", result);
    }

    @Test
    public void testToStringWithEncoding() throws UnsupportedEncodingException {
        AbstractByteArrayOutputStream stream = new ConcreteByteArrayOutputStream();
        stream.write(new byte[]{65, 66, 67, 68}, 0, 4);
        String result = stream.toString("UTF-8");
        assertEquals("ABCD", result);
    }

    // Concrete implementation for testing purposes
    private static class ConcreteByteArrayOutputStream extends AbstractByteArrayOutputStream {
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
}