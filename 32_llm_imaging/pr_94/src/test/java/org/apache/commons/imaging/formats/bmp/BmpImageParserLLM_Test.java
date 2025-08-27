package org.apache.commons.imaging.formats.bmp;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BmpImageParserLLM_Test {

    @Test
    public void testGetImageInfoPhysicalWidthDpi() throws ImageReadException, IOException {
        byte[] bmpData = createBmpDataWithResolution(3780, 3780); // 96 DPI
        ByteSource byteSource = new ByteSourceArray(bmpData);
        BmpImageParser parser = new BmpImageParser();

        ImageInfo imageInfo = parser.getImageInfo(byteSource, null);

        assertEquals(96, imageInfo.getPhysicalWidthDpi());
    }

    @Test
    public void testGetImageInfoPhysicalHeightDpi() throws ImageReadException, IOException {
        byte[] bmpData = createBmpDataWithResolution(3780, 3780); // 96 DPI
        ByteSource byteSource = new ByteSourceArray(bmpData);
        BmpImageParser parser = new BmpImageParser();

        ImageInfo imageInfo = parser.getImageInfo(byteSource, null);

        assertEquals(96, imageInfo.getPhysicalHeightDpi());
    }

    @Test
    public void testGetImageInfoInvalidResolution() throws ImageReadException, IOException {
        byte[] bmpData = createBmpDataWithResolution(-1, -1); // Invalid DPI
        ByteSource byteSource = new ByteSourceArray(bmpData);
        BmpImageParser parser = new BmpImageParser();

        assertThrows(ImageReadException.class, () -> parser.getImageInfo(byteSource, null));
    }

    private byte[] createBmpDataWithResolution(int hResolution, int vResolution) {
        // Create a minimal BMP header with the specified resolution
        byte[] bmpHeader = new byte[54];
        bmpHeader[0] = 0x42; // 'B'
        bmpHeader[1] = 0x4D; // 'M'
        bmpHeader[10] = 54; // Pixel data offset
        bmpHeader[14] = 40; // Header size
        bmpHeader[18] = 1; // Width
        bmpHeader[22] = 1; // Height
        bmpHeader[26] = 1; // Planes
        bmpHeader[28] = 24; // Bits per pixel
        bmpHeader[38] = (byte) (hResolution & 0xFF);
        bmpHeader[39] = (byte) ((hResolution >> 8) & 0xFF);
        bmpHeader[42] = (byte) (vResolution & 0xFF);
        bmpHeader[43] = (byte) ((vResolution >> 8) & 0xFF);
        return bmpHeader;
    }
}