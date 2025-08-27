package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testCreateNumberWithInvalidExponentPosition() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1.23e4.5"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1.23e.45"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1.23e4.5D"));
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1.23e.45F"));
    }
}