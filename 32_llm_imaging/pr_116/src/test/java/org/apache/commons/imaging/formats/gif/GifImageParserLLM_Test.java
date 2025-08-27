package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.XmpImagingParameters;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.Palette;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GifImageParserLLM_Test {

    @Test
    public void testGetDefaultParameters() {
        GifImageParser parser = new GifImageParser();
        assertNotNull(parser.getDefaultParameters());
        assertTrue(parser.getDefaultParameters() instanceof GifImagingParameters);
    }

    @Test
    public void testGetImageSize() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        when(byteSource.getInputStream()).thenReturn(inputStream);

        GifImageParser parser = new GifImageParser();
        GifImagingParameters params = new GifImagingParameters();

        Dimension dimension = parser.getImageSize(byteSource, params);
        assertNotNull(dimension);
    }

    @Test
    public void testGetMetadata() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        when(byteSource.getInputStream()).thenReturn(inputStream);

        GifImageParser parser = new GifImageParser();
        GifImagingParameters params = new GifImagingParameters();

        ImageMetadata metadata = parser.getMetadata(byteSource, params);
        assertNotNull(metadata);
    }

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        when(byteSource.getInputStream()).thenReturn(inputStream);

        GifImageParser parser = new GifImageParser();
        GifImagingParameters params = new GifImagingParameters();

        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);
        assertNotNull(imageInfo);
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        when(byteSource.getInputStream()).thenReturn(inputStream);

        GifImageParser parser = new GifImageParser();
        GifImagingParameters params = new GifImagingParameters();

        BufferedImage bufferedImage = parser.getBufferedImage(byteSource, params);
        assertNotNull(bufferedImage);
    }

    @Test
    public void testWriteImage() throws ImageWriteException, IOException {
        BufferedImage src = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        OutputStream os = new ByteArrayOutputStream();
        GifImagingParameters params = new GifImagingParameters();
        params.setXmpXml("<xmp>test</xmp>");

        GifImageParser parser = new GifImageParser();
        parser.writeImage(src, os, params);

        assertTrue(((ByteArrayOutputStream) os).size() > 0);
    }

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        when(byteSource.getInputStream()).thenReturn(inputStream);

        GifImageParser parser = new GifImageParser();
        XmpImagingParameters params = new XmpImagingParameters();

        String xmpXml = parser.getXmpXml(byteSource, params);
        assertNull(xmpXml);
    }
}