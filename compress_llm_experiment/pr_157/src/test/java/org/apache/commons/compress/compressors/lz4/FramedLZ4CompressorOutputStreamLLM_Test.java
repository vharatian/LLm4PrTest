package org.apache.commons.compress.compressors.lz4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FramedLZ4CompressorOutputStreamLLM_Test {

    @Test
    public void testWriteWithContentChecksumAndBlockDataLengthExceeded() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FramedLZ4CompressorOutputStream.Parameters params = new FramedLZ4CompressorOutputStream.Parameters(
                FramedLZ4CompressorOutputStream.BlockSize.K64, true, false, false);
        FramedLZ4CompressorOutputStream stream = new FramedLZ4CompressorOutputStream(baos, params);

        byte[] data = new byte[128 * 1024]; // 128 KB data
        stream.write(data);

        assertEquals(0, stream.currentIndex);
        assertTrue(baos.size() > 0);
    }

    @Test
    public void testWriteWithBlockDataLengthNotExceeded() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FramedLZ4CompressorOutputStream.Parameters params = new FramedLZ4CompressorOutputStream.Parameters(
                FramedLZ4CompressorOutputStream.BlockSize.K64, true, false, false);
        FramedLZ4CompressorOutputStream stream = new FramedLZ4CompressorOutputStream(baos, params);

        byte[] data = new byte[32 * 1024]; // 32 KB data
        stream.write(data);

        assertEquals(32 * 1024, stream.currentIndex);
        assertTrue(baos.size() > 0);
    }

    @Test
    public void testWriteWithMultipleFlushes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FramedLZ4CompressorOutputStream.Parameters params = new FramedLZ4CompressorOutputStream.Parameters(
                FramedLZ4CompressorOutputStream.BlockSize.K64, true, false, false);
        FramedLZ4CompressorOutputStream stream = new FramedLZ4CompressorOutputStream(baos, params);

        byte[] data = new byte[128 * 1024]; // 128 KB data
        stream.write(data, 0, 128 * 1024);

        assertEquals(0, stream.currentIndex);
        assertTrue(baos.size() > 0);
    }
}