package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SeekableChannelRandomAccessOutputStreamLLM_Test {

    private SeekableByteChannel mockChannel;
    private SeekableChannelRandomAccessOutputStream outputStream;

    @BeforeEach
    void setUp() {
        mockChannel = Mockito.mock(SeekableByteChannel.class);
        outputStream = new SeekableChannelRandomAccessOutputStream(mockChannel);
    }

    @Test
    void testWrite() throws IOException {
        byte[] data = {1, 2, 3, 4};
        outputStream.write(data, 0, data.length);

        verify(mockChannel, times(1)).write(any(ByteBuffer.class));
    }

    @Test
    void testPosition() throws IOException {
        when(mockChannel.position()).thenReturn(10L);

        long position = outputStream.position();

        assertEquals(10L, position);
        verify(mockChannel, times(1)).position();
    }

    @Test
    void testWriteFullyAt() throws IOException {
        byte[] data = {1, 2, 3, 4};
        long position = 5L;
        when(mockChannel.position()).thenReturn(0L);

        outputStream.writeFullyAt(data, 0, data.length, position);

        verify(mockChannel, times(1)).position(position);
        verify(mockChannel, times(1)).write(any(ByteBuffer.class));
        verify(mockChannel, times(1)).position(0L);
    }

    @Test
    void testClose() throws IOException {
        outputStream.close();

        verify(mockChannel, times(1)).close();
    }
}