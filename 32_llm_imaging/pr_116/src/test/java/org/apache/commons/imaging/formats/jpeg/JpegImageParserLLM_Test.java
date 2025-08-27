package org.apache.commons.imaging.formats.jpeg;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.jpeg.segments.Segment;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JpegImageParserLLM_Test {

    @Test
    public void testGetDefaultParameters() {
        JpegImageParser parser = new JpegImageParser();
        JpegImagingParameters params = parser.getDefaultParameters();
        assertNotNull(params);
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        JpegImagingParameters params = parser.getDefaultParameters();
        BufferedImage image = parser.getBufferedImage(byteSource, params);
        assertNotNull(image);
    }

    @Test
    public void testReadSegments() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        List<Segment> segments = parser.readSegments(byteSource, new int[]{JpegConstants.SOF0_MARKER}, true);
        assertNotNull(segments);
        assertFalse(segments.isEmpty());
    }

    @Test
    public void testGetICCProfileBytes() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        JpegImagingParameters params = parser.getDefaultParameters();
        byte[] iccProfileBytes = parser.getICCProfileBytes(byteSource, params);
        assertNotNull(iccProfileBytes);
    }

    @Test
    public void testGetMetadata() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        JpegImagingParameters params = parser.getDefaultParameters();
        assertNotNull(parser.getMetadata(byteSource, params));
    }

    @Test
    public void testGetExifMetadata() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        TiffImagingParameters params = new TiffImagingParameters();
        assertNotNull(parser.getExifMetadata(byteSource, params));
    }

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        XmpImagingParameters params = new XmpImagingParameters();
        assertNotNull(parser.getXmpXml(byteSource, params));
    }

    @Test
    public void testGetPhotoshopMetadata() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        JpegImagingParameters params = parser.getDefaultParameters();
        assertNotNull(parser.getPhotoshopMetadata(byteSource, params));
    }

    @Test
    public void testGetImageSize() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        JpegImagingParameters params = parser.getDefaultParameters();
        Dimension dimension = parser.getImageSize(byteSource, params);
        assertNotNull(dimension);
    }

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.array(new byte[]{/* some valid JPEG byte data */});
        JpegImagingParameters params = parser.getDefaultParameters();
        assertNotNull(parser.getImageInfo(byteSource, params));
    }
}