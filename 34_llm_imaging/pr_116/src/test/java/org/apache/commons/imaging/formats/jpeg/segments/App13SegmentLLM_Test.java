package org.apache.commons.imaging.formats.jpeg.segments;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImagingParameters;
import org.apache.commons.imaging.formats.jpeg.iptc.PhotoshopApp13Data;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class App13SegmentLLM_Test {

    @Test
    public void testConstructorWithMarkerAndSegmentData() throws IOException {
        int marker = 0xFFED;
        byte[] segmentData = new byte[]{0x1, 0x2, 0x3, 0x4};
        App13Segment segment = new App13Segment(marker, segmentData);
        assertNotNull(segment);
    }

    @Test
    public void testConstructorWithMarkerMarkerLengthAndInputStream() throws IOException {
        int marker = 0xFFED;
        int markerLength = 4;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[]{0x1, 0x2, 0x3, 0x4});
        App13Segment segment = new App13Segment(marker, markerLength, inputStream);
        assertNotNull(segment);
    }

    @Test
    public void testIsPhotoshopJpegSegment() throws IOException {
        int marker = 0xFFED;
        byte[] segmentData = new byte[]{0x1, 0x2, 0x3, 0x4};
        App13Segment segment = new App13Segment(marker, segmentData);
        assertFalse(segment.isPhotoshopJpegSegment());
    }

    @Test
    public void testParsePhotoshopSegment() throws IOException, ImageReadException {
        int marker = 0xFFED;
        byte[] segmentData = new byte[]{0x1, 0x2, 0x3, 0x4};
        App13Segment segment = new App13Segment(marker, segmentData);
        ImagingParameters params = new ImagingParameters();
        PhotoshopApp13Data data = segment.parsePhotoshopSegment(params);
        assertNull(data);
    }
}