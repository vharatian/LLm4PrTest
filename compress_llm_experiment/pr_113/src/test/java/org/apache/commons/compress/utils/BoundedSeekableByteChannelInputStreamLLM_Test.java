package org.apache.commons.compress.utils;

import org.junit.Test;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoundedSeekableByteChannelInputStreamLLM_Test {

    @Test
    public void testReadWithinBounds() throws IOException {
        byte[] readContent;
        try (BoundedSeekableByteChannelInputStream input = new BoundedSeekableByteChannelInputStream(0, 5,
                new SeekableInMemoryByteChannel("Hello World!".getBytes(StandardCharsets.UTF_8)))) {
            readContent = IOUtils.toByteArray(input);
        }
        assertEquals(5, readContent.length);
        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8), readContent);
    }

    @Test
    public void testReadBeyondBounds() throws IOException {
        byte[] readContent;
        try (BoundedSeekableByteChannelInputStream input = new BoundedSeekableByteChannelInputStream(0, 20,
                new SeekableInMemoryByteChannel("Hello World!".getBytes(StandardCharsets.UTF_8)))) {
            readContent = IOUtils.toByteArray(input);
        }
        assertEquals(12, readContent.length);
        assertArrayEquals("Hello World!".getBytes(StandardCharsets.UTF_8), readContent);
    }

    @Test
    public void testReadWithOffset() throws IOException {
        byte[] readContent;
        try (BoundedSeekableByteChannelInputStream input = new BoundedSeekableByteChannelInputStream(6, 5,
                new SeekableInMemoryByteChannel("Hello World!".getBytes(StandardCharsets.UTF_8)))) {
            readContent = IOUtils.toByteArray(input);
        }
        assertEquals(5, readContent.length);
        assertArrayEquals("World".getBytes(StandardCharsets.UTF_8), readContent);
    }

    @Test
    public void testReadEmptyBuffer() throws IOException {
        byte[] readContent;
        try (BoundedSeekableByteChannelInputStream input = new BoundedSeekableByteChannelInputStream(0, 0,
                new SeekableInMemoryByteChannel("Hello World!".getBytes(StandardCharsets.UTF_8)))) {
            readContent = IOUtils.toByteArray(input);
        }
        assertEquals(0, readContent.length);
    }

    @Test
    public void testReadWithByteBuffer() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(5);
        int bytesRead;
        try (BoundedSeekableByteChannelInputStream input = new BoundedSeekableByteChannelInputStream(0, 5,
                new SeekableInMemoryByteChannel("Hello World!".getBytes(StandardCharsets.UTF_8)))) {
            bytesRead = input.read(0, buffer);
        }
        buffer.flip();
        byte[] readContent = new byte[buffer.remaining()];
        buffer.get(readContent);
        assertEquals(5, bytesRead);
        assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8), readContent);
    }

    @Test
    public void testThreadSafety() throws IOException, InterruptedException {
        final SeekableByteChannel channel = new SeekableInMemoryByteChannel("Hello World!".getBytes(StandardCharsets.UTF_8));
        final BoundedSeekableByteChannelInputStream input = new BoundedSeekableByteChannelInputStream(0, 5, channel);

        Runnable readTask = () -> {
            try {
                byte[] readContent = IOUtils.toByteArray(input);
                assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8), readContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread1 = new Thread(readTask);
        Thread thread2 = new Thread(readTask);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}