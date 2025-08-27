package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GifImageParserLLM_Test {

    @Test
    public void testInvalidColorTableIndex() throws IOException {
        byte[] gifData = new byte[]{
                0x47, 0x49, 0x46, 0x38, 0x39, 0x61, // Header
                0x01, 0x00, 0x01, 0x00, // Logical Screen Width/Height
                0x80, 0x00, 0x00, // GCT Flag, Color Resolution, Sort Flag, Size of GCT
                0x00, 0x00, 0x00, // Background Color Index, Pixel Aspect Ratio
                0x00, 0x00, 0x00, // Global Color Table (1 entry)
                0x2C, // Image Separator
                0x00, 0x00, 0x00, 0x00, // Image Left, Top Position
                0x01, 0x00, 0x01, 0x00, // Image Width, Height
                0x00, // No Local Color Table, No Interlace
                0x02, // LZW Minimum Code Size
                0x02, 0x4C, 0x01, 0x00, // Image Data
                0x3B // Trailer
        };

        ByteSource byteSource = new ByteSourceArray(gifData);
        GifImageParser parser = new GifImageParser();

        assertThrows(ImageReadException.class, () -> {
            List<BufferedImage> images = parser.getAllBufferedImages(byteSource);
        });
    }
}