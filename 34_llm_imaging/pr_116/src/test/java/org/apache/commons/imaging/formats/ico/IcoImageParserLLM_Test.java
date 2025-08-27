package org.apache.commons.imaging.formats.ico;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.formats.ico.IcoImagingParameters;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IcoImageParserLLM_Test {

    @Test
    public void testGetDefaultParameters() {
        IcoImageParser parser = new IcoImageParser();
        IcoImagingParameters params = parser.getDefaultParameters();
        assertNotNull(params);
    }

    @Test
    public void testGetMetadata() throws IOException, ImageReadException {
        IcoImageParser parser = new IcoImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        IcoImagingParameters params = new IcoImagingParameters();
        assertNull(parser.getMetadata(byteSource, params));
    }

    @Test
    public void testGetImageInfo() throws IOException, ImageReadException {
        IcoImageParser parser = new IcoImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        IcoImagingParameters params = new IcoImagingParameters();
        assertNull(parser.getImageInfo(byteSource, params));
    }

    @Test
    public void testGetImageSize() throws IOException, ImageReadException {
        IcoImageParser parser = new IcoImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        IcoImagingParameters params = new IcoImagingParameters();
        assertNull(parser.getImageSize(byteSource, params));
    }

    @Test
    public void testGetICCProfileBytes() throws IOException, ImageReadException {
        IcoImageParser parser = new IcoImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        IcoImagingParameters params = new IcoImagingParameters();
        assertNull(parser.getICCProfileBytes(byteSource, params));
    }

    @Test
    public void testGetBufferedImage() throws IOException, ImageReadException {
        IcoImageParser parser = new IcoImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        IcoImagingParameters params = new IcoImagingParameters();
        assertThrows(ImageReadException.class, () -> parser.getBufferedImage(byteSource, params));
    }

    @Test
    public void testGetAllBufferedImages() throws IOException, ImageReadException {
        IcoImageParser parser = new IcoImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        List<BufferedImage> images = parser.getAllBufferedImages(byteSource);
        assertNotNull(images);
        assertTrue(images.isEmpty());
    }

    @Test
    public void testWriteImage() throws IOException, ImageWriteException {
        IcoImageParser parser = new IcoImageParser();
        BufferedImage src = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        OutputStream os = new ByteArrayOutputStream();
        IcoImagingParameters params = new IcoImagingParameters();
        parser.writeImage(src, os, params);
        assertTrue(((ByteArrayOutputStream) os).size() > 0);
    }
}