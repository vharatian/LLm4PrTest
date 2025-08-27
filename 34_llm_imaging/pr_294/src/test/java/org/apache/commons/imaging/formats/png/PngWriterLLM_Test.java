package org.apache.commons.imaging.formats.png;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PngWriterLLM_Test {

    @Test
    public void testWriteImageWithDefaultPaletteFactory() throws ImagingException, IOException {
        PngWriter pngWriter = new PngWriter();
        BufferedImage image = mock(BufferedImage.class);
        when(image.getWidth()).thenReturn(100);
        when(image.getHeight()).thenReturn(100);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PngImagingParameters params = new PngImagingParameters();

        pngWriter.writeImage(image, os, params);

        assertTrue(os.size() > 0, "Output stream should have data written to it.");
    }

    @Test
    public void testWriteImageWithCustomPaletteFactory() throws ImagingException, IOException {
        PngWriter pngWriter = new PngWriter();
        BufferedImage image = mock(BufferedImage.class);
        when(image.getWidth()).thenReturn(100);
        when(image.getHeight()).thenReturn(100);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PngImagingParameters params = new PngImagingParameters();
        PaletteFactory customPaletteFactory = mock(PaletteFactory.class);
        when(customPaletteFactory.hasTransparency(image)).thenReturn(true);
        when(customPaletteFactory.isGrayscale(image)).thenReturn(false);

        pngWriter.writeImage(image, os, params, customPaletteFactory);

        assertTrue(os.size() > 0, "Output stream should have data written to it.");
        verify(customPaletteFactory).hasTransparency(image);
        verify(customPaletteFactory).isGrayscale(image);
    }

    @Test
    public void testWriteImageWithNullPaletteFactory() throws ImagingException, IOException {
        PngWriter pngWriter = new PngWriter();
        BufferedImage image = mock(BufferedImage.class);
        when(image.getWidth()).thenReturn(100);
        when(image.getHeight()).thenReturn(100);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PngImagingParameters params = new PngImagingParameters();

        pngWriter.writeImage(image, os, params, null);

        assertTrue(os.size() > 0, "Output stream should have data written to it.");
    }
}