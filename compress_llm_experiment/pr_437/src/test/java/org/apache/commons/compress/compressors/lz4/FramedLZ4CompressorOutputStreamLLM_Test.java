package org.apache.commons.compress.compressors.lz4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FramedLZ4CompressorOutputStreamLLM_Test {

    private ByteArrayOutputStream baos;
    private FramedLZ4CompressorOutputStream compressorOutputStream;

    @BeforeEach
    public void setUp() throws IOException {
        baos = new ByteArrayOutputStream();
        compressorOutputStream = new FramedLZ4CompressorOutputStream(baos);
    }

    @Test
    public void testFinishFlushesRemainingData() throws IOException {
        byte[] data = new byte[100];
        compressorOutputStream.write(data);
        compressorOutputStream.finish();
        byte[] compressedData = baos.toByteArray();
        assertTrue(compressedData.length > 0, "Compressed data should not be empty after finish");
    }

    @Test
    public void testFlushBlockWithNoData() throws IOException {
        compressorOutputStream.finish();
        byte[] compressedData = baos.toByteArray();
        assertTrue(compressedData.length > 0, "Compressed data should not be empty after finish with no data");
    }

    @Test
    public void testWriteWithPartialBlock() throws IOException {
        byte[] data = new byte[50];
        compressorOutputStream.write(data);
        byte[] compressedDataBeforeFinish = baos.toByteArray();
        assertTrue(compressedDataBeforeFinish.length == 0, "Compressed data should be empty before finish");
        compressorOutputStream.finish();
        byte[] compressedDataAfterFinish = baos.toByteArray();
        assertTrue(compressedDataAfterFinish.length > 0, "Compressed data should not be empty after finish");
    }

    @Test
    public void testWriteWithFullBlock() throws IOException {
        byte[] data = new byte[compressorOutputStream.params.blockSize.getSize()];
        compressorOutputStream.write(data);
        byte[] compressedDataBeforeFinish = baos.toByteArray();
        assertTrue(compressedDataBeforeFinish.length > 0, "Compressed data should not be empty after writing full block");
        compressorOutputStream.finish();
        byte[] compressedDataAfterFinish = baos.toByteArray();
        assertTrue(compressedDataAfterFinish.length > compressedDataBeforeFinish.length, "Compressed data should increase after finish");
    }
}