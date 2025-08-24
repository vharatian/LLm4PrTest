package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiBackgroundInitializerLLM_Test {
    private static final String CHILD_INIT = "childInitializer";
    private MultiBackgroundInitializer initializer;

    @BeforeEach
    public void setUp() {
        initializer = new MultiBackgroundInitializer();
    }

    @Test
    public void testIsInitializedNoChildren() {
        assertFalse(initializer.isInitialized(), "isInitialized should return false when there are no child initializers");
    }

    @Test
    public void testIsInitializedWithChildren() throws ConcurrentException {
        initializer.addInitializer(CHILD_INIT, new ChildBackgroundInitializer());
        assertFalse(initializer.isInitialized(), "isInitialized should return false before start is called");

        initializer.start();
        initializer.get();
        assertTrue(initializer.isInitialized(), "isInitialized should return true after initialization");
    }

    @Test
    public void testIsInitializedWithExternalExecutor() throws ConcurrentException, InterruptedException {
        final ExecutorService exec = Executors.newCachedThreadPool();
        try {
            initializer = new MultiBackgroundInitializer(exec);
            initializer.addInitializer(CHILD_INIT, new ChildBackgroundInitializer());
            assertFalse(initializer.isInitialized(), "isInitialized should return false before start is called");

            initializer.start();
            initializer.get();
            assertTrue(initializer.isInitialized(), "isInitialized should return true after initialization");
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    private static final class ChildBackgroundInitializer extends BackgroundInitializer<Integer> {
        volatile ExecutorService currentExecutor;
        volatile int initializeCalls;
        Exception ex;

        @Override
        protected Integer initialize() throws Exception {
            currentExecutor = getActiveExecutor();
            initializeCalls++;
            if (ex != null) {
                throw ex;
            }
            return Integer.valueOf(initializeCalls);
        }
    }
}