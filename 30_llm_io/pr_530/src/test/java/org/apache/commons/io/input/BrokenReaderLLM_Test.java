package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrokenReaderLLM_Test {
    private Throwable exception;
    private Reader brokenReader;

    @BeforeEach
    public void setUp() {
        exception = new RuntimeException("test runtime exception");
        brokenReader = new BrokenReader(exception);
    }

    @Test
    public void testCloseWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.close()));
    }

    @Test
    public void testMarkWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.mark(1)));
    }

    @Test
    public void testReadWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.read()));
    }

    @Test
    public void testReadCharArrayWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.read(new char[1])));
    }

    @Test
    public void testReadCharArrayIndexedWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.read(new char[1], 0, 1)));
    }

    @Test
    public void testReadyWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.ready()));
    }

    @Test
    public void testResetWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.reset()));
    }

    @Test
    public void testSkipWithRuntimeException() {
        assertEquals(exception, assertThrows(RuntimeException.class, () -> brokenReader.skip(1)));
    }

    @Test
    public void testTryWithResourcesWithRuntimeException() {
        final RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            try (Reader newReader = new BrokenReader(exception)) {
                newReader.read();
            }
        });
        assertEquals("test runtime exception", thrown.getMessage());
    }
}