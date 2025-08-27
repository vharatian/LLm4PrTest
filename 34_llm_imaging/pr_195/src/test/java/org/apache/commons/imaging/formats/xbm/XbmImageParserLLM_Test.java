package org.apache.commons.imaging.formats.xbm;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class XbmImageParserLLM_Test {

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        // Mock ByteSource
        ByteSource byteSource = mock(ByteSource.class);

        // Mock XbmHeader
        XbmImageParser.XbmHeader xbmHeader = new XbmImageParser.XbmHeader(10, 20, -1, -1);

        // Create instance of XbmImageParser
        XbmImageParser parser = new XbmImageParser() {
            @Override
            protected XbmHeader readXbmHeader(ByteSource byteSource) {
                return xbmHeader;
            }
        };

        // Call getImageInfo and verify the result
        ImageInfo imageInfo = parser.getImageInfo(byteSource, new XbmImagingParameters());
        assertEquals("XBM", imageInfo.getFormatName());
        assertEquals(1, imageInfo.getBitsPerPixel());
        assertEquals(new ArrayList<>(), imageInfo.getComments());
        assertEquals(ImageFormats.XBM, imageInfo.getFormat());
        assertEquals("X BitMap", imageInfo.getFormatDetails());
        assertEquals(20, imageInfo.getHeight());
        assertEquals("image/x-xbitmap", imageInfo.getMimeType());
        assertEquals(1, imageInfo.getNumberOfImages());
        assertEquals(0, imageInfo.getPhysicalHeightDpi());
        assertEquals(0, imageInfo.getPhysicalWidthDpi());
        assertEquals(0, imageInfo.getWidth());
        assertEquals(false, imageInfo.isProgressive());
        assertEquals(false, imageInfo.isTransparent());
        assertEquals(false, imageInfo.isAnimation());
        assertEquals(ImageInfo.ColorType.BW, imageInfo.getColorType());
        assertEquals(ImageInfo.CompressionAlgorithm.NONE, imageInfo.getCompressionAlgorithm());
    }
}