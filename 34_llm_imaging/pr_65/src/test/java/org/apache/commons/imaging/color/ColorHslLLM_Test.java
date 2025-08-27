package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for the updated ColorHsl class.
 */
public class ColorHslLLM_Test {

    /**
     * Test to ensure the ColorHsl constants are not null.
     */
    @Test
    public void testColorHslConstantsNotNull() {
        assertNotNull(ColorHsl.BLACK);
        assertNotNull(ColorHsl.WHITE);
        assertNotNull(ColorHsl.RED);
        assertNotNull(ColorHsl.GREEN);
        assertNotNull(ColorHsl.BLUE);
    }
}