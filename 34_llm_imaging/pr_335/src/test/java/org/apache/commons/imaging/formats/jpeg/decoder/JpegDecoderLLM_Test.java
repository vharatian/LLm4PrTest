package org.apache.commons.imaging.formats.jpeg.decoder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.File;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.test.TestResources;
import org.junit.jupiter.api.Test;

public class JpegDecoderLLM_Test {

    @Test
    public void testDecodeWithTiffRgb() {
        final File inputFile = TestResources.resourceToFile("/IMAGING-220/tiff-rgb-48eb4251935b4ca8b26d1859ea525c1b42ae0c78.jpeg");
        final ByteSource byteSourceFile = ByteSource.file(inputFile);
        JpegDecoder decoder = new JpegDecoder();
        decoder.setTiffRgb();
        assertDoesNotThrow(() -> decoder.decode(byteSourceFile));
    }

    @Test
    public void testDecodeWithoutTiffRgb() {
        final File inputFile = TestResources.resourceToFile("/IMAGING-220/standard-jpeg-48eb4251935b4ca8b26d1859ea525c1b42ae0c78.jpeg");
        final ByteSource byteSourceFile = ByteSource.file(inputFile);
        JpegDecoder decoder = new JpegDecoder();
        assertDoesNotThrow(() -> decoder.decode(byteSourceFile));
    }
}