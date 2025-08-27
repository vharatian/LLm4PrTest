package org.apache.commons.imaging.formats.tiff.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiffConstantsLLM_Test {

    @Test
    public void testTiffCompressionJpegObsolete() {
        assertEquals(6, TiffConstants.TIFF_COMPRESSION_JPEG_OBSOLETE);
    }

    @Test
    public void testTiffCompressionJpeg() {
        assertEquals(7, TiffConstants.TIFF_COMPRESSION_JPEG);
    }
}