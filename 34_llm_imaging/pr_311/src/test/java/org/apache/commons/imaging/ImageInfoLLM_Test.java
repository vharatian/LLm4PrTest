package org.apache.commons.imaging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageInfoLLM_Test {

    @Test
    public void testCompressionAlgorithmToString() {
        // Test the new compression algorithms added in the diff
        assertEquals("JPEG Obsolete (TIFF only)", ImageInfo.CompressionAlgorithm.JPEG_TIFF_OBSOLETE.toString());
        assertEquals("DEFLATE (ZIP)", ImageInfo.CompressionAlgorithm.DEFLATE.toString());
    }
}