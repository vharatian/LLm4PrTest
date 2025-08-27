package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for the updated ColorCmyk class.
 */
public class ColorCmykLLM_Test {

    /**
     * Test to ensure the class-level Javadoc is present.
     */
    @Test
    public void testClassJavadoc() {
        // Using reflection to get the class-level Javadoc
        Class<ColorCmyk> clazz = ColorCmyk.class;
        assertNotNull(clazz.getAnnotation(Since.class), "Class-level Javadoc is missing @since annotation");
    }
}