package org.apache.commons.imaging.formats.jpeg.decoder;

import java.io.File;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JpegDecoderLLM_Test {

    @Test
    public void testSplitByRstMarkers() {
        // Test case for splitByRstMarkers method to ensure it handles intervals correctly
        int[] scanPayload = {0xFF, 0xD0, 0x00, 0x01, 0xFF, 0xD1, 0x00, 0x02, 0xFF, 0xD2, 0x00, 0x03};
        JpegInputStream[] streams = JpegDecoder.splitByRstMarkers(scanPayload);
        Assertions.assertEquals(3, streams.length, "Expected 3 intervals");
    }

    @Test
    public void testGetIntervalStartPositions() {
        // Test case for getIntervalStartPositions method to ensure it identifies start positions correctly
        int[] scanPayload = {0xFF, 0xD0, 0x00, 0x01, 0xFF, 0xD1, 0x00, 0x02, 0xFF, 0xD2, 0x00, 0x03};
        List<Integer> intervalStarts = JpegDecoder.getIntervalStartPositions(scanPayload);
        Assertions.assertEquals(3, intervalStarts.size(), "Expected 3 start positions");
        Assertions.assertEquals(0, intervalStarts.get(0), "First interval should start at position 0");
        Assertions.assertEquals(4, intervalStarts.get(1), "Second interval should start at position 4");
        Assertions.assertEquals(8, intervalStarts.get(2), "Third interval should start at position 8");
    }
}