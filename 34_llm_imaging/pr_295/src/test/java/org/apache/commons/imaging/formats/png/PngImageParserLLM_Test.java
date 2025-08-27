package org.apache.commons.imaging.formats.png;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.bytesource.ByteSourceInputStream;
import org.apache.commons.imaging.formats.png.chunks.PngChunk;
import org.apache.commons.imaging.formats.png.chunks.PngChunkPlte;
import org.junit.jupiter.api.Test;

public class PngImageParserLLM_Test {

    @Test
    public void testUsesPaletteWhenPLTEChunkExists() throws Exception {
        // Create a byte array representing a PNG file with a PLTE chunk
        final byte[] pngWithPlteChunk = {
            (byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n', // PNG signature
            0, 0, 0, 13, // IHDR chunk length
            'I', 'H', 'D', 'R', // IHDR chunk type
            0, 0, 0, 1, // Width: 1
            0, 0, 0, 1, // Height: 1
            8, // Bit depth: 8
            3, // Color type: Indexed color
            0, // Compression method
            0, // Filter method
            0, // Interlace method
            (byte) 0x2C, (byte) 0x26, (byte) 0xB4, (byte) 0xE5, // CRC
            0, 0, 0, 3, // PLTE chunk length
            'P', 'L', 'T', 'E', // PLTE chunk type
            0, 0, 0, // Palette entries
            (byte) 0x76, (byte) 0xA5, (byte) 0x57, // CRC
            0, 0, 0, 0, // IEND chunk length
            'I', 'E', 'N', 'D', // IEND chunk type
            (byte) 0xAE, 0x42, 0x60, (byte) 0x82 // CRC
        };

        ByteSource byteSource = new ByteSourceInputStream(new ByteArrayInputStream(pngWithPlteChunk), "test");
        PngImageParser parser = new PngImageParser();
        PngImagingParameters params = new PngImagingParameters();
        PngImageInfo imageInfo = (PngImageInfo) parser.getImageInfo(byteSource, params);

        assertTrue(imageInfo.usesPalette(), "Image should use palette when PLTE chunk exists");
    }

    @Test
    public void testDoesNotUsePaletteWhenPLTEChunkDoesNotExist() throws Exception {
        // Create a byte array representing a PNG file without a PLTE chunk
        final byte[] pngWithoutPlteChunk = {
            (byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n', // PNG signature
            0, 0, 0, 13, // IHDR chunk length
            'I', 'H', 'D', 'R', // IHDR chunk type
            0, 0, 0, 1, // Width: 1
            0, 0, 0, 1, // Height: 1
            8, // Bit depth: 8
            2, // Color type: Truecolor
            0, // Compression method
            0, // Filter method
            0, // Interlace method
            (byte) 0x2C, (byte) 0x26, (byte) 0xB4, (byte) 0xE5, // CRC
            0, 0, 0, 0, // IEND chunk length
            'I', 'E', 'N', 'D', // IEND chunk type
            (byte) 0xAE, 0x42, 0x60, (byte) 0x82 // CRC
        };

        ByteSource byteSource = new ByteSourceInputStream(new ByteArrayInputStream(pngWithoutPlteChunk), "test");
        PngImageParser parser = new PngImageParser();
        PngImagingParameters params = new PngImagingParameters();
        PngImageInfo imageInfo = (PngImageInfo) parser.getImageInfo(byteSource, params);

        assertFalse(imageInfo.usesPalette(), "Image should not use palette when PLTE chunk does not exist");
    }
}