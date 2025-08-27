package org.apache.commons.imaging.palette;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedianCutQuantizerLLM_Test {

    private MedianCutQuantizer quantizer;
    private BufferedImage image;
    private MedianCut medianCut;

    @BeforeEach
    public void setUp() {
        quantizer = new MedianCutQuantizer(true);
        image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        medianCut = new MedianCut();
    }

    @Test
    public void testProcessThrowsImageWriteExceptionForEmptyColorGroup() {
        // Create a mock MedianCut that always returns an empty color group
        MedianCut mockMedianCut = new MedianCut() {
            @Override
            public boolean performNextMedianCut(List<ColorGroup> colorGroups, boolean ignoreAlpha) {
                colorGroups.add(new ColorGroup(new ArrayList<>(), ignoreAlpha));
                return true;
            }
        };

        assertThrows(ImageWriteException.class, () -> {
            quantizer.process(image, 256, mockMedianCut);
        });
    }

    @Test
    public void testProcessWithNonEmptyColorGroup() throws ImageWriteException {
        // Create a mock MedianCut that returns a non-empty color group
        MedianCut mockMedianCut = new MedianCut() {
            @Override
            public boolean performNextMedianCut(List<ColorGroup> colorGroups, boolean ignoreAlpha) {
                List<ColorCount> colorCounts = new ArrayList<>();
                colorCounts.add(new ColorCount(0xff000000));
                colorGroups.add(new ColorGroup(colorCounts, ignoreAlpha));
                return true;
            }
        };

        Palette palette = quantizer.process(image, 256, mockMedianCut);
        assertTrue(palette != null);
    }
}