package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testIsNumericSpace() {
        // Test cases for isNumericSpace method
        assertTrue(StringUtils.isNumericSpace("123"));
        assertTrue(StringUtils.isNumericSpace("12 3"));
        assertTrue(StringUtils.isNumericSpace("\u0967\u0968\u0969"));
        assertTrue(StringUtils.isNumericSpace("\u0967\u0968 \u0969"));
        assertFalse(StringUtils.isNumericSpace("ab2c"));
        assertFalse(StringUtils.isNumericSpace("12-3"));
        assertFalse(StringUtils.isNumericSpace("12.3"));
    }
}