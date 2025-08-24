package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SystemUtilsLLM_Test {

    /**
     * Test for the deprecated LINE_SEPARATOR constant.
     * Verifies that the value matches System.lineSeparator().
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testLineSeparator() {
        assertEquals(System.lineSeparator(), SystemUtils.LINE_SEPARATOR);
    }
}