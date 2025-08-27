package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for additional tests for ColorHsv.
 */
public class ColorHsvLLM_Test {

    /**
     * Test to ensure the ColorHsv constants are initialized correctly.
     */
    @Test
    public void testColorHsvConstants() {
        assertNotNull(ColorHsv.BLACK, "BLACK constant should be initialized");
        assertNotNull(ColorHsv.WHITE, "WHITE constant should be initialized");
        assertNotNull(ColorHsv.RED, "RED constant should be initialized");
        assertNotNull(ColorHsv.GREEN, "GREEN constant should be initialized");
        assertNotNull(ColorHsv.BLUE, "BLUE constant should be initialized");
    }
}