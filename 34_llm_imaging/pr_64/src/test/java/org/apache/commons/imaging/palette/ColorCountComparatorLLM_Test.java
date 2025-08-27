package org.apache.commons.imaging.palette;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColorCountComparatorLLM_Test {

    @Test
    public void testCompareAlpha() {
        ColorCount c1 = new ColorCount(10, 20, 30, 40);
        ColorCount c2 = new ColorCount(10, 20, 30, 50);
        ColorCountComparator comparator = new ColorCountComparator(ColorComponent.ALPHA);
        assertTrue(comparator.compare(c1, c2) < 0);
    }

    @Test
    public void testCompareRed() {
        ColorCount c1 = new ColorCount(10, 20, 30, 40);
        ColorCount c2 = new ColorCount(20, 20, 30, 40);
        ColorCountComparator comparator = new ColorCountComparator(ColorComponent.RED);
        assertTrue(comparator.compare(c1, c2) < 0);
    }

    @Test
    public void testCompareGreen() {
        ColorCount c1 = new ColorCount(10, 20, 30, 40);
        ColorCount c2 = new ColorCount(10, 30, 30, 40);
        ColorCountComparator comparator = new ColorCountComparator(ColorComponent.GREEN);
        assertTrue(comparator.compare(c1, c2) < 0);
    }

    @Test
    public void testCompareBlue() {
        ColorCount c1 = new ColorCount(10, 20, 30, 40);
        ColorCount c2 = new ColorCount(10, 20, 40, 40);
        ColorCountComparator comparator = new ColorCountComparator(ColorComponent.BLUE);
        assertTrue(comparator.compare(c1, c2) < 0);
    }

    @Test
    public void testCompareEqual() {
        ColorCount c1 = new ColorCount(10, 20, 30, 40);
        ColorCount c2 = new ColorCount(10, 20, 30, 40);
        ColorCountComparator comparator = new ColorCountComparator(ColorComponent.RED);
        assertEquals(0, comparator.compare(c1, c2));
    }
}