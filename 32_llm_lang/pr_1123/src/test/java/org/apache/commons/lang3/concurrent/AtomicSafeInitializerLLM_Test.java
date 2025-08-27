package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;
import org.junit.jupiter.api.Test;

public class AtomicSafeInitializerLLM_Test {

    @Test
    public void testBuilderCreatesInstance() throws ConcurrentException {
        AtomicSafeInitializer<String> initializer = AtomicSafeInitializer.<String>builder()
            .initializer(() -> "test")
            .build();
        assertNotNull(initializer);
        assertEquals("test", initializer.get());
    }

    @Test
    public void testBuilderWithCloser() throws ConcurrentException {
        AtomicSafeInitializer<String> initializer = AtomicSafeInitializer.<String>builder()
            .initializer(() -> "test")
            .closer(value -> {
                // Closer logic here
            })
            .build();
        assertNotNull(initializer);
        assertEquals("test", initializer.get());
    }

    @Test
    public void testGetTypedException() {
        AtomicSafeInitializer<String> initializer = new AtomicSafeInitializer<String>() {
            @Override
            protected String initialize() throws ConcurrentException {
                throw new RuntimeException("Test exception");
            }
        };
        Exception exception = assertThrows(ConcurrentException.class, initializer::get);
        assertEquals("Test exception", exception.getCause().getMessage());
    }

    @Test
    public void testCustomInitializerAndCloser() throws ConcurrentException {
        FailableSupplier<String, ConcurrentException> initializer = () -> "initialized";
        FailableConsumer<String, ConcurrentException> closer = value -> {
            // Custom close logic
        };

        AtomicSafeInitializer<String> atomicSafeInitializer = new AtomicSafeInitializer<>(initializer, closer);
        assertEquals("initialized", atomicSafeInitializer.get());
    }
}