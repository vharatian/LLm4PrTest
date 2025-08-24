package org.apache.commons.lang3.concurrent;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testIsInitializedBeforeInitialization() {
        AtomicInitializer<Object> initializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return new Object();
            }
        };
        assertFalse(initializer.isInitialized(), "Initializer should not be initialized before get() is called");
    }

    @Test
    public void testIsInitializedAfterInitialization() throws ConcurrentException {
        AtomicInitializer<Object> initializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return new Object();
            }
        };
        initializer.get();
        assertTrue(initializer.isInitialized(), "Initializer should be initialized after get() is called");
    }

    @Test
    public void testGetReturnsSameInstance() throws ConcurrentException {
        AtomicInitializer<Object> initializer = new AtomicInitializer<Object>() {
            @Override
            protected Object initialize() {
                return new Object();
            }
        };
        Object firstInstance = initializer.get();
        Object secondInstance = initializer.get();
        assertSame(firstInstance, secondInstance, "get() should return the same instance on subsequent calls");
    }
}