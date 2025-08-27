package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    /**
     * Test to ensure the refactored variable name 'hexPrefixes' works correctly in createNumber method.
     */
    @Test
    public void testCreateNumberWithHexPrefixes() {
        assertEquals(255, NumberUtils.createNumber("0xFF").intValue(), "createNumber(String) with 0x prefix failed");
        assertEquals(255, NumberUtils.createNumber("0XFF").intValue(), "createNumber(String) with 0X prefix failed");
        assertEquals(255, NumberUtils.createNumber("#FF").intValue(), "createNumber(String) with # prefix failed");
        assertEquals(-255, NumberUtils.createNumber("-0xFF").intValue(), "createNumber(String) with -0x prefix failed");
        assertEquals(-255, NumberUtils.createNumber("-0XFF").intValue(), "createNumber(String) with -0X prefix failed");
        assertEquals(-255, NumberUtils.createNumber("-#FF").intValue(), "createNumber(String) with -# prefix failed");
    }

    /**
     * Test to ensure the refactored variable name 'hexPrefixes' does not affect invalid hex number parsing.
     */
    @Test
    public void testCreateNumberWithInvalidHexPrefixes() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("0xGHI"), "createNumber(String) with invalid 0x prefix should fail");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("0XGHI"), "createNumber(String) with invalid 0X prefix should fail");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("#GHI"), "createNumber(String) with invalid # prefix should fail");
    }
}