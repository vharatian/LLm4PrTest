package org.apache.commons.imaging.formats.pcx;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.pcx.PcxImageParser.PcxHeader;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PcxImageParserLLM_Test {

    @Test
    public void testGetDefaultParameters() {
        PcxImageParser parser = new PcxImageParser();
        assertNotNull(parser.getDefaultParameters());
        assertTrue(parser.getDefaultParameters() instanceof PcxImagingParameters);
    }

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        PcxImageParser parser = new PcxImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        PcxImagingParameters params = new PcxImagingParameters();

        PcxHeader header = new PcxHeader(10, 5, 1, 8, 0, 0, 100, 100, 72, 72, new int[16], 0, 1, 100, 1, 0, 0);
        when(byteSource.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[128]));
        when(byteSource.getLength()).thenReturn(128L);

        ImageInfo info = parser.getImageInfo(byteSource, params);
        assertNotNull(info);
        assertEquals("PCX", info.getFormatName());
        assertEquals(ImageFormats.PCX, info.getFormat());
    }

    @Test
    public void testGetImageSize() throws ImageReadException, IOException {
        PcxImageParser parser = new PcxImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        PcxImagingParameters params = new PcxImagingParameters();

        PcxHeader header = new PcxHeader(10, 5, 1, 8, 0, 0, 100, 100, 72, 72, new int[16], 0, 1, 100, 1, 0, 0);
        when(byteSource.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[128]));
        when(byteSource.getLength()).thenReturn(128L);

        Dimension size = parser.getImageSize(byteSource, params);
        assertNotNull(size);
        assertEquals(101, size.width);
        assertEquals(101, size.height);
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        PcxImageParser parser = new PcxImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        PcxImagingParameters params = new PcxImagingParameters();

        PcxHeader header = new PcxHeader(10, 5, 1, 8, 0, 0, 100, 100, 72, 72, new int[16], 0, 1, 100, 1, 0, 0);
        when(byteSource.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[128]));
        when(byteSource.getLength()).thenReturn(128L);

        BufferedImage image = parser.getBufferedImage(byteSource, params);
        assertNotNull(image);
    }

    @Test
    public void testWriteImage() throws ImageWriteException, IOException {
        PcxImageParser parser = new PcxImageParser();
        BufferedImage src = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PcxImagingParameters params = new PcxImagingParameters();

        parser.writeImage(src, os, params);
        assertTrue(os.size() > 0);
    }
}