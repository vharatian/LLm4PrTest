package org.apache.commons.imaging.palette;

import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.color.ColorSpace;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class PaletteFactoryLLM_Test {

    @Test
    public void testMakeQuantizedRgbaPalette() {
        PaletteFactory paletteFactory = new PaletteFactory();
        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        try {
            Palette palette = paletteFactory.makeQuantizedRgbaPalette(image, true, 256);
            assertNotNull(palette);
        } catch (ImageWriteException e) {
            fail("ImageWriteException should not be thrown");
        }
    }

    @Test
    public void testMakeQuantizedRgbaPaletteThrowsException() {
        PaletteFactory paletteFactory = new PaletteFactory();
        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        try {
            paletteFactory.makeQuantizedRgbaPalette(image, true, -1);
            fail("ImageWriteException should be thrown for invalid max value");
        } catch (ImageWriteException e) {
            assertTrue(true);
        }
    }
}