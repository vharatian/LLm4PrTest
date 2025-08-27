package org.apache.commons.imaging.formats.jpeg.segments;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotSame;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;
import org.junit.Test;

public class App2SegmentLLM_Test {

    @Test
    public void testGetIccBytesReturnsClone() throws IOException, ImageReadException {
        byte[] iccProfileLabel = JpegConstants.ICC_PROFILE_LABEL;
        byte[] segmentData = new byte[iccProfileLabel.length + 2 + 10]; // ICC_PROFILE_LABEL + curMarker + numMarkers + iccBytes
        System.arraycopy(iccProfileLabel, 0, segmentData, 0, iccProfileLabel.length);
        segmentData[iccProfileLabel.length] = 1; // curMarker
        segmentData[iccProfileLabel.length + 1] = 1; // numMarkers
        for (int i = iccProfileLabel.length + 2; i < segmentData.length; i++) {
            segmentData[i] = (byte) i; // iccBytes content
        }

        App2Segment app2Segment = new App2Segment(0, segmentData.length, new ByteArrayInputStream(segmentData));
        byte[] iccBytes1 = app2Segment.getIccBytes();
        byte[] iccBytes2 = app2Segment.getIccBytes();

        assertArrayEquals(iccBytes1, iccBytes2);
        assertNotSame(iccBytes1, iccBytes2); // Ensure that the returned array is a clone
    }
}