package org.apache.commons.imaging.formats.pnm;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PamFileInfoLLM_Test {

    @Test
    void testPamFileInfoWithBlackAndWhite() throws ImageReadException {
        PamFileInfo pamFileInfo = new PamFileInfo(100, 100, 1, 255, "BLACKANDWHITE");
        assertEquals(ImageInfo.ColorType.BW, pamFileInfo.getColorType());
        assertFalse(pamFileInfo.hasAlpha());
    }

    @Test
    void testPamFileInfoWithBlackAndWhiteAlpha() throws ImageReadException {
        PamFileInfo pamFileInfo = new PamFileInfo(100, 100, 2, 255, "BLACKANDWHITE_ALPHA");
        assertEquals(ImageInfo.ColorType.BW, pamFileInfo.getColorType());
        assertTrue(pamFileInfo.hasAlpha());
    }

    @Test
    void testPamFileInfoWithGrayscale() throws ImageReadException {
        PamFileInfo pamFileInfo = new PamFileInfo(100, 100, 1, 255, "GRAYSCALE");
        assertEquals(ImageInfo.ColorType.GRAYSCALE, pamFileInfo.getColorType());
        assertFalse(pamFileInfo.hasAlpha());
    }

    @Test
    void testPamFileInfoWithGrayscaleAlpha() throws ImageReadException {
        PamFileInfo pamFileInfo = new PamFileInfo(100, 100, 2, 255, "GRAYSCALE_ALPHA");
        assertEquals(ImageInfo.ColorType.GRAYSCALE, pamFileInfo.getColorType());
        assertTrue(pamFileInfo.hasAlpha());
    }

    @Test
    void testPamFileInfoWithRGB() throws ImageReadException {
        PamFileInfo pamFileInfo = new PamFileInfo(100, 100, 3, 255, "RGB");
        assertEquals(ImageInfo.ColorType.RGB, pamFileInfo.getColorType());
        assertFalse(pamFileInfo.hasAlpha());
    }

    @Test
    void testPamFileInfoWithRGBAlpha() throws ImageReadException {
        PamFileInfo pamFileInfo = new PamFileInfo(100, 100, 4, 255, "RGB_ALPHA");
        assertEquals(ImageInfo.ColorType.RGB, pamFileInfo.getColorType());
        assertTrue(pamFileInfo.hasAlpha());
    }

    @Test
    void testPamFileInfoWithUnknownTupleType() {
        assertThrows(ImageReadException.class, () -> {
            new PamFileInfo(100, 100, 3, 255, "UNKNOWN");
        });
    }
}