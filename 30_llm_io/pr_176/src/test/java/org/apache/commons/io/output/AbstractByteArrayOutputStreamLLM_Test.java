package org.apache.commons.io.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void testNeedNewBufferWithNullCurrentBuffer() {
        stream.needNewBuffer(10);
        assertEquals(10, stream.getCurrentBuffer().length);
        assertEquals(0, stream.getFilledBufferSum());
    }

    @Test
    public void testNeedNewBufferWithNonNullCurrentBuffer() {
        stream.write(new byte[1024], 0, 1024);
        stream.needNewBuffer(2048);
        assertEquals(2048, stream.getCurrentBuffer().length);
        assertEquals(1024, stream.getFilledBufferSum());
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
            return toInputStream(ByteArrayInputStream::new);
        }

        @Override
        public byte[] toByteArray() {
            return toByteArrayImpl();
        }

        public byte[] getCurrentBuffer() {
            return currentBuffer;
        }

        public int getFilledBufferSum() {
            return filledBufferSum;
        }
    }
}