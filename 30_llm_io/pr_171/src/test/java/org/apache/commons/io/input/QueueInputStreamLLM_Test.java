package org.apache.commons.io.input;

import org.apache.commons.io.output.QueueOutputStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class QueueInputStreamLLM_Test {

    @Test
    public void testReadSingleByte() throws IOException {
        QueueInputStream inputStream = new QueueInputStream();
        QueueOutputStream outputStream = inputStream.newQueueOutputStream();
        
        outputStream.write(97); // ASCII for 'a'
        assertEquals(97, inputStream.read());
        assertEquals(-1, inputStream.read()); // End of stream
    }

    @Test
    public void testReadFromEmptyStream() {
        QueueInputStream inputStream = new QueueInputStream();
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void testNewQueueOutputStream() throws IOException {
        QueueInputStream inputStream = new QueueInputStream();
        QueueOutputStream outputStream = inputStream.newQueueOutputStream();
        
        outputStream.write(98); // ASCII for 'b'
        assertEquals(98, inputStream.read());
    }

    @Test
    public void testCustomQueue() throws IOException {
        BlockingQueue<Integer> customQueue = new LinkedBlockingQueue<>();
        QueueInputStream inputStream = new QueueInputStream(customQueue);
        QueueOutputStream outputStream = inputStream.newQueueOutputStream();
        
        outputStream.write(99); // ASCII for 'c'
        assertEquals(99, inputStream.read());
    }

    @Test
    public void testNullQueueThrowsException() {
        assertThrows(NullPointerException.class, () -> new QueueInputStream(null));
    }
}