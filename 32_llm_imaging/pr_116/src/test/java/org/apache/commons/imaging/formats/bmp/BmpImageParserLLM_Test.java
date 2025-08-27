package org.apache.commons.imaging.formats.bmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.junit.jupiter.api.Test;

public class BmpImageParserLLM_Test {

    @Test
    public void testGetDefaultParameters() {
        BmpImageParser parser = new BmpImageParser();
        BmpImagingParameters params = parser.getDefaultParameters();
        assertEquals(BmpImagingParameters.class, params.getClass(), "Expected default parameters to be of type BmpImagingParameters");
    }

    @Test
    public void testGetImageSize() throws ImageReadException, IOException {
        String file = "/images/bmp/IMAGING-264/test-72_6-dpi.bmp";
        File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        BmpImageParser parser = new BmpImageParser();
        Dimension dimension = parser.getImageSize(bmp, new BmpImagingParameters());
        assertEquals(73, dimension.getWidth(), "Expected width to be 73");
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        String file = "/images/bmp/IMAGING-264/test-72_6-dpi.bmp";
        File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        BmpImageParser parser = new BmpImageParser();
        BufferedImage image = parser.getBufferedImage(bmp, new BmpImagingParameters());
        assertEquals(73, image.getWidth(), "Expected image width to be 73");
    }

    @Test
    public void testWriteImage() throws ImageReadException, IOException, ImageWriteException {
        String file = "/images/bmp/IMAGING-264/test-72_6-dpi.bmp";
        File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        BmpImageParser parser = new BmpImageParser();
        BufferedImage image = parser.getBufferedImage(bmp, new BmpImagingParameters());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        parser.writeImage(image, baos, new BmpImagingParameters());

        ByteSourceInputStream byteSource = new ByteSourceInputStream(new ByteArrayInputStream(baos.toByteArray()), "test");
        BufferedImage writtenImage = parser.getBufferedImage(byteSource, new BmpImagingParameters());
        assertEquals(image.getWidth(), writtenImage.getWidth(), "Expected written image width to match original");
        assertEquals(image.getHeight(), writtenImage.getHeight(), "Expected written image height to match original");
    }

    @Test
    public void testGetImageInfoWithInvalidParams() throws ImageReadException, IOException {
        String file = "/images/bmp/IMAGING-264/test-72_6-dpi.bmp";
        File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        BmpImageParser parser = new BmpImageParser();
        assertThrows(ImageReadException.class, () -> parser.getImageInfo(bmp, null));
    }
}