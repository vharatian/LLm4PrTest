package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CloseShieldOutputStreamLLM_Test {

    private ByteArrayOutputStream original;
    private OutputStream shielded;
    private boolean closed;

    @BeforeEach
    public void setUp() {
        original = new ByteArrayOutputStream() {
            @Override
            public void close() {
                closed = true;
            }
        };
        shielded = new CloseShieldOutputStream(original);
        closed = false;
    }

    @Test
    public void testWrap() throws IOException {
        OutputStream wrapped = CloseShieldOutputStream.wrap(original);
        wrapped.close();
        assertFalse(closed, "closed");
        try {
            wrapped.write('x');
            fail("write(b)");
        } catch (final IOException ignore) {
        }
        original.write('y');
        assertEquals(1, original.size());
        assertEquals('y', original.toByteArray()[0]);
    }
}