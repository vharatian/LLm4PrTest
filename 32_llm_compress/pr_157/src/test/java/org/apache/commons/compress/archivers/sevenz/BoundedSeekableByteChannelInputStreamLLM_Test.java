package org.apache.commons.compress.archivers.sevenz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoundedSeekableByteChannelInputStreamLLM_Test {

    private SeekableByteChannel mockChannel;
    private BoundedSeekableByteChannelInputStream inputStream;

    @BeforeEach
    void setUp() {
        mockChannel = mock(SeekableByteChannel.class);
    }

    @Test
    void testReadWithBytesRemaining() throws IOException {
        byte[] data = {1, 2, 3, 4, 5};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        when(mockChannel.read(any(ByteBuffer.class))).thenAnswer(invocation -> {
            ByteBuffer buf = invocation.getArgument(0);
            buf.put(data);
            return data.length;
        });

        inputStream = new BoundedSeekableByteChannelInputStream(mockChannel, data.length);

        byte[] readBuffer = new byte[5];
        int bytesRead = inputStream.read(readBuffer, 0, readBuffer.length);

        assertEquals(5, bytesRead);
        assertArrayEquals(data, readBuffer);
    }

    @Test
    void testReadWithBytesRemainingLessThanBufferCapacity() throws IOException {
        byte[] data = {1, 2, 3};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        when(mockChannel.read(any(ByteBuffer.class))).thenAnswer(invocation -> {
            ByteBuffer buf = invocation.getArgument(0);
            buf.put(data);
            return data.length;
        });

        inputStream = new BoundedSeekableByteChannelInputStream(mockChannel, data.length);

        byte[] readBuffer = new byte[3];
        int bytesRead = inputStream.read(readBuffer, 0, readBuffer.length);

        assertEquals(3, bytesRead);
        assertArrayEquals(data, readBuffer);
    }

    @Test
    void testReadWithBytesRemainingMoreThanBufferCapacity() throws IOException {
        byte[] data = new byte[9000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        when(mockChannel.read(any(ByteBuffer.class))).thenAnswer(invocation -> {
            ByteBuffer buf = invocation.getArgument(0);
            buf.put(data, 0, buf.remaining());
            return buf.remaining();
        });

        inputStream = new BoundedSeekableByteChannelInputStream(mockChannel, data.length);

        byte[] readBuffer = new byte[9000];
        int bytesRead = inputStream.read(readBuffer, 0, readBuffer.length);

        assertEquals(9000, bytesRead);
        assertArrayEquals(data, readBuffer);
    }

    @Test
    void testReadWithNoBytesRemaining() throws IOException {
        inputStream = new BoundedSeekableByteChannelInputStream(mockChannel, 0);

        byte[] readBuffer = new byte[5];
        int bytesRead = inputStream.read(readBuffer, 0, readBuffer.length);

        assertEquals(-1, bytesRead);
    }
}