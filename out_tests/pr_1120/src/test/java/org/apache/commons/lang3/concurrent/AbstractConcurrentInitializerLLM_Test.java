package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public abstract class AbstractConcurrentInitializerLLM_Test extends AbstractLangTest {

    @Test
    public void testIsInitializedBeforeInitialization() throws ConcurrentException {
        ConcurrentInitializer<Object> initializer = createInitializer();
        assertFalse(((AbstractConcurrentInitializer<Object, ConcurrentException>) initializer).isInitialized(), "Initializer should not be initialized before calling get()");
    }

    @Test
    public void testIsInitializedAfterInitialization() throws ConcurrentException {
        ConcurrentInitializer<Object> initializer = createInitializer();
        initializer.get();
        assertTrue(((AbstractConcurrentInitializer<Object, ConcurrentException>) initializer).isInitialized(), "Initializer should be initialized after calling get()");
    }

    protected abstract ConcurrentInitializer<Object> createInitializer();
}