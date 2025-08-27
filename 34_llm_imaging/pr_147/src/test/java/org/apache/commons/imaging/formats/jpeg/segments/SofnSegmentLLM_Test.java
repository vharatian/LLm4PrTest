package org.apache.commons.imaging.formats.jpeg.segments;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class SofnSegmentLLM_Test {

    @Test
    public void testSofnSegmentWithValidData() throws IOException, ImageReadException {
        byte[] segmentData = new byte[]{
                8, 0, 10, 0, 10, 3, 1, 17, 0, 2, 17, 1, 3, 17, 1
        };
        SofnSegment segment = new SofnSegment(0xFFC0, segmentData);
        assertEquals(8, segment.precision);
        assertEquals(10, segment.height);
        assertEquals(10, segment.width);
        assertEquals(3, segment.numberOfComponents);
        assertEquals(3, segment.getComponents().length);
    }

    @Test
    public void testSofnSegmentWithNegativeNumberOfComponents() {
        byte[] segmentData = new byte[]{
                8, 0, 10, 0, 10, -1
        };
        assertThrows(ImageReadException.class, () -> {
            new SofnSegment(0xFFC0, segmentData);
        });
    }

    @Test
    public void testSofnSegmentWithZeroComponents() throws IOException, ImageReadException {
        byte[] segmentData = new byte[]{
                8, 0, 10, 0, 10, 0
        };
        SofnSegment segment = new SofnSegment(0xFFC0, segmentData);
        assertEquals(0, segment.numberOfComponents);
        assertEquals(0, segment.getComponents().length);
    }
}