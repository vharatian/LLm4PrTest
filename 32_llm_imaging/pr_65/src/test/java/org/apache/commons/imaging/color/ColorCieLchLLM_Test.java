package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for the updated ColorCieLch class.
 */
public class ColorCieLchLLM_Test {

    /**
     * Test to ensure the constant BLACK is not null.
     */
    @Test
    public void testBlackConstant() {
        assertNotNull(ColorCieLch.BLACK);
    }

    /**
     * Test to ensure the constant WHITE is not null.
     */
    @Test
    public void testWhiteConstant() {
        assertNotNull(ColorCieLch.WHITE);
    }

    /**
     * Test to ensure the constant RED is not null.
     */
    @Test
    public void testRedConstant() {
        assertNotNull(ColorCieLch.RED);
    }

    /**
     * Test to ensure the constant GREEN is not null.
     */
    @Test
    public void testGreenConstant() {
        assertNotNull(ColorCieLch.GREEN);
    }

    /**
     * Test to ensure the constant BLUE is not null.
     */
    @Test
    public void testBlueConstant() {
        assertNotNull(ColorCieLch.BLUE);
    }
}