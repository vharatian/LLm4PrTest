package org.apache.commons.imaging.formats.jpeg;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JpegUtilsLLM_Test {

    @Test
    public void testGetMarkerName() {
        assertEquals("SOS_MARKER", JpegUtils.getMarkerName(JpegConstants.SOS_MARKER));
        assertEquals("JPEG_APP1_MARKER", JpegUtils.getMarkerName(JpegConstants.JPEG_APP1_MARKER));
        assertEquals("JPEG_APP2_MARKER", JpegUtils.getMarkerName(JpegConstants.JPEG_APP2_MARKER));
        assertEquals("JPEG_APP13_MARKER", JpegUtils.getMarkerName(JpegConstants.JPEG_APP13_MARKER));
        assertEquals("JPEG_APP14_MARKER", JpegUtils.getMarkerName(JpegConstants.JPEG_APP14_MARKER));
        assertEquals("JPEG_APP15_MARKER", JpegUtils.getMarkerName(JpegConstants.JPEG_APP15_MARKER));
        assertEquals("JFIF_MARKER", JpegUtils.getMarkerName(JpegConstants.JFIF_MARKER));
        assertEquals("SOF0_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF0_MARKER));
        assertEquals("SOF1_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF1_MARKER));
        assertEquals("SOF2_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF2_MARKER));
        assertEquals("SOF3_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF3_MARKER));
        assertEquals("SOF4_MARKER", JpegUtils.getMarkerName(JpegConstants.DHT_MARKER));
        assertEquals("SOF5_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF5_MARKER));
        assertEquals("SOF6_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF6_MARKER));
        assertEquals("SOF7_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF7_MARKER));
        assertEquals("SOF8_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF8_MARKER));
        assertEquals("SOF9_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF9_MARKER));
        assertEquals("SOF10_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF10_MARKER));
        assertEquals("SOF11_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF11_MARKER));
        assertEquals("DAC_MARKER", JpegUtils.getMarkerName(JpegConstants.DAC_MARKER));
        assertEquals("SOF13_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF13_MARKER));
        assertEquals("SOF14_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF14_MARKER));
        assertEquals("SOF15_MARKER", JpegUtils.getMarkerName(JpegConstants.SOF15_MARKER));
        assertEquals("DQT_MARKER", JpegUtils.getMarkerName(JpegConstants.DQT_MARKER));
        assertEquals("DRI_MARKER", JpegUtils.getMarkerName(JpegConstants.DRI_MARKER));
        assertEquals("RST0_MARKER", JpegUtils.getMarkerName(JpegConstants.RST0_MARKER));
        assertEquals("RST1_MARKER", JpegUtils.getMarkerName(JpegConstants.RST1_MARKER));
        assertEquals("RST2_MARKER", JpegUtils.getMarkerName(JpegConstants.RST2_MARKER));
        assertEquals("RST3_MARKER", JpegUtils.getMarkerName(JpegConstants.RST3_MARKER));
        assertEquals("RST4_MARKER", JpegUtils.getMarkerName(JpegConstants.RST4_MARKER));
        assertEquals("RST5_MARKER", JpegUtils.getMarkerName(JpegConstants.RST5_MARKER));
        assertEquals("RST6_MARKER", JpegUtils.getMarkerName(JpegConstants.RST6_MARKER));
        assertEquals("RST7_MARKER", JpegUtils.getMarkerName(JpegConstants.RST7_MARKER));
        assertEquals("Unknown", JpegUtils.getMarkerName(0xFFFF));
    }
}