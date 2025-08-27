package org.apache.commons.imaging.formats.jpeg.decoder;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class JpegDecoderLLM_Test {

    @Test
    public void testSplitByRstMarkers() throws Exception {
        // Test data simulating scan payload with RST markers
        int[] scanPayload = {0, 1, 2, 0xFF, 0xD0, 3, 4, 5, 0xFF, 0xD1, 6, 7, 8};

        // Expected intervals
        int[][] expectedIntervals = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        // Invoke the method using reflection
        Method method = JpegDecoder.class.getDeclaredMethod("splitByRstMarkers", int[].class);
        method.setAccessible(true);
        JpegInputStream[] result = (JpegInputStream[]) method.invoke(null, (Object) scanPayload);

        // Verify the intervals
        assertEquals(expectedIntervals.length, result.length);
        for (int i = 0; i < expectedIntervals.length; i++) {
            int[] interval = result[i].getData();
            assertArrayEquals(expectedIntervals[i], interval);
        }
    }

    @Test
    public void testGetIntervalStartPositions() throws Exception {
        // Test data simulating scan payload with RST markers
        int[] scanPayload = {0, 1, 2, 0xFF, 0xD0, 3, 4, 5, 0xFF, 0xD1, 6, 7, 8};

        // Expected start positions
        List<Integer> expectedStartPositions = Arrays.asList(0, 5, 10);

        // Invoke the method using reflection
        Method method = JpegDecoder.class.getDeclaredMethod("getIntervalStartPositions", int[].class);
        method.setAccessible(true);
        List<Integer> result = (List<Integer>) method.invoke(null, (Object) scanPayload);

        // Verify the start positions
        assertEquals(expectedStartPositions, result);
    }

    @Test
    public void testDecodeWithRstMarkers() throws ImageReadException, IOException {
        // Test decoding a JPEG file with RST markers
        final File inputFile = new File(
                JpegDecoderTest2.class.getResource("/IMAGING-220/with-rst-markers.jpeg").getFile());
        final ByteSourceFile byteSourceFile = new ByteSourceFile(inputFile);
        BufferedImage image = new JpegDecoder().decode(byteSourceFile);

        // Verify the image is decoded successfully
        assertNotNull(image);
    }
}