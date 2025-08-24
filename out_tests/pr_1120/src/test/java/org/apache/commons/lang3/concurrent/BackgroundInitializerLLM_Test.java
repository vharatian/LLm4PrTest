package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

public class BackgroundInitializerLLM_Test {

    @Test
    public void testIsInitializedNotStarted() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertFalse(init.isInitialized(), "Not initialized yet");
    }

    @Test
    public void testIsInitializedStartedNotDone() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        assertFalse(init.isInitialized(), "Initialization not completed yet");
    }

    @Test
    public void testIsInitializedCompletedSuccessfully() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        init.get(); // Ensure initialization is completed
        assertTrue(init.isInitialized(), "Initialization completed successfully");
    }

    @Test
    public void testIsInitializedWithExecutionException() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        final Exception ex = new Exception();
        init.ex = ex;
        init.start();
        assertFalse(init.isInitialized(), "Initialization failed with ExecutionException");
    }

    @Test
    public void testIsInitializedWithCancellationException() {
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl(exec);
            init.start();
            Future<Integer> future = init.getFuture();
            future.cancel(true);
            assertFalse(init.isInitialized(), "Initialization failed with CancellationException");
        } finally {
            exec.shutdown();
        }
    }

    @Test
    public void testIsInitializedWithInterruptedException() throws InterruptedException {
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl(exec);
        init.shouldSleep = true;
        init.start();
        final Thread getThread = new Thread(() -> {
            try {
                init.get();
            } catch (final ConcurrentException cex) {
                // Ignore
            }
        });
        getThread.start();
        getThread.interrupt();
        getThread.join();
        assertFalse(init.isInitialized(), "Initialization failed with InterruptedException");
        exec.shutdownNow();
    }

    private static final class BackgroundInitializerTestImpl extends BackgroundInitializer<Integer> {
        Exception ex;
        boolean shouldSleep;
        volatile int initializeCalls;

        BackgroundInitializerTestImpl() {
        }

        BackgroundInitializerTestImpl(final ExecutorService exec) {
            super(exec);
        }

        @Override
        protected Integer initialize() throws Exception {
            if (ex != null) {
                throw ex;
            }
            if (shouldSleep) {
                Thread.sleep(60000);
            }
            return Integer.valueOf(++initializeCalls);
        }
    }
}