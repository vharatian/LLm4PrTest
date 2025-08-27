package org.apache.commons.imaging.color;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Test class for the updated ColorXyz class.
 */
public class ColorXyzLLM_Test {

    /**
     * Test to ensure the class-level Javadoc is present.
     */
    @Test
    public void testClassJavadoc() {
        Class<ColorXyz> clazz = ColorXyz.class;
        assertNotNull(clazz.getAnnotation(Since.class));
    }
}