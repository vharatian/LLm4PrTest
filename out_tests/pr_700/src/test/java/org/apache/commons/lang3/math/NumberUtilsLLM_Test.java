package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testCreateNumberWithFinalVariables() {
        // Test case to ensure that the final variables are handled correctly in createNumber method
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5"), "createNumber(String) failed for float value");
        assertEquals(Integer.valueOf("12345"), NumberUtils.createNumber("12345"), "createNumber(String) failed for integer value");
        assertEquals(Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5D"), "createNumber(String) failed for double value with D");
        assertEquals(Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5d"), "createNumber(String) failed for double value with d");
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5F"), "createNumber(String) failed for float value with F");
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5f"), "createNumber(String) failed for float value with f");
        assertEquals(Long.valueOf("12345"), NumberUtils.createNumber("12345L"), "createNumber(String) failed for long value with L");
        assertEquals(Long.valueOf("12345"), NumberUtils.createNumber("12345l"), "createNumber(String) failed for long value with l");
    }

    @Test
    public void testCreateNumberWithInvalidFinalVariables() {
        // Test case to ensure that invalid final variables are handled correctly in createNumber method
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5ff"), "createNumber(String) should have failed for invalid float value with ff");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5FF"), "createNumber(String) should have failed for invalid float value with FF");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5dd"), "createNumber(String) should have failed for invalid double value with dd");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5DD"), "createNumber(String) should have failed for invalid double value with DD");
    }
}