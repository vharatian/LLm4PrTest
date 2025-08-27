package org.apache.commons.imaging;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImagingConstantsLLM_Test {

    @Test
    public void testParamKeyFilename() {
        assertEquals("FILENAME", ImagingConstants.PARAM_KEY_FILENAME);
    }

    @Test
    public void testParamKeyFormat() {
        assertEquals("FORMAT", ImagingConstants.PARAM_KEY_FORMAT);
    }

    @Test
    public void testParamKeyCompression() {
        assertEquals("COMPRESSION", ImagingConstants.PARAM_KEY_COMPRESSION);
    }

    @Test
    public void testBufferedImageFactory() {
        assertEquals("BUFFERED_IMAGE_FACTORY", ImagingConstants.BUFFERED_IMAGE_FACTORY);
    }

    @Test
    public void testParamKeyReadThumbnails() {
        assertEquals("READ_THUMBNAILS", ImagingConstants.PARAM_KEY_READ_THUMBNAILS);
    }

    @Test
    public void testParamKeyStrict() {
        assertEquals("STRICT", ImagingConstants.PARAM_KEY_STRICT);
    }

    @Test
    public void testParamKeyExif() {
        assertEquals("EXIF", ImagingConstants.PARAM_KEY_EXIF);
    }

    @Test
    public void testParamKeyXmpXml() {
        assertEquals("XMP_XML", ImagingConstants.PARAM_KEY_XMP_XML);
    }

    @Test
    public void testParamKeyPixelDensity() {
        assertEquals("PIXEL_DENSITY", ImagingConstants.PARAM_KEY_PIXEL_DENSITY);
    }
}