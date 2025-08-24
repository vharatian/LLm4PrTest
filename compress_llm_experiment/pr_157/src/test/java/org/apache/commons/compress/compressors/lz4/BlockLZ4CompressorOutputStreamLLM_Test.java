package org.apache.commons.compress.compressors.lz4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.junit.Assert;
import org.junit.Test;

public class BlockLZ4CompressorOutputStreamLLM_Test {

    @Test
    public void testExpandFromListWithPositiveOffset() throws IOException {
        BlockLZ4CompressorOutputStream compressor = new BlockLZ4CompressorOutputStream(new ByteArrayOutputStream());
        byte[] expanded = new byte[10];
        compressor.prefill(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 10);
        compressor.expandFromList(expanded, 5, 5);
        Assert.assertArrayEquals(new byte[]{6, 7, 8, 9, 10}, expanded);
    }

    @Test
    public void testExpandFromListWithNegativeOffset() throws IOException {
        BlockLZ4CompressorOutputStream compressor = new BlockLZ4CompressorOutputStream(new ByteArrayOutputStream());
        byte[] expanded = new byte[10];
        compressor.prefill(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 10);
        compressor.expandFromList(expanded, -5, 5);
        Assert.assertArrayEquals(new byte[]{6, 7, 8, 9, 10}, expanded);
    }

    @Test
    public void testExpandFromListWithZeroOffset() throws IOException {
        BlockLZ4CompressorOutputStream compressor = new BlockLZ4CompressorOutputStream(new ByteArrayOutputStream());
        byte[] expanded = new byte[10];
        compressor.prefill(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 10);
        compressor.expandFromList(expanded, 0, 5);
        Assert.assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, expanded);
    }
}