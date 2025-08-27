package org.apache.commons.compress.compressors.zstandard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.luben.zstd.BufferPool;
import com.github.luben.zstd.NoPool;
import com.github.luben.zstd.RecyclingBufferPool;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class ZstdCompressorInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testZstdDecodeWithNoPool() throws IOException {
        final File input = getFile("zstandard.testdata.zst");
        final File expected = getFile("zstandard.testdata");
        try (InputStream inputStream = new FileInputStream(input);
             ZstdCompressorInputStream zstdInputStream = new ZstdCompressorInputStream(inputStream, new NoPool())) {
            final byte[] b = new byte[97];
            IOUtils.read(expected, b);
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int readByte = -1;
            while ((readByte = zstdInputStream.read()) != -1) {
                bos.write(readByte);
            }
            Assert.assertArrayEquals(b, bos.toByteArray());
        }
    }

    @Test
    public void testZstdDecodeWithRecyclingBufferPool() throws IOException {
        final File input = getFile("zstandard.testdata.zst");
        final File expected = getFile("zstandard.testdata");
        try (InputStream inputStream = new FileInputStream(input);
             ZstdCompressorInputStream zstdInputStream = new ZstdCompressorInputStream(inputStream, new RecyclingBufferPool())) {
            final byte[] b = new byte[97];
            IOUtils.read(expected, b);
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int readByte = -1;
            while ((readByte = zstdInputStream.read()) != -1) {
                bos.write(readByte);
            }
            Assert.assertArrayEquals(b, bos.toByteArray());
        }
    }
}