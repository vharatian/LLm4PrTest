package org.apache.commons.lang3.concurrent;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LazyInitializerLLM_Test {

    @Test
    public void testNoInitValue() {
        // Test to ensure NO_INIT is correctly set and used
        LazyInitializer<Object> initializer = new LazyInitializer<>();
        assertFalse(initializer.isInitialized(), "Object should not be initialized");
        assertThrows(ConcurrentException.class, initializer::get, "Should throw ConcurrentException");
    }

    @Test
    public void testInitialization() throws ConcurrentException {
        // Test to ensure object is initialized correctly
        LazyInitializer<String> initializer = new LazyInitializer<>(() -> "initialized", null);
        assertFalse(initializer.isInitialized(), "Object should not be initialized yet");
        String result = initializer.get();
        assertEquals("initialized", result, "Object should be initialized to 'initialized'");
        assertTrue(initializer.isInitialized(), "Object should be initialized");
    }

    @Test
    public void testConcurrentInitialization() throws ConcurrentException {
        // Test to ensure object is initialized only once in a concurrent environment
        LazyInitializer<String> initializer = new LazyInitializer<>(() -> "initialized", null);
        Runnable task = () -> {
            try {
                assertEquals("initialized", initializer.get(), "Object should be initialized to 'initialized'");
            } catch (ConcurrentException e) {
                fail("Initialization failed with ConcurrentException");
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            fail("Threads interrupted");
        }

        assertTrue(initializer.isInitialized(), "Object should be initialized");
    }
}