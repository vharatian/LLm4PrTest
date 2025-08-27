package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.File;
import java.io.IOException;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.ImageInfo;
import org.junit.jupiter.api.Test;

public class TiffImageParserLLM_Test {

    @Test
    public void testCompressionAlgorithmDeflateAdobe() throws IOException, ImagingException {
        ByteSource byteSource = ByteSource.file(new File("src/test/resources/images/tiff/deflate_adobe.tiff"));
        TiffImageParser parser = new TiffImageParser();
        ImageInfo imageInfo = parser.getImageInfo(byteSource, null);
        assertNotNull(imageInfo);
        assertEquals(ImageInfo.CompressionAlgorithm.DEFLATE, imageInfo.getCompressionAlgorithm());
    }

    @Test
    public void testCompressionAlgorithmDeflatePkzip() throws IOException, ImagingException {
        ByteSource byteSource = ByteSource.file(new File("src/test/resources/images/tiff/deflate_pkzip.tiff"));
        TiffImageParser parser = new TiffImageParser();
        ImageInfo imageInfo = parser.getImageInfo(byteSource, null);
        assertNotNull(imageInfo);
        assertEquals(ImageInfo.CompressionAlgorithm.DEFLATE, imageInfo.getCompressionAlgorithm());
    }

    @Test
    public void testCompressionAlgorithmJpegObsolete() throws IOException, ImagingException {
        ByteSource byteSource = ByteSource.file(new File("src/test/resources/images/tiff/jpeg_obsolete.tiff"));
        TiffImageParser parser = new TiffImageParser();
        ImageInfo imageInfo = parser.getImageInfo(byteSource, null);
        assertNotNull(imageInfo);
        assertEquals(ImageInfo.CompressionAlgorithm.JPEG_TIFF_OBSOLETE, imageInfo.getCompressionAlgorithm());
    }
}