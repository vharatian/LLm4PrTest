package org.apache.commons.imaging.formats.dcx;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.formats.pcx.PcxImagingParameters;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class DcxImageParserLLM_Test {

    @Test
    public void testGetDefaultParameters() {
        DcxImageParser parser = new DcxImageParser();
        PcxImagingParameters params = parser.getDefaultParameters();
        assertNotNull(params);
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        DcxImageParser parser = new DcxImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{/* DCX file bytes */});
        PcxImagingParameters params = new PcxImagingParameters();
        BufferedImage image = parser.getBufferedImage(byteSource, params);
        assertNotNull(image);
    }

    @Test
    public void testWriteImage() throws ImageWriteException, IOException {
        DcxImageParser parser = new DcxImageParser();
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PcxImagingParameters params = new PcxImagingParameters();
        parser.writeImage(image, os, params);
        assertTrue(os.size() > 0);
    }

    @Test
    public void testDumpImageFile() throws ImageReadException, IOException {
        DcxImageParser parser = new DcxImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{/* DCX file bytes */});
        PrintWriter pw = new PrintWriter(System.out);
        boolean result = parser.dumpImageFile(pw, byteSource);
        assertTrue(result);
    }

    @Test
    public void testGetAllBufferedImages() throws ImageReadException, IOException {
        DcxImageParser parser = new DcxImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{/* DCX file bytes */});
        PcxImagingParameters params = new PcxImagingParameters();
        assertNotNull(parser.getAllBufferedImages(byteSource));
    }
}