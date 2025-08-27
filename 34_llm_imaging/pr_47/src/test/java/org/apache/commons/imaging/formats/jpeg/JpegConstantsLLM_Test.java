package org.apache.commons.imaging.formats.jpeg;

import org.junit.Test;
import static org.junit.Assert.*;

public class JpegConstantsLLM_Test {

    @Test
    public void testDRIMarker() {
        assertEquals(0xFFdd, JpegConstants.DRI_MARKER);
    }

    @Test
    public void testRST0Marker() {
        assertEquals(0xFFd0, JpegConstants.RST0_MARKER);
    }

    @Test
    public void testRST1Marker() {
        assertEquals(0xFFd0 + 0x1, JpegConstants.RST1_MARKER);
    }

    @Test
    public void testRST2Marker() {
        assertEquals(0xFFd0 + 0x2, JpegConstants.RST2_MARKER);
    }

    @Test
    public void testRST3Marker() {
        assertEquals(0xFFd0 + 0x3, JpegConstants.RST3_MARKER);
    }

    @Test
    public void testRST4Marker() {
        assertEquals(0xFFd0 + 0x4, JpegConstants.RST4_MARKER);
    }

    @Test
    public void testRST5Marker() {
        assertEquals(0xFFd0 + 0x5, JpegConstants.RST5_MARKER);
    }

    @Test
    public void testRST6Marker() {
        assertEquals(0xFFd0 + 0x6, JpegConstants.RST6_MARKER);
    }

    @Test
    public void testRST7Marker() {
        assertEquals(0xFFd0 + 0x7, JpegConstants.RST7_MARKER);
    }

    @Test
    public void testMarkersListContainsNewMarkers() {
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.DRI_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST0_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST1_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST2_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST3_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST4_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST5_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST6_MARKER));
        assertTrue(JpegConstants.MARKERS.contains(JpegConstants.RST7_MARKER));
    }
}