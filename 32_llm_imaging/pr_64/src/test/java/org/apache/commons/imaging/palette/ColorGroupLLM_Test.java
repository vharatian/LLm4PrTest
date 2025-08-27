package org.apache.commons.imaging.palette;

import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ColorGroupLLM_Test {

    @Test
    void testConstructorWithNonEmptyColorCounts() throws ImageWriteException {
        List<ColorCount> colorCounts = Arrays.asList(
                new ColorCount(255, 0, 0, 0, 1),
                new ColorCount(0, 255, 0, 0, 1)
        );
        ColorGroup colorGroup = new ColorGroup(colorCounts, false);

        assertEquals(2, colorGroup.totalPoints);
        assertEquals(255, colorGroup.maxRed);
        assertEquals(0, colorGroup.minRed);
        assertEquals(255, colorGroup.maxGreen);
        assertEquals(0, colorGroup.minGreen);
        assertEquals(0, colorGroup.maxBlue);
        assertEquals(0, colorGroup.minBlue);
        assertEquals(0, colorGroup.maxAlpha);
        assertEquals(0, colorGroup.minAlpha);
    }

    @Test
    void testContains() throws ImageWriteException {
        List<ColorCount> colorCounts = Arrays.asList(
                new ColorCount(255, 0, 0, 0, 1),
                new ColorCount(0, 255, 0, 0, 1)
        );
        ColorGroup colorGroup = new ColorGroup(colorCounts, false);

        assertTrue(colorGroup.contains(0xFF000000));
        assertTrue(colorGroup.contains(0xFF00FF00));
        assertFalse(colorGroup.contains(0xFFFF0000));
    }

    @Test
    void testGetMedianValue() throws ImageWriteException {
        List<ColorCount> colorCounts = Arrays.asList(
                new ColorCount(255, 0, 0, 0, 1),
                new ColorCount(0, 255, 0, 0, 1)
        );
        ColorGroup colorGroup = new ColorGroup(colorCounts, false);

        int medianValue = colorGroup.getMedianValue();
        assertEquals(0xFF7F7F00, medianValue);
    }

    @Test
    void testGetColorCounts() throws ImageWriteException {
        List<ColorCount> colorCounts = Arrays.asList(
                new ColorCount(255, 0, 0, 0, 1),
                new ColorCount(0, 255, 0, 0, 1)
        );
        ColorGroup colorGroup = new ColorGroup(colorCounts, false);

        List<ColorCount> copiedColorCounts = colorGroup.getColorCounts();
        assertEquals(colorCounts.size(), copiedColorCounts.size());
        assertNotSame(colorCounts, copiedColorCounts);
    }
}