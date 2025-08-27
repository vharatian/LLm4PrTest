package org.apache.commons.imaging.palette;

import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongestAxisMedianCutLLM_Test {

    private LongestAxisMedianCut longestAxisMedianCut;
    private List<ColorGroup> colorGroups;

    @BeforeEach
    public void setUp() {
        longestAxisMedianCut = new LongestAxisMedianCut();
        colorGroups = new ArrayList<>();
    }

    @Test
    public void testPerformNextMedianCut_SingleColorGroup() throws ImageWriteException {
        ColorGroup colorGroup = new ColorGroup(createColorCounts(), false);
        colorGroups.add(colorGroup);

        boolean result = longestAxisMedianCut.performNextMedianCut(colorGroups, false);

        assertTrue(result);
        assertEquals(2, colorGroups.size());
    }

    @Test
    public void testPerformNextMedianCut_IgnoreAlpha() throws ImageWriteException {
        ColorGroup colorGroup = new ColorGroup(createColorCounts(), false);
        colorGroups.add(colorGroup);

        boolean result = longestAxisMedianCut.performNextMedianCut(colorGroups, true);

        assertTrue(result);
        assertEquals(2, colorGroups.size());
    }

    @Test
    public void testDoCut_SortingByColorComponent() throws ImageWriteException {
        ColorGroup colorGroup = new ColorGroup(createColorCounts(), false);
        colorGroups.add(colorGroup);

        longestAxisMedianCut.performNextMedianCut(colorGroups, false);

        ColorGroup less = colorGroups.get(0);
        ColorGroup more = colorGroups.get(1);

        assertTrue(less.colorCounts.get(less.colorCounts.size() - 1).red <= more.colorCounts.get(0).red);
    }

    private List<ColorCount> createColorCounts() {
        List<ColorCount> colorCounts = new ArrayList<>();
        colorCounts.add(new ColorCount(255, 0, 0, 0, 10));
        colorCounts.add(new ColorCount(0, 255, 0, 0, 20));
        colorCounts.add(new ColorCount(0, 0, 255, 0, 30));
        colorCounts.add(new ColorCount(0, 0, 0, 255, 40));
        return colorCounts;
    }
}