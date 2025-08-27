package org.apache.commons.imaging.formats.jpeg;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.jpeg.segments.Segment;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JpegImageParserLLM_Test {

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.file("path/to/jpeg/file.jpg");
        Map<String, Object> params = null;

        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);

        assertNotNull(imageInfo);
        assertEquals("JPEG (Joint Photographic Experts Group) Format", imageInfo.getFormatName());
        assertEquals("image/jpeg", imageInfo.getMimeType());
        assertTrue(imageInfo.getWidth() > 0);
        assertTrue(imageInfo.getHeight() > 0);
    }

    @Test
    public void testReadSegments() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.file("path/to/jpeg/file.jpg");
        int[] markers = {JpegConstants.SOF0_MARKER, JpegConstants.SOF1_MARKER};
        boolean returnAfterFirst = true;

        List<Segment> segments = parser.readSegments(byteSource, markers, returnAfterFirst);

        assertNotNull(segments);
        assertFalse(segments.isEmpty());
        assertTrue(segments.get(0).getMarker() == JpegConstants.SOF0_MARKER || segments.get(0).getMarker() == JpegConstants.SOF1_MARKER);
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.file("path/to/jpeg/file.jpg");
        Map<String, Object> params = null;

        BufferedImage bufferedImage = parser.getBufferedImage(byteSource, params);

        assertNotNull(bufferedImage);
        assertTrue(bufferedImage.getWidth() > 0);
        assertTrue(bufferedImage.getHeight() > 0);
    }
}