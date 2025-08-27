package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailableLLM_Test {

    @Test
    public void testRethrowWithRuntimeException() {
        RuntimeException runtimeException = new RuntimeException("Test RuntimeException");
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            throw Failable.rethrow(runtimeException);
        });
        assertEquals(runtimeException, thrown);
    }

    @Test
    public void testRethrowWithError() {
        Error error = new Error("Test Error");
        Error thrown = assertThrows(Error.class, () -> {
            throw Failable.rethrow(error);
        });
        assertEquals(error, thrown);
    }

    @Test
    public void testRethrowWithIOException() {
        IOException ioException = new IOException("Test IOException");
        UncheckedIOException thrown = assertThrows(UncheckedIOException.class, () -> {
            throw Failable.rethrow(ioException);
        });
        assertEquals(ioException, thrown.getCause());
    }

    @Test
    public void testRethrowWithOtherThrowable() {
        Throwable throwable = new Throwable("Test Throwable");
        UndeclaredThrowableException thrown = assertThrows(UndeclaredThrowableException.class, () -> {
            throw Failable.rethrow(throwable);
        });
        assertEquals(throwable, thrown.getCause());
    }

    @Test
    public void testTryWithResources() {
        FailableRunnable<Throwable> action = () -> {
            // Action that does nothing
        };
        FailableRunnable<Throwable> resource1 = () -> {
            // Resource that does nothing
        };
        FailableRunnable<Throwable> resource2 = () -> {
            // Resource that does nothing
        };

        assertDoesNotThrow(() -> Failable.tryWithResources(action, resource1, resource2));
    }

    @Test
    public void testTryWithResourcesWithException() {
        FailableRunnable<Throwable> action = () -> {
            throw new IOException("Action failed");
        };
        FailableRunnable<Throwable> resource1 = () -> {
            // Resource that does nothing
        };
        FailableRunnable<Throwable> resource2 = () -> {
            // Resource that does nothing
        };

        IOException thrown = assertThrows(IOException.class, () -> {
            Failable.tryWithResources(action, resource1, resource2);
        });
        assertEquals("Action failed", thrown.getMessage());
    }

    @Test
    public void testTryWithResourcesWithResourceException() {
        FailableRunnable<Throwable> action = () -> {
            // Action that does nothing
        };
        FailableRunnable<Throwable> resource1 = () -> {
            throw new IOException("Resource1 failed");
        };
        FailableRunnable<Throwable> resource2 = () -> {
            throw new IOException("Resource2 failed");
        };

        IOException thrown = assertThrows(IOException.class, () -> {
            Failable.tryWithResources(action, resource1, resource2);
        });
        assertEquals("Resource1 failed", thrown.getMessage());
    }
}