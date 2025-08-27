package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.output.QueueOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueueInputStreamLLM_Test {

    @Test
    @DisplayName("If read is interrupted while waiting, then exception is thrown and thread is interrupted")
    public void testReadInterruptedException() throws Exception {
        try (QueueInputStream inputStream = QueueInputStream.builder().setTimeout(Duration.ofMinutes(2)).get();
             QueueOutputStream outputStream = inputStream.newQueueOutputStream()) {
            final AtomicBoolean result = new AtomicBoolean();
            final CountDownLatch latch = new CountDownLatch(1);
            final Thread thread = new Thread(() -> {
                assertThrows(IllegalStateException.class, () -> {
                    try {
                        inputStream.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                assertTrue(Thread.currentThread().isInterrupted());
                result.set(true);
                latch.countDown();
            });
            thread.setDaemon(true);
            thread.start();
            thread.interrupt();
            latch.await(500, TimeUnit.MILLISECONDS);
            assertTrue(result.get());
        }
    }
}