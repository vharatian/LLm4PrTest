package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

public class BackgroundInitializerLLM_Test {

    private void checkInitialize(final BackgroundInitializerTestImpl init) throws ConcurrentException {
        final Integer result = init.get();
        assertEquals(1, result.intValue(), "Wrong result");
        assertEquals(1, init.initializeCalls, "Wrong number of invocations");
        assertNotNull(init.getFuture(), "No future");
    }

    @Test
    public void testInitialize() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        checkInitialize(init);
    }

    @Test
    public void testGetActiveExecutorBeforeStart() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertNull(init.getActiveExecutor(), "Got an executor");
    }

    @Test
    public void testGetActiveExecutorExternal() throws InterruptedException, ConcurrentException {
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl(exec);
            init.start();
            assertSame(exec, init.getActiveExecutor(), "Wrong executor");
            checkInitialize(init);
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    @Test
    public void testGetActiveExecutorTemp() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        assertNotNull(init.getActiveExecutor(), "No active executor");
        checkInitialize(init);
    }

    @Test
    public void testInitializeTempExecutor() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertTrue(init.start(), "Wrong result of start()");
        checkInitialize(init);
        assertTrue(init.getActiveExecutor().isShutdown(), "Executor not shutdown");
    }

    @Test
    public void testSetExternalExecutor() throws ConcurrentException {
        final ExecutorService exec = Executors.newCachedThreadPool();
        try {
            final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
            init.setExternalExecutor(exec);
            assertEquals(exec, init.getExternalExecutor(), "Wrong executor service");
            assertTrue(init.start(), "Wrong result of start()");
            assertSame(exec, init.getActiveExecutor(), "Wrong active executor");
            checkInitialize(init);
            assertFalse(exec.isShutdown(), "Executor was shutdown");
        } finally {
            exec.shutdown();
        }
    }

    @Test
    public void testSetExternalExecutorAfterStart() throws ConcurrentException, InterruptedException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            assertThrows(IllegalStateException.class, () -> init.setExternalExecutor(exec));
            init.get();
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    @Test
    public void testStartMultipleTimes() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertTrue(init.start(), "Wrong result for start()");
        for (int i = 0; i < 10; i++) {
            assertFalse(init.start(), "Could start again");
        }
        checkInitialize(init);
    }

    @Test
    public void testGetBeforeStart() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertThrows(IllegalStateException.class, init::get);
    }

    @Test
    public void testGetRuntimeException() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        final RuntimeException rex = new RuntimeException();
        init.ex = rex;
        init.start();
        final Exception ex = assertThrows(Exception.class, init::get);
        assertEquals(rex, ex, "Runtime exception not thrown");
    }

    @Test
    public void testGetCheckedException() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        final Exception ex = new Exception();
        init.ex = ex;
        init.start();
        final ConcurrentException cex = assertThrows(ConcurrentException.class, init::get);
        assertEquals(ex, cex.getCause(), "Exception not thrown");
    }

    @Test
    public void testGetInterruptedException() throws InterruptedException {
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl(exec);
        final CountDownLatch latch1 = new CountDownLatch(1);
        init.shouldSleep = true;
        init.start();
        final AtomicReference<InterruptedException> iex = new AtomicReference<>();
        final Thread getThread = new Thread() {
            @Override
            public void run() {
                try {
                    init.get();
                } catch (final ConcurrentException cex) {
                    if (cex.getCause() instanceof InterruptedException) {
                        iex.set((InterruptedException) cex.getCause());
                    }
                } finally {
                    assertTrue(isInterrupted(), "Thread not interrupted");
                    latch1.countDown();
                }
            }
        };
        getThread.start();
        getThread.interrupt();
        latch1.await();
        exec.shutdownNow();
        exec.awaitTermination(1, TimeUnit.SECONDS);
        assertNotNull(iex.get(), "No interrupted exception");
    }

    @Test
    public void testIsStartedFalse() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertFalse(init.isStarted(), "Already started");
    }

    @Test
    public void testIsStartedTrue() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        assertTrue(init.isStarted(), "Not started");
    }

    @Test
    public void testIsStartedAfterGet() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        checkInitialize(init);
        assertTrue(init.isStarted(), "Not started");
    }

    private static class BackgroundInitializerTestImpl extends BackgroundInitializer<Integer> {
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
                Thread.sleep(60000L);
            }
            return Integer.valueOf(++initializeCalls);
        }
    }
}