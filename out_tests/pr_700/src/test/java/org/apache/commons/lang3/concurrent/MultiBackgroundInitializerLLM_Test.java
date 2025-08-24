package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiBackgroundInitializerLLM_Test {

    private MultiBackgroundInitializer initializer;

    @BeforeEach
    public void setUp() {
        initializer = new MultiBackgroundInitializer();
    }

    /**
     * Test to ensure that the final keyword on the 'inits' variable does not affect the functionality.
     */
    @Test
    public void testInitializeWithFinalInits() throws Exception {
        final int count = 3;
        for (int i = 0; i < count; i++) {
            initializer.addInitializer("child" + i, new ChildBackgroundInitializer());
        }
        initializer.start();
        MultiBackgroundInitializer.MultiBackgroundInitializerResults results = initializer.get();

        assertEquals(count, results.initializerNames().size(), "Wrong number of child initializers");
        for (int i = 0; i < count; i++) {
            assertEquals(Integer.valueOf(1), results.getResultObject("child" + i), "Wrong result object");
        }
    }

    /**
     * Test to ensure that the final keyword on the 'inits' variable does not affect the functionality
     * when using an external executor.
     */
    @Test
    public void testInitializeWithFinalInitsExternalExecutor() throws Exception {
        final ExecutorService exec = Executors.newCachedThreadPool();
        try {
            initializer = new MultiBackgroundInitializer(exec);
            final int count = 3;
            for (int i = 0; i < count; i++) {
                initializer.addInitializer("child" + i, new ChildBackgroundInitializer());
            }
            initializer.start();
            MultiBackgroundInitializer.MultiBackgroundInitializerResults results = initializer.get();

            assertEquals(count, results.initializerNames().size(), "Wrong number of child initializers");
            for (int i = 0; i < count; i++) {
                assertEquals(Integer.valueOf(1), results.getResultObject("child" + i), "Wrong result object");
            }
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    private static class ChildBackgroundInitializer extends BackgroundInitializer<Integer> {
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