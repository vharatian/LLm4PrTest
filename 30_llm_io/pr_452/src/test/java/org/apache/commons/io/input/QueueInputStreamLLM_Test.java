package org.apache.commons.io.input;

import org.apache.commons.io.output.QueueOutputStream;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class QueueInputStreamLLM_Test {

    @Test
    public void testConstructorWithBlockingQueueAndWaitTime() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Duration waitTime = Duration.ofMillis(100);
        QueueInputStream queueInputStream = new QueueInputStream(queue, waitTime);
        assertNotNull(queueInputStream);
    }

    @Test
    public void testConstructorWithNegativeWaitTime() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Duration waitTime = Duration.ofMillis(-1);
        assertThrows(IllegalArgumentException.class, () -> new QueueInputStream(queue, waitTime));
    }

    @Test
    public void testNewQueueOutputStream() {
        QueueInputStream queueInputStream = new QueueInputStream();
        QueueOutputStream queueOutputStream = queueInputStream.newQueueOutputStream();
        assertNotNull(queueOutputStream);
    }

    @Test
    public void testReadWithWaitTime() throws Exception {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        QueueInputStream queueInputStream = new QueueInputStream(queue, Duration.ofMillis(100));
        queue.put(65); // ASCII value for 'A'
        assertEquals(65, queueInputStream.read());
    }

    @Test
    public void testReadWithTimeout() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        QueueInputStream queueInputStream = new QueueInputStream(queue, Duration.ofMillis(100));
        assertEquals(-1, queueInputStream.read());
    }

    @Test
    public void testReadInterruptedException() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        QueueInputStream queueInputStream = new QueueInputStream(queue, Duration.ofMillis(100));
        Thread.currentThread().interrupt();
        assertThrows(IllegalStateException.class, queueInputStream::read);
        assertTrue(Thread.interrupted()); // Clear the interrupted status
    }
}