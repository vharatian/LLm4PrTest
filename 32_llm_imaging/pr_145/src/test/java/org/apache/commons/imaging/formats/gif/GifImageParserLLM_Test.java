package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GifImageParserLLM_Test {

    @Test
    public void testGetBufferedImageWithInvalidImageDataLength() {
        // Arrange
        byte[] gifData = new byte[]{
                0x47, 0x49, 0x46, 0x38, 0x39, 0x61, // Header
                0x01, 0x00, 0x01, 0x00, // Logical Screen Width and Height
                0x80, 0x00, 0x00, // GCT Flag, Color Resolution, Sort Flag, Size of GCT
                0x00, 0x00, 0x00, // Background Color Index, Pixel Aspect Ratio
                0x21, (byte) 0xF9, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, // Graphic Control Extension
                0x2C, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00, // Image Descriptor
                0x02, 0x02, 0x44, 0x01, 0x00, // Image Data
                0x3B // Trailer
        };
        ByteSource byteSource = new ByteSourceArray(gifData);
        GifImageParser parser = new GifImageParser();

        // Act & Assert
        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            BufferedImage image = parser.getBufferedImage(byteSource, null);
        });

        assertTrue(exception.getMessage().contains("Invalid GIF image data length"));
    }
}