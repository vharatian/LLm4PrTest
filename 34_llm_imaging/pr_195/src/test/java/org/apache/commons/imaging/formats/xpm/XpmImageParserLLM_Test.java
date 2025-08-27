package org.apache.commons.imaging.formats.xpm;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XpmImageParserLLM_Test {

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        // Mock ByteSource and XpmImagingParameters
        ByteSource byteSource = new MockByteSource();
        XpmImagingParameters params = new XpmImagingParameters();

        // Create an instance of XpmImageParser
        XpmImageParser parser = new XpmImageParser();

        // Call the getImageInfo method
        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);

        // Validate the returned ImageInfo object
        assertEquals("XPM version 3", imageInfo.getFormatDetails());
        assertEquals(new ArrayList<>(), imageInfo.getComments());
        assertEquals(ImageFormats.XPM, imageInfo.getFormat());
        assertEquals("X PixMap", imageInfo.getFormatName());
        assertEquals("image/x-xpixmap", imageInfo.getMimeType());
        assertEquals(1, imageInfo.getNumberOfImages());
        assertEquals(0, imageInfo.getPhysicalHeightDpi());
        assertEquals(0, imageInfo.getPhysicalWidthDpi());
        assertEquals(0, imageInfo.getPhysicalHeightInch());
        assertEquals(0, imageInfo.getPhysicalWidthInch());
        assertEquals(0, imageInfo.getWidth());
        assertEquals(false, imageInfo.isProgressive());
        assertEquals(true, imageInfo.isTransparent());
        assertEquals(true, imageInfo.isUsesPalette());
        assertEquals(ImageInfo.ColorType.RGB, imageInfo.getColorType());
        assertEquals(ImageInfo.CompressionAlgorithm.NONE, imageInfo.getCompressionAlgorithm());
    }

    // Mock ByteSource class for testing
    private static class MockByteSource extends ByteSource {
        public MockByteSource() {
            super("mock");
        }

        @Override
        public InputStream getInputStream() throws IOException {
            // Implement a mock input stream
            return new ByteArrayInputStream(new byte[0]);
        }

        @Override
        public byte[] getBlock(int start, int length) throws IOException {
            // Implement a mock byte array
            return new byte[0];
        }

        @Override
        public long getLength() throws IOException {
            // Implement a mock length
            return 0;
        }
    }
}