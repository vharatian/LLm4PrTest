package org.apache.commons.imaging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageFormatsLLM_Test {

    @Test
    public void testGetName() {
        assertEquals("BMP", ImageFormats.BMP.getName());
        assertEquals("UNKNOWN", ImageFormats.UNKNOWN.getName());
    }

    @Test
    public void testGetExtensions() {
        assertArrayEquals(new String[]{"bmp", "dib"}, ImageFormats.BMP.getExtensions());
        assertArrayEquals(new String[]{"jpg", "jpeg"}, ImageFormats.JPEG.getExtensions());
        assertArrayEquals(new String[]{"hdr", "pic"}, ImageFormats.RGBE.getExtensions());
        assertArrayEquals(new String[]{"tif", "tiff"}, ImageFormats.TIFF.getExtensions());
        assertArrayEquals(new String[]{"xbm"}, ImageFormats.XBM.getExtensions());
    }

    @Test
    public void testGetDefaultExtension() {
        assertEquals("bmp", ImageFormats.BMP.getDefaultExtension());
        assertEquals("jpg", ImageFormats.JPEG.getDefaultExtension());
        assertEquals("hdr", ImageFormats.RGBE.getDefaultExtension());
        assertEquals("tif", ImageFormats.TIFF.getDefaultExtension());
        assertEquals("xbm", ImageFormats.XBM.getDefaultExtension());
        assertNull(ImageFormats.UNKNOWN.getDefaultExtension());
    }
}