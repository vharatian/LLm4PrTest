package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrokenWriterLLM_Test {

    private Throwable exception;
    private Writer brokenWriter;

    @BeforeEach
    public void setUp() {
        exception = new RuntimeException("test runtime exception");
        brokenWriter = new BrokenWriter(exception);
    }

    @Test
    public void testCloseWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenWriter.close()));
    }

    @Test
    public void testFlushWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenWriter.flush()));
    }

    @Test
    public void testWriteCharArrayWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenWriter.write(new char[1])));
    }

    @Test
    public void testWriteCharArrayIndexedWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenWriter.write(new char[1], 0, 1)));
    }

    @Test
    public void testWriteIntWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenWriter.write(1)));
    }

    @Test
    public void testWriteStringWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenWriter.write("01")));
    }

    @Test
    public void testWriteStringIndexedWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenWriter.write("01", 0, 1)));
    }

    @Test
    public void testTryWithResourcesWithRuntimeException() {
        final RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            try (Writer newWriter = new BrokenWriter(exception)) {
                newWriter.write(1);
            }
        });
        assertEquals("test runtime exception", thrown.getMessage());
    }
}