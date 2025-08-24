package org.apache.commons.lang3.concurrent;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LazyInitializerLLM_Test {

    @Test
    public void testBuilderCreatesLazyInitializer() throws ConcurrentException {
        LazyInitializer<String> initializer = LazyInitializer.<String>builder().get();
        assertNotNull(initializer);
        assertFalse(initializer.isInitialized());
    }

    @Test
    public void testLazyInitializerWithCustomInitializer() throws ConcurrentException {
        FailableSupplier<String, ConcurrentException> supplier = () -> "Test";
        LazyInitializer<String> initializer = new LazyInitializer<>(supplier, null);
        assertFalse(initializer.isInitialized());
        assertEquals("Test", initializer.get());
        assertTrue(initializer.isInitialized());
    }

    @Test
    public void testLazyInitializerWithCustomCloser() throws ConcurrentException {
        FailableSupplier<String, ConcurrentException> supplier = () -> "Test";
        FailableConsumer<String, ConcurrentException> closer = (s) -> {};
        LazyInitializer<String> initializer = new LazyInitializer<>(supplier, closer);
        assertFalse(initializer.isInitialized());
        assertEquals("Test", initializer.get());
        assertTrue(initializer.isInitialized());
    }

    @Test
    public void testGetTypedException() {
        LazyInitializer<String> initializer = new LazyInitializer<>();
        ConcurrentException exception = initializer.getTypedException(new Exception("Test Exception"));
        assertNotNull(exception);
        assertEquals("Test Exception", exception.getCause().getMessage());
    }
}