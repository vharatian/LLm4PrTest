package org.apache.commons.imaging.palette;

import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.color.ColorSpace;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PaletteFactoryLLM_Test {

    @Test
    public void testFinishDivisionWithoutRemainderCheck() {
        PaletteFactory factory = new PaletteFactory();
        int[] mins = {0, 0, 0};
        int[] maxs = {1, 1, 1};
        ColorSpaceSubset subset = new ColorSpaceSubset(10, 6, mins, maxs);
        DivisionCandidate result = factory.finishDivision(subset, 0, 6, 5, 0);
        assertNotNull(result);
    }

    @Test
    public void testDivideWithVoidReturn() {
        PaletteFactory factory = new PaletteFactory();
        int[] table = new int[1 << (6 * 3)];
        List<ColorSpaceSubset> subsets = List.of(new ColorSpaceSubset(10, 6));
        factory.divide(subsets, 1, table, 6);
        assertEquals(1, subsets.size());
    }

    @Test
    public void testMakeQuantizedRgbPalette() {
        PaletteFactory factory = new PaletteFactory();
        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Palette palette = factory.makeQuantizedRgbPalette(image, 256);
        assertNotNull(palette);
    }
}