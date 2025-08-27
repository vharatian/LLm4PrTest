package org.apache.commons.imaging.formats.rgbe;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class RgbeInfoLLM_Test {

    @Test
    void testDecompressThrowsImageReadExceptionOnNegativeRead() {
        byte[] inputData = new byte[]{-1}; // Simulate end of stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData);
        byte[] outputData = new byte[10];

        assertThrows(ImageReadException.class, () -> {
            RgbeInfo.decompress(inputStream, outputData);
        });
    }

    @Test
    void testDecompressHandlesValidData() throws IOException, ImageReadException {
        byte[] inputData = new byte[]{(byte) 129, 1}; // Simulate valid compressed data
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData);
        byte[] outputData = new byte[10];

        RgbeInfo.decompress(inputStream, outputData);

        assertEquals(1, outputData[0]);
        for (int i = 1; i < outputData.length; i++) {
            assertEquals(0, outputData[i]);
        }
    }
}