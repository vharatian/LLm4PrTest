package org.apache.commons.io.output;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.QueueInputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.InterruptedIOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueueOutputStreamLLM_Test {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @AfterAll
    public static void afterAll() {
        executorService.shutdown();
    }

    private static <T> T callInThrowAwayThread(final Callable<T> callable) throws Exception {
        final Exchanger<T> exchanger = new Exchanger<>();
        executorService.submit(() -> {
            final T value = callable.call();
            exchanger.exchange(value);
            return null;
        });
        return exchanger.exchange(null);
    }

    @Test
    public void testNullArgument() {
        assertThrows(NullPointerException.class, () -> new QueueOutputStream(null), "queue is required");
    }

    @Test
    public void writeInterrupted() throws Exception {
        try (final QueueOutputStream outputStream = new QueueOutputStream(new LinkedBlockingQueue<>(1));
             final QueueInputStream inputStream = outputStream.newQueueInputStream()) {
            final int timeout = 1;
            final Exchanger<Thread> writerThreadExchanger = new Exchanger<>();
            final Exchanger<Exception> exceptionExchanger = new Exchanger<>();
            executorService.submit(() -> {
                final Thread writerThread = writerThreadExchanger.exchange(null, timeout, SECONDS);
                writerThread.interrupt();
                return null;
            });
            executorService.submit(() -> {
                try {
                    writerThreadExchanger.exchange(Thread.currentThread(), timeout, SECONDS);
                    outputStream.write("ABC".getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    Thread.interrupted();
                    exceptionExchanger.exchange(e, timeout, SECONDS);
                }
                return null;
            });
            final Exception exception = exceptionExchanger.exchange(null, timeout, SECONDS);
            assertNotNull(exception);
            assertEquals(exception.getClass(), InterruptedIOException.class);
        }
    }

    @Test
    public void writeString() throws Exception {
        try (final QueueOutputStream outputStream = new QueueOutputStream();
             final QueueInputStream inputStream = outputStream.newQueueInputStream()) {
            outputStream.write("ABC".getBytes(UTF_8));
            final String value = IOUtils.toString(inputStream, UTF_8);
            assertEquals("ABC", value);
        }
    }

    @Test
    public void writeStringMultiThread() throws Exception {
        try (final QueueOutputStream outputStream = callInThrowAwayThread(QueueOutputStream::new);
             final QueueInputStream inputStream = callInThrowAwayThread(outputStream::newQueueInputStream)) {
            callInThrowAwayThread(() -> {
                outputStream.write("ABC".getBytes(UTF_8));
                return null;
            });
            final String value = callInThrowAwayThread(() -> IOUtils.toString(inputStream, UTF_8));
            assertEquals("ABC", value);
        }
    }
}