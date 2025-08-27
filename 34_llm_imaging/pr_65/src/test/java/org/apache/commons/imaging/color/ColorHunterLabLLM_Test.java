package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ColorHunterLab class.
 */
public class ColorHunterLabLLM_Test {

    /**
     * Test to ensure that the constant BLACK is not null.
     */
    @Test
    public void testBlackConstant() {
        assertNotNull(ColorHunterLab.BLACK);
    }

    /**
     * Test to ensure that the constant WHITE is not null.
     */
    @Test
    public void testWhiteConstant() {
        assertNotNull(ColorHunterLab.WHITE);
    }

    /**
     * Test to ensure that the constant RED is not null.
     */
    @Test
    public void testRedConstant() {
        assertNotNull(ColorHunterLab.RED);
    }

    /**
     * Test to ensure that the constant GREEN is not null.
     */
    @Test
    public void testGreenConstant() {
        assertNotNull(ColorHunterLab.GREEN);
    }

    /**
     * Test to ensure that the constant BLUE is not null.
     */
    @Test
    public void testBlueConstant() {
        assertNotNull(ColorHunterLab.BLUE);
    }
}