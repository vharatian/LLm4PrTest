package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class RandomAccessOutputStreamLLM_Test {

    private static class MockRandomAccessOutputStream extends RandomAccessOutputStream {
        private long position = 0;
        private byte[] buffer = new byte[1024];

        @Override
        public long position() {
            return position;
        }

        @Override
        public void writeFullyAt(byte[] b, int off, int len, long position) {
            System.arraycopy(b, off, buffer, (int) position, len);
            this.position = position + len;
        }

        @Override
        public void write(int b) {
            buffer[(int) position] = (byte) b;
            position++;
        }
    }

    @Test
    void testPosition() throws IOException {
        MockRandomAccessOutputStream stream = new MockRandomAccessOutputStream();
        assertEquals(0, stream.position());
        stream.write(1);
        assertEquals(1, stream.position());
    }

    @Test
    void testWriteFullyAt() throws IOException {
        MockRandomAccessOutputStream stream = new MockRandomAccessOutputStream();
        byte[] data = {1, 2, 3, 4};
        stream.writeFullyAt(data, 0);
        assertEquals(4, stream.position());
    }

    @Test
    void testWriteFullyAtWithOffsetAndLength() throws IOException {
        MockRandomAccessOutputStream stream = new MockRandomAccessOutputStream();
        byte[] data = {1, 2, 3, 4, 5, 6};
        stream.writeFullyAt(data, 2, 3, 0);
        assertEquals(3, stream.position());
    }

    @Test
    void testWriteInt() throws IOException {
        MockRandomAccessOutputStream stream = new MockRandomAccessOutputStream();
        stream.write(255);
        assertEquals(1, stream.position());
    }
}