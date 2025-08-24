package org.apache.commons.lang3;

import org.apache.commons.lang3.Functions.FailableConsumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FunctionsLLM_Test {

    @Test
    @DisplayName("Test rethrow with null Throwable")
    void testRethrowWithNullThrowable() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> Functions.rethrow(null));
        assertEquals("The Throwable must not be null.", e.getMessage());
    }

    @Test
    @DisplayName("Test rethrow with RuntimeException")
    void testRethrowWithRuntimeException() {
        RuntimeException runtimeException = new RuntimeException("Runtime exception");
        RuntimeException e = assertThrows(RuntimeException.class, () -> Functions.rethrow(runtimeException));
        assertSame(runtimeException, e);
    }

    @Test
    @DisplayName("Test rethrow with Error")
    void testRethrowWithError() {
        Error error = new OutOfMemoryError("Out of memory");
        Error e = assertThrows(Error.class, () -> Functions.rethrow(error));
        assertSame(error, e);
    }

    @Test
    @DisplayName("Test rethrow with IOException")
    void testRethrowWithIOException() {
        IOException ioException = new IOException("I/O error");
        UncheckedIOException e = assertThrows(UncheckedIOException.class, () -> Functions.rethrow(ioException));
        assertSame(ioException, e.getCause());
    }

    @Test
    @DisplayName("Test rethrow with other Throwable")
    void testRethrowWithOtherThrowable() {
        Throwable throwable = new Throwable("Other throwable");
        UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, () -> Functions.rethrow(throwable));
        assertSame(throwable, e.getCause());
    }
}