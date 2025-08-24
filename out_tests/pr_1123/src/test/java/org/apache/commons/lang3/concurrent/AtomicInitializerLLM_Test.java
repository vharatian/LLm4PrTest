package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;
import org.junit.jupiter.api.Test;

public class AtomicInitializerLLM_Test extends AbstractConcurrentInitializerTest {

    @Override
    protected ConcurrentInitializer<Object> createInitializer() {
        return new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return new Object();
            }
        };
    }

    @Test
    public void testBuilderCreatesAtomicInitializer() throws ConcurrentException {
        AtomicInitializer.Builder<AtomicInitializer<Object>, Object> builder = AtomicInitializer.builder();
        AtomicInitializer<Object> initializer = builder.get();
        assertNotNull(initializer);
    }

    @Test
    public void testCustomInitializerAndCloser() throws ConcurrentException {
        FailableSupplier<Object, ConcurrentException> initializer = () -> new Object();
        FailableConsumer<Object, ConcurrentException> closer = obj -> {
            // Custom close logic
        };

        AtomicInitializer<Object> atomicInitializer = new AtomicInitializer<>(initializer, closer);
        assertNotNull(atomicInitializer.get());
    }

    @Test
    public void testGetTypedException() {
        AtomicInitializer<Object> initializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return new Object();
            }
        };

        Exception exception = new Exception("Test Exception");
        ConcurrentException concurrentException = initializer.getTypedException(exception);
        assertTrue(concurrentException instanceof ConcurrentException);
    }
}