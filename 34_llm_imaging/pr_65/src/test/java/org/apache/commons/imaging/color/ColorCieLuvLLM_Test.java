package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for the updated ColorCieLuv class.
 */
public class ColorCieLuvLLM_Test {

    /**
     * Test to ensure the constants BLACK, WHITE, RED, GREEN, and BLUE are not null.
     */
    @Test
    public void testColorConstantsNotNull() {
        assertNotNull(ColorCieLuv.BLACK, "BLACK constant should not be null");
        assertNotNull(ColorCieLuv.WHITE, "WHITE constant should not be null");
        assertNotNull(ColorCieLuv.RED, "RED constant should not be null");
        assertNotNull(ColorCieLuv.GREEN, "GREEN constant should not be null");
        assertNotNull(ColorCieLuv.BLUE, "BLUE constant should not be null");
    }
}