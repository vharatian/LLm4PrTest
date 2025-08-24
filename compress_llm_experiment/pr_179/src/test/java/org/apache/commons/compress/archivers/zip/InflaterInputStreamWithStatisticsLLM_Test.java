package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.utils.InputStreamStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.Inflater;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InflaterInputStreamWithStatisticsLLM_Test {

    private InflaterInputStreamWithStatistics inflaterInputStreamWithStatistics;

    @BeforeEach
    public void setUp() {
        inflaterInputStreamWithStatistics = new InflaterInputStreamWithStatistics(new ByteArrayInputStream(new byte[0]));
    }

    @Test
    public void testCompressedCountInitialization() {
        // Ensure compressedCount is initialized to 0
        assertEquals(0, inflaterInputStreamWithStatistics.getCompressedCount());
    }

    @Test
    public void testUncompressedCountInitialization() {
        // Ensure uncompressedCount is initialized to 0
        assertEquals(0, inflaterInputStreamWithStatistics.getUncompressedCount());
    }

    @Test
    public void testCompressedCountAfterFill() throws IOException {
        Inflater inflater = new Inflater();
        inflaterInputStreamWithStatistics = new InflaterInputStreamWithStatistics(new ByteArrayInputStream(new byte[]{1, 2, 3}), inflater);
        inflaterInputStreamWithStatistics.fill();
        // Since the input stream is empty, compressedCount should remain 0
        assertEquals(0, inflaterInputStreamWithStatistics.getCompressedCount());
    }

    @Test
    public void testUncompressedCountAfterRead() throws IOException {
        inflaterInputStreamWithStatistics = new InflaterInputStreamWithStatistics(new ByteArrayInputStream(new byte[]{1, 2, 3}));
        inflaterInputStreamWithStatistics.read();
        // Since the input stream is empty, uncompressedCount should remain 0
        assertEquals(0, inflaterInputStreamWithStatistics.getUncompressedCount());
    }
}