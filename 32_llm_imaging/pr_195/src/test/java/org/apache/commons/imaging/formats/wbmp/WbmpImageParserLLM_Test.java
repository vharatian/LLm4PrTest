package org.apache.commons.imaging.formats.wbmp;

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

public class WbmpImageParserLLM_Test {

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        // Mocking ByteSource
        ByteSource byteSource = mock(ByteSource.class);
        WbmpImageParser.WbmpHeader wbmpHeader = new WbmpImageParser.WbmpHeader(0, (byte) 0, 100, 100);

        // Mocking WbmpImageParser to return the mocked WbmpHeader
        WbmpImageParser parser = new WbmpImageParser() {
            @Override
            protected WbmpHeader readWbmpHeader(ByteSource byteSource) {
                return wbmpHeader;
            }
        };

        // Calling the method to test
        ImageInfo imageInfo = parser.getImageInfo(byteSource, new WbmpImagingParameters());

        // Asserting the results
        assertEquals("WBMP", imageInfo.getFormatName());
        assertEquals(1, imageInfo.getBitsPerPixel());
        assertEquals(new ArrayList<>(), imageInfo.getComments());
        assertEquals(ImageFormats.WBMP, imageInfo.getFormat());
        assertEquals("Wireless Application Protocol Bitmap", imageInfo.getFormatDetails());
        assertEquals(100, imageInfo.getHeight());
        assertEquals("image/vnd.wap.wbmp", imageInfo.getMimeType());
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