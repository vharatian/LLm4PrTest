package org.apache.commons.imaging.palette;

import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ColorGroupLLM_Test {

    @Test
    void testConstructorWithEmptyColorCounts() {
        List<ColorCount> emptyColorCounts = Collections.emptyList();
        boolean ignoreAlpha = false;

        ImageWriteException exception = assertThrows(ImageWriteException.class, () -> {
            new ColorGroup(emptyColorCounts, ignoreAlpha);
        });

        assertEquals("empty color_group", exception.getMessage());
    }

    @Test
    void testConstructorWithNonEmptyColorCounts() throws ImageWriteException {
        List<ColorCount> colorCounts = List.of(new ColorCount(255, 0, 0, 0, 1));
        boolean ignoreAlpha = false;

        ColorGroup colorGroup = new ColorGroup(colorCounts, ignoreAlpha);

        assertNotNull(colorGroup);
        assertEquals(1, colorGroup.totalPoints);
        assertEquals(0, colorGroup.minRed);
        assertEquals(0, colorGroup.maxRed);
        assertEquals(0, colorGroup.minGreen);
        assertEquals(0, colorGroup.maxGreen);
        assertEquals(0, colorGroup.minBlue);
        assertEquals(0, colorGroup.maxBlue);
        assertEquals(255, colorGroup.minAlpha);
        assertEquals(255, colorGroup.maxAlpha);
    }
}