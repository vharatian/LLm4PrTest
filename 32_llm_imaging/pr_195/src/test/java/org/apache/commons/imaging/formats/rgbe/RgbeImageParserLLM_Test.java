package org.apache.commons.imaging.formats.rgbe;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RgbeImageParserLLM_Test {

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        // Mock ByteSource and RgbeImagingParameters
        ByteSource byteSource = ...; // Provide a mock or actual ByteSource
        RgbeImagingParameters params = new RgbeImagingParameters();

        // Create an instance of RgbeImageParser
        RgbeImageParser parser = new RgbeImageParser();

        // Call the method to test
        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);

        // Validate the changes
        assertEquals("Radiance HDR", imageInfo.getFormatName());
        assertEquals(32, imageInfo.getBitsPerPixel());
        assertEquals(new ArrayList<>(), imageInfo.getComments());
        assertEquals(ImageFormats.RGBE, imageInfo.getFormat());
        assertEquals("Radiance HDR", imageInfo.getFormatName());
        assertEquals("image/vnd.radiance", imageInfo.getMimeType());
        assertEquals(1, imageInfo.getNumberOfImages());
        assertEquals(-1, imageInfo.getPhysicalHeightDpi());
        assertEquals(-1, imageInfo.getPhysicalWidthDpi());
        assertEquals(-1, imageInfo.getPhysicalHeightInch());
        assertEquals(-1, imageInfo.getPhysicalWidthInch());
        assertEquals(false, imageInfo.isProgressive());
        assertEquals(false, imageInfo.isTransparent());
        assertEquals(false, imageInfo.isAnimation());
        assertEquals(ImageInfo.ColorType.RGB, imageInfo.getColorType());
        assertEquals(ImageInfo.CompressionAlgorithm.ADAPTIVE_RLE, imageInfo.getCompressionAlgorithm());
    }
}