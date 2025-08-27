package org.apache.commons.imaging.common.itu_t4;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class T4AndT6CompressionLLM_Test {

    @Test
    public void testDecompressT4_2D() throws ImageReadException {
        byte[] compressedData = {/* some compressed data */};
        int width = 10;
        int height = 10;
        boolean hasFill = true;

        byte[] decompressedData = T4AndT6Compression.decompressT4_2D(compressedData, width, height, hasFill);

        assertNotNull(decompressedData);
        assertEquals(width * height, decompressedData.length * 8); // Assuming 1 bit per pixel
    }

    @Test
    public void testDecompressT4_2D_InvalidEOL() {
        byte[] compressedData = {/* some invalid compressed data */};
        int width = 10;
        int height = 10;
        boolean hasFill = true;

        assertThrows(ImageReadException.class, () -> {
            T4AndT6Compression.decompressT4_2D(compressedData, width, height, hasFill);
        });
    }

    @Test
    public void testCompressT6() throws ImageWriteException {
        byte[] uncompressedData = new byte[10 * 10 / 8]; // Assuming 1 bit per pixel
        int width = 10;
        int height = 10;

        byte[] compressedData = T4AndT6Compression.compressT6(uncompressedData, width, height);

        assertNotNull(compressedData);
        assertTrue(compressedData.length > 0);
    }

    @Test
    public void testCompressT6_IOException() {
        byte[] uncompressedData = new byte[10 * 10 / 8]; // Assuming 1 bit per pixel
        int width = 10;
        int height = 10;

        assertThrows(ImageWriteException.class, () -> {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(uncompressedData)) {
                bais.close(); // Close the stream to cause an IOException
                T4AndT6Compression.compressT6(uncompressedData, width, height);
            }
        });
    }
}