package org.apache.commons.io.output;

import org.apache.commons.io.input.QueueInputStream;
import org.junit.jupiter.api.Test;

import java.io.InterruptedIOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class QueueOutputStreamLLM_Test {

    @Test
    public void testWriteSingleByte() throws Exception {
        QueueOutputStream outputStream = new QueueOutputStream();
        QueueInputStream inputStream = outputStream.newQueueInputStream();

        outputStream.write(65); // ASCII value for 'A'
        assertEquals(65, inputStream.read());
    }

    @Test
    public void testWriteMultipleBytes() throws Exception {
        QueueOutputStream outputStream = new QueueOutputStream();
        QueueInputStream inputStream = outputStream.newQueueInputStream();

        byte[] data = "hello".getBytes();
        for (byte b : data) {
            outputStream.write(b);
        }

        for (byte b : data) {
            assertEquals(b, inputStream.read());
        }
    }

    @Test
    public void testInterruptedWrite() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>() {
            @Override
            public void put(Integer e) throws InterruptedException {
                throw new InterruptedException("Test interruption");
            }
        };

        QueueOutputStream outputStream = new QueueOutputStream(queue);

        InterruptedIOException exception = assertThrows(InterruptedIOException.class, () -> {
            outputStream.write(65);
        });

        assertEquals("Test interruption", exception.getCause().getMessage());
    }

    @Test
    public void testNewQueueInputStream() {
        QueueOutputStream outputStream = new QueueOutputStream();
        QueueInputStream inputStream = outputStream.newQueueInputStream();

        assertNotNull(inputStream);
    }
}