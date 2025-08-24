package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ZipIoUtilLLM_Test {

    @Test
    void testWriteFully() throws IOException {
        SeekableByteChannel mockChannel = mock(SeekableByteChannel.class);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        buffer.flip();

        when(mockChannel.write(buffer)).thenReturn(10);

        ZipIoUtil.writeFully(mockChannel, buffer);

        verify(mockChannel, times(1)).write(buffer);
    }

    @Test
    void testWriteFullyThrowsIOException() throws IOException {
        SeekableByteChannel mockChannel = mock(SeekableByteChannel.class);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        buffer.flip();

        when(mockChannel.write(buffer)).thenReturn(0);

        assertThrows(IOException.class, () -> ZipIoUtil.writeFully(mockChannel, buffer));
    }

    @Test
    void testWriteFullyAt() throws IOException {
        FileChannel mockChannel = mock(FileChannel.class);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        buffer.flip();

        when(mockChannel.write(buffer, 0)).thenReturn(10);

        ZipIoUtil.writeFullyAt(mockChannel, buffer, 0);

        verify(mockChannel, times(1)).write(buffer, 0);
    }

    @Test
    void testWriteFullyAtThrowsIOException() throws IOException {
        FileChannel mockChannel = mock(FileChannel.class);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        buffer.flip();

        when(mockChannel.write(buffer, 0)).thenReturn(0);

        assertThrows(IOException.class, () -> ZipIoUtil.writeFullyAt(mockChannel, buffer, 0));
    }
}