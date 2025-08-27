package org.apache.commons.imaging.formats.icns;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IcnsImageParserLLM_Test {

    @Test
    public void testGetDefaultParameters() {
        IcnsImageParser parser = new IcnsImageParser();
        assertNotNull(parser.getDefaultParameters());
    }

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        IcnsImageParser parser = new IcnsImageParser();
        ByteSource byteSource = createTestByteSource();
        IcnsImagingParameters params = parser.getDefaultParameters();
        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);
        assertNotNull(imageInfo);
        assertEquals("Icns", imageInfo.getFormatName());
    }

    @Test
    public void testGetImageSize() throws ImageReadException, IOException {
        IcnsImageParser parser = new IcnsImageParser();
        ByteSource byteSource = createTestByteSource();
        IcnsImagingParameters params = parser.getDefaultParameters();
        Dimension dimension = parser.getImageSize(byteSource, params);
        assertNotNull(dimension);
        assertEquals(32, dimension.width);
        assertEquals(32, dimension.height);
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        IcnsImageParser parser = new IcnsImageParser();
        ByteSource byteSource = createTestByteSource();
        IcnsImagingParameters params = parser.getDefaultParameters();
        BufferedImage image = parser.getBufferedImage(byteSource, params);
        assertNotNull(image);
        assertEquals(32, image.getWidth());
        assertEquals(32, image.getHeight());
    }

    @Test
    public void testWriteImage() throws ImageWriteException, IOException {
        IcnsImageParser parser = new IcnsImageParser();
        BufferedImage src = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        IcnsImagingParameters params = parser.getDefaultParameters();
        parser.writeImage(src, os, params);
        assertTrue(os.size() > 0);
    }

    private ByteSource createTestByteSource() {
        return new ByteSource() {
            @Override
            public InputStream getInputStream() throws IOException {
                return getClass().getResourceAsStream("/test.icns");
            }

            @Override
            public byte[] getBlock(int start, int length) throws IOException {
                return new byte[0];
            }

            @Override
            public long getLength() throws IOException {
                return 0;
            }

            @Override
            public String getDescription() {
                return "Test ByteSource";
            }
        };
    }
}