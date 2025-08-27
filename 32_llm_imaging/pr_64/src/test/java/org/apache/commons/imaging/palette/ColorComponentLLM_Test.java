package org.apache.commons.imaging.palette;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColorComponentLLM_Test {

    @Test
    public void testAlphaComponent() {
        int argb = 0x80FFFFFF; // 50% transparent white
        int expectedAlpha = 0x80;
        assertEquals(expectedAlpha, ColorComponent.ALPHA.argbComponent(argb));
    }

    @Test
    public void testRedComponent() {
        int argb = 0xFFFF0000; // Fully opaque red
        int expectedRed = 0xFF;
        assertEquals(expectedRed, ColorComponent.RED.argbComponent(argb));
    }

    @Test
    public void testGreenComponent() {
        int argb = 0xFF00FF00; // Fully opaque green
        int expectedGreen = 0xFF;
        assertEquals(expectedGreen, ColorComponent.GREEN.argbComponent(argb));
    }

    @Test
    public void testBlueComponent() {
        int argb = 0xFF0000FF; // Fully opaque blue
        int expectedBlue = 0xFF;
        assertEquals(expectedBlue, ColorComponent.BLUE.argbComponent(argb));
    }

    // Test to ensure the enum constants are correctly documented
    @Test
    public void testEnumDocumentation() {
        assertNotNull(ColorComponent.class.getAnnotation(Since.class));
        Since sinceAnnotation = ColorComponent.class.getAnnotation(Since.class);
        assertEquals("1.0-alpha1", sinceAnnotation.value());
    }
}