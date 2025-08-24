package org.apache.commons.compress.utils;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoundedInputStreamLLM_Test {

    @Test
    public void testSkipWithinBounds() throws IOException {
        byte[] data = new byte[10];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BoundedInputStream boundedInputStream = new BoundedInputStream(byteArrayInputStream, 5);

        long skipped = boundedInputStream.skip(3);
        assertEquals(3, skipped);
        assertEquals(2, boundedInputStream.bytesRemaining);
    }

    @Test
    public void testSkipBeyondBounds() throws IOException {
        byte[] data = new byte[10];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BoundedInputStream boundedInputStream = new BoundedInputStream(byteArrayInputStream, 5);

        long skipped = boundedInputStream.skip(10);
        assertEquals(5, skipped);
        assertEquals(0, boundedInputStream.bytesRemaining);
    }

    @Test
    public void testSkipZeroBytes() throws IOException {
        byte[] data = new byte[10];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BoundedInputStream boundedInputStream = new BoundedInputStream(byteArrayInputStream, 5);

        long skipped = boundedInputStream.skip(0);
        assertEquals(0, skipped);
        assertEquals(5, boundedInputStream.bytesRemaining);
    }

    @Test
    public void testSkipNegativeBytes() {
        byte[] data = new byte[10];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BoundedInputStream boundedInputStream = new BoundedInputStream(byteArrayInputStream, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            boundedInputStream.skip(-1);
        });
    }
}