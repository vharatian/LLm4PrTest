package org.apache.commons.imaging.formats.pcx;

import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.PixelDensity;
import org.apache.commons.imaging.formats.pcx.PcxImagingParameters;
import org.apache.commons.imaging.formats.pcx.PcxWriter;
import org.apache.commons.imaging.formats.pcx.PcxConstants;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PcxWriterLLM_Test {

    @Test
    void testPcxWriterWithNullParams() throws ImageWriteException {
        PcxWriter writer = new PcxWriter(null);
        assertNotNull(writer);
    }

    @Test
    void testPcxWriterWithValidParams() throws ImageWriteException {
        PcxImagingParameters params = new PcxImagingParameters();
        params.setCompression(PcxConstants.PCX_COMPRESSION_UNCOMPRESSED);
        params.setBitDepth(8);
        params.setPlanes(1);
        params.setPixelDensity(PixelDensity.createFromPixelsPerInch(96, 96));

        PcxWriter writer = new PcxWriter(params);
        assertNotNull(writer);
    }

    @Test
    void testWriteImage() throws ImageWriteException, IOException {
        PcxImagingParameters params = new PcxImagingParameters();
        params.setCompression(PcxConstants.PCX_COMPRESSION_UNCOMPRESSED);
        params.setBitDepth(8);
        params.setPlanes(1);
        params.setPixelDensity(PixelDensity.createFromPixelsPerInch(96, 96));

        PcxWriter writer = new PcxWriter(params);

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writer.writeImage(image, os);

        assertTrue(os.size() > 0);
    }

    @Test
    void testWriteImageWithDefaultParams() throws ImageWriteException, IOException {
        PcxWriter writer = new PcxWriter(null);

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writer.writeImage(image, os);

        assertTrue(os.size() > 0);
    }
}