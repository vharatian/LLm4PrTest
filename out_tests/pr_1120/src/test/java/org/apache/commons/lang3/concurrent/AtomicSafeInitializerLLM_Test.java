package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AtomicSafeInitializerLLM_Test extends AbstractConcurrentInitializerTest {
    private AtomicSafeInitializerTestImpl initializer;

    @BeforeEach
    public void setUp() {
        initializer = new AtomicSafeInitializerTestImpl();
    }

    @Override
    protected ConcurrentInitializer<Object> createInitializer() {
        return initializer;
    }

    /**
     * Test to ensure that the isInitialized method returns false before initialization
     * and true after initialization.
     */
    @Test
    public void testIsInitialized() throws ConcurrentException {
        assertFalse(initializer.isInitialized(), "Initializer should not be initialized initially");
        initializer.get();
        assertTrue(initializer.isInitialized(), "Initializer should be initialized after get() call");
    }

    private static final class AtomicSafeInitializerTestImpl extends AtomicSafeInitializer<Object> {
        final AtomicInteger initCounter = new AtomicInteger();

        @Override
        protected Object initialize() {
            initCounter.incrementAndGet();
            return new Object();
        }
    }
}