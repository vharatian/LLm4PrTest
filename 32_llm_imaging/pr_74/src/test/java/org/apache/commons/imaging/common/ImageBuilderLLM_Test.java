package org.apache.commons.imaging.common;

import org.junit.Test;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import static org.junit.Assert.*;

public class ImageBuilderLLM_Test {

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageNegativeWidth() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(0, 0, -1, 5);
    }

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageZeroWidth() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(0, 0, 0, 5);
    }

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageNegativeHeight() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(0, 0, 5, -1);
    }

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageZeroHeight() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(0, 0, 5, 0);
    }

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageXOutsideRaster() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(10, 0, 5, 5);
    }

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageXPlusWidthOutsideRaster() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(6, 0, 5, 5);
    }

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageYOutsideRaster() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(0, 10, 5, 5);
    }

    @Test(expected = RasterFormatException.class)
    public void testGetSubimageYPlusHeightOutsideRaster() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.getSubimage(0, 6, 5, 5);
    }

    @Test
    public void testGetSubimageValid() {
        ImageBuilder builder = new ImageBuilder(10, 10, false);
        builder.setRGB(1, 1, 0xFF00FF00);
        BufferedImage subimage = builder.getSubimage(0, 0, 5, 5);
        assertEquals(0xFF00FF00, subimage.getRGB(1, 1));
    }
}