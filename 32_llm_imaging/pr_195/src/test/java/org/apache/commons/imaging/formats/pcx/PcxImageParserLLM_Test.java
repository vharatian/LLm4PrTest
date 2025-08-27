package org.apache.commons.imaging.formats.pcx;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PcxImageParserLLM_Test {

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        // Mock ByteSource
        ByteSource byteSource = mock(ByteSource.class);
        
        // Mock PcxHeader
        PcxImageParser.PcxHeader pcxHeader = new PcxImageParser.PcxHeader(
                10, 5, 1, 8, 0, 0, 100, 100, 300, 300, new int[16], 0, 3, 100, 1, 0, 0);
        
        // Mock PcxImageParser
        PcxImageParser parser = new PcxImageParser() {
            @Override
            protected PcxHeader readPcxHeader(ByteSource byteSource) {
                return pcxHeader;
            }

            @Override
            public Dimension getImageSize(ByteSource byteSource, PcxImagingParameters params) {
                return new Dimension(101, 101);
            }
        };

        // Call getImageInfo
        ImageInfo imageInfo = parser.getImageInfo(byteSource, null);

        // Validate the results
        assertEquals("PCX", imageInfo.getFormatName());
        assertEquals(24, imageInfo.getBitsPerPixel());
        assertEquals(new ArrayList<>(), imageInfo.getComments());
        assertEquals(ImageFormats.PCX, imageInfo.getFormat());
        assertEquals("ZSoft PCX Image", imageInfo.getFormatDetails());
        assertEquals(101, imageInfo.getHeight());
        assertEquals("image/x-pcx", imageInfo.getMimeType());
        assertEquals(1, imageInfo.getNumberOfImages());
        assertEquals(300, imageInfo.getPhysicalHeightDpi());
        assertEquals(0, imageInfo.getPhysicalHeightInch());
        assertEquals(300, imageInfo.getPhysicalWidthDpi());
        assertEquals(0, imageInfo.getPhysicalWidthInch());
        assertEquals(101, imageInfo.getWidth());
        assertEquals(false, imageInfo.isProgressive());
        assertEquals(false, imageInfo.isTransparent());
        assertEquals(true, imageInfo.isRgb());
        assertEquals(ImageInfo.ColorType.RGB, imageInfo.getColorType());
        assertEquals(ImageInfo.CompressionAlgorithm.RLE, imageInfo.getCompressionAlgorithm());
    }
}