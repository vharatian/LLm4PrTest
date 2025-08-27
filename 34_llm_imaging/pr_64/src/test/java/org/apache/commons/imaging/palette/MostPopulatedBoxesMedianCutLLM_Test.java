package org.apache.commons.imaging.palette;

import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MostPopulatedBoxesMedianCutLLM_Test {

    @Test
    public void testPerformNextMedianCut() throws ImageWriteException {
        List<ColorCount> colorCounts = new ArrayList<>();
        colorCounts.add(new ColorCount(255, 0, 0, 0, 10));
        colorCounts.add(new ColorCount(0, 255, 0, 0, 20));
        colorCounts.add(new ColorCount(0, 0, 255, 0, 30));
        ColorGroup colorGroup = new ColorGroup(colorCounts, false);
        List<ColorGroup> colorGroups = new ArrayList<>();
        colorGroups.add(colorGroup);

        MostPopulatedBoxesMedianCut medianCut = new MostPopulatedBoxesMedianCut();
        boolean result = medianCut.performNextMedianCut(colorGroups, false);

        assertTrue(result);
        assertEquals(2, colorGroups.size());
    }

    @Test
    public void testPerformNextMedianCutWithIgnoreAlpha() throws ImageWriteException {
        List<ColorCount> colorCounts = new ArrayList<>();
        colorCounts.add(new ColorCount(255, 0, 0, 0, 10));
        colorCounts.add(new ColorCount(0, 255, 0, 0, 20));
        colorCounts.add(new ColorCount(0, 0, 255, 0, 30));
        ColorGroup colorGroup = new ColorGroup(colorCounts, true);
        List<ColorGroup> colorGroups = new ArrayList<>();
        colorGroups.add(colorGroup);

        MostPopulatedBoxesMedianCut medianCut = new MostPopulatedBoxesMedianCut();
        boolean result = medianCut.performNextMedianCut(colorGroups, true);

        assertTrue(result);
        assertEquals(2, colorGroups.size());
    }

    @Test
    public void testPerformNextMedianCutNoCutPossible() throws ImageWriteException {
        List<ColorCount> colorCounts = new ArrayList<>();
        colorCounts.add(new ColorCount(255, 0, 0, 0, 10));
        ColorGroup colorGroup = new ColorGroup(colorCounts, false);
        List<ColorGroup> colorGroups = new ArrayList<>();
        colorGroups.add(colorGroup);

        MostPopulatedBoxesMedianCut medianCut = new MostPopulatedBoxesMedianCut();
        boolean result = medianCut.performNextMedianCut(colorGroups, false);

        assertFalse(result);
        assertEquals(1, colorGroups.size());
    }
}