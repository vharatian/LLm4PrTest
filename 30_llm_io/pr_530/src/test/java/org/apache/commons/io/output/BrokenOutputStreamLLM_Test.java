package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrokenOutputStreamLLM_Test {

    private Throwable exception;
    private OutputStream stream;

    @BeforeEach
    public void setUp() {
        exception = new RuntimeException("test runtime exception");
        stream = new BrokenOutputStream(exception);
    }

    @Test
    public void testCloseWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> stream.close()));
    }

    @Test
    public void testFlushWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> stream.flush()));
    }

    @Test
    public void testWriteIntWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> stream.write(1)));
    }

    @Test
    public void testWriteByteArrayWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> stream.write(new byte[1])));
    }

    @Test
    public void testWriteByteArrayIndexedWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> stream.write(new byte[1], 0, 1)));
    }

    @Test
    public void testDeprecatedConstructor() {
        IOException ioException = new IOException("deprecated exception");
        OutputStream deprecatedStream = new BrokenOutputStream(ioException);
        assertEquals(ioException, assertThrows(IOException.class, () -> deprecatedStream.close()));
    }
}