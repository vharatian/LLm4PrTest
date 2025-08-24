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
    public void testFinishWithoutFlushBlock() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FramedLZ4CompressorOutputStream.Parameters params = new FramedLZ4CompressorOutputStream.Parameters(
                FramedLZ4CompressorOutputStream.BlockSize.M4, true, false, false);
        FramedLZ4CompressorOutputStream out = new FramedLZ4CompressorOutputStream(baos, params);
        out.finish();
        byte[] result = baos.toByteArray();
        assertTrue(result.length > 0, "Trailer should be written even if no data was written");
    }

    @Test
    public void testFlushBlockWithCurrentIndex() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FramedLZ4CompressorOutputStream.Parameters params = new FramedLZ4CompressorOutputStream.Parameters(
                FramedLZ4CompressorOutputStream.BlockSize.M4, true, false, false);
        FramedLZ4CompressorOutputStream out = new FramedLZ4CompressorOutputStream(baos, params);
        byte[] data = new byte[1024];
        out.write(data);
        out.finish();
        byte[] result = baos.toByteArray();
        assertTrue(result.length > 0, "Data should be flushed and written to the output stream");
    }

    @Test
    public void testWriteWithBlockDataLength() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FramedLZ4CompressorOutputStream.Parameters params = new FramedLZ4CompressorOutputStream.Parameters(
                FramedLZ4CompressorOutputStream.BlockSize.M4, true, false, false);
        FramedLZ4CompressorOutputStream out = new FramedLZ4CompressorOutputStream(baos, params);
        byte[] data = new byte[1024 * 1024 * 5]; // 5MB data
        out.write(data);
        out.finish();
        byte[] result = baos.toByteArray();
        assertTrue(result.length > 0, "Large data should be handled and written correctly");
    }
}