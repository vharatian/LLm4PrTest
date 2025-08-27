package org.apache.commons.imaging.formats.jpeg.xmp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class JpegRewriterLLM_Test {

    @Test
    public void testJFIFPieceSegmentConstructorClonesSegmentData() {
        byte[] segmentData = {0x01, 0x02, 0x03};
        JpegRewriter.JFIFPieceSegment segment = new JpegRewriter.JFIFPieceSegment(0xFFE1, segmentData);

        // Modify the original array to ensure it does not affect the segment data
        segmentData[0] = 0x00;

        byte[] segmentDataFromSegment = segment.getSegmentData();
        assertNotSame(segmentData, segmentDataFromSegment, "Segment data should be cloned");
        assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, segmentDataFromSegment, "Segment data should match the original values");
    }

    @Test
    public void testGetSegmentDataReturnsClonedArray() {
        byte[] segmentData = {0x01, 0x02, 0x03};
        JpegRewriter.JFIFPieceSegment segment = new JpegRewriter.JFIFPieceSegment(0xFFE1, segmentData);

        byte[] segmentDataFromSegment = segment.getSegmentData();
        segmentDataFromSegment[0] = 0x00;

        byte[] segmentDataFromSegmentAgain = segment.getSegmentData();
        assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, segmentDataFromSegmentAgain, "Segment data should be cloned and unchanged");
    }
}