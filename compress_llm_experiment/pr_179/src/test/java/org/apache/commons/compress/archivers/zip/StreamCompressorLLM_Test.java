package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;

import org.junit.Test;

public class StreamCompressorLLM_Test {

    @Test
    public void testInitialValues() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (StreamCompressor sc = StreamCompressor.create(baos)) {
            // Test initial values of the fields
            assertEquals(0, sc.getBytesRead());
            assertEquals(0, sc.getBytesWrittenForLastEntry());
            assertEquals(0, sc.getTotalBytesWritten());
        }
    }

    @Test
    public void testResetValues() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (StreamCompressor sc = StreamCompressor.create(baos)) {
            sc.deflate(new ByteArrayInputStream("TEST".getBytes()), ZipEntry.STORED);
            sc.reset();
            // Test values after reset
            assertEquals(0, sc.getBytesRead());
            assertEquals(0, sc.getBytesWrittenForLastEntry());
            assertEquals(0, sc.getTotalBytesWritten());
        }
    }

    @Test
    public void testCreateWithCompressionLevel() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (StreamCompressor sc = StreamCompressor.create(Deflater.BEST_COMPRESSION, new ScatterGatherBackingStore() {
            @Override
            public void writeOut(byte[] data, int offset, int length) throws IOException {
                baos.write(data, offset, length);
            }

            @Override
            public void close() throws IOException {
                baos.close();
            }
        })) {
            assertNotNull(sc);
        }
    }
}