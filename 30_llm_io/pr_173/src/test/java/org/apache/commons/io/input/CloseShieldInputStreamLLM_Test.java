package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CloseShieldInputStreamLLM_Test {

    private byte[] data;
    private InputStream original;
    private InputStream shielded;
    private boolean closed;

    @BeforeEach
    public void setUp() {
        data = new byte[] { 'x', 'y', 'z' };
        original = new ByteArrayInputStream(data) {
            @Override
            public void close() {
                closed = true;
            }
        };
        shielded = new CloseShieldInputStream(original);
        closed = false;
    }

    @Test
    public void testWrap() throws IOException {
        InputStream wrappedStream = CloseShieldInputStream.wrap(original);
        assertNotNull(wrappedStream, "Wrapped stream should not be null");
        wrappedStream.close();
        assertFalse(closed, "Original stream should not be closed");
        assertEquals(-1, wrappedStream.read(), "Wrapped stream should return -1 after close");
        assertEquals(data[0], original.read(), "Original stream should still be readable");
    }
}