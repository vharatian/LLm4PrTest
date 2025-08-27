package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ColorCmy class.
 */
public class ColorCmyLLM_Test {

    /**
     * Test to ensure that the constant values for colors are not null.
     */
    @Test
    public void testColorConstantsNotNull() {
        assertNotNull(ColorCmy.CYAN, "CYAN constant should not be null");
        assertNotNull(ColorCmy.MAGENTA, "MAGENTA constant should not be null");
        assertNotNull(ColorCmy.YELLOW, "YELLOW constant should not be null");
        assertNotNull(ColorCmy.BLACK, "BLACK constant should not be null");
        assertNotNull(ColorCmy.WHITE, "WHITE constant should not be null");
        assertNotNull(ColorCmy.RED, "RED constant should not be null");
        assertNotNull(ColorCmy.GREEN, "GREEN constant should not be null");
        assertNotNull(ColorCmy.BLUE, "BLUE constant should not be null");
    }
}