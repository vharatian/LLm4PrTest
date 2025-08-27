package org.apache.commons.lang3.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LazyInitializerLLM_Test extends AbstractConcurrentInitializerTest {
    private LazyInitializerTestImpl initializer;

    @BeforeEach
    public void setUp() {
        initializer = new LazyInitializerTestImpl();
    }

    @Override
    protected ConcurrentInitializer<Object> createInitializer() {
        return initializer;
    }

    private static class LazyInitializerTestImpl extends LazyInitializer<Object> {
        @Override
        protected Object initialize() {
            return new Object();
        }
    }

    @Test
    public void testObjectInitialization() throws ConcurrentException {
        assertNotNull(initializer.get());
    }

    @Test
    public void testObjectIsSameAfterInitialization() throws ConcurrentException {
        Object firstCall = initializer.get();
        Object secondCall = initializer.get();
        assertSame(firstCall, secondCall);
    }
}