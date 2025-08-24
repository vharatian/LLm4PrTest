package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testGetMantissaWithInvalidInput() {
        // Test with a string that is too short to have a valid mantissa
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1e-"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("+"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("e"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("."));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1e"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1."));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-e"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("+e"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-1e"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("+1e"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-."));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("+."));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-1."));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("+1."));
    }

    @Test
    public void testGetMantissaWithValidInput() {
        // Test with valid strings that should not throw an exception
        NumberUtils.createNumber("1.23");
        NumberUtils.createNumber("-1.23");
        NumberUtils.createNumber("+1.23");
        NumberUtils.createNumber("1e3");
        NumberUtils.createNumber("-1e3");
        NumberUtils.createNumber("+1e3");
        NumberUtils.createNumber("1.23e3");
        NumberUtils.createNumber("-1.23e3");
        NumberUtils.createNumber("+1.23e3");
    }
}