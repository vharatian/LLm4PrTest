package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testCreateNumberWithHexPrefix() {
        assertEquals(0xFADE, NumberUtils.createNumber("0xFADE").intValue(), "createNumber(String) with hex prefix failed");
        assertEquals(0xFADE, NumberUtils.createNumber("0Xfade").intValue(), "createNumber(String) with hex prefix failed");
        assertEquals(-0xFADE, NumberUtils.createNumber("-0xFADE").intValue(), "createNumber(String) with hex prefix failed");
        assertEquals(-0xFADE, NumberUtils.createNumber("-0Xfade").intValue(), "createNumber(String) with hex prefix failed");
    }

    @Test
    public void testCreateNumberWithLengthCheck() {
        assertEquals(0x7FFFFFFFFFFFFFFFL, NumberUtils.createNumber("0x7FFFFFFFFFFFFFFF").longValue(), "createNumber(String) with length check failed");
        assertEquals(new BigInteger("8000000000000000", 16), NumberUtils.createNumber("0x8000000000000000"), "createNumber(String) with length check failed");
    }

    @Test
    public void testCreateNumberWithInvalidLength() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("0x80000000000000000"), "createNumber(String) with invalid length should fail");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("0x10000000000000000"), "createNumber(String) with invalid length should fail");
    }

    @Test
    public void testCreateNumberWithExponents() {
        assertEquals(Double.valueOf("1.1E200"), NumberUtils.createNumber("1.1E200"), "createNumber(String) with exponent failed");
        assertEquals(Float.valueOf("1.1E20"), NumberUtils.createNumber("1.1E20"), "createNumber(String) with exponent failed");
        assertEquals(Double.valueOf("-1.1E200"), NumberUtils.createNumber("-1.1E200"), "createNumber(String) with exponent failed");
        assertEquals(Double.valueOf("1.1E-200"), NumberUtils.createNumber("1.1E-200"), "createNumber(String) with exponent failed");
    }

    @Test
    public void testCreateNumberWithInvalidExponents() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1.1E200E"), "createNumber(String) with invalid exponent should fail");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1.1E200.5"), "createNumber(String) with invalid exponent should fail");
    }

    @Test
    public void testCreateNumberWithLeadingZeros() {
        assertEquals(12345, NumberUtils.createNumber("012345").intValue(), "createNumber(String) with leading zeros failed");
        assertEquals(-12345, NumberUtils.createNumber("-012345").intValue(), "createNumber(String) with leading zeros failed");
    }

    @Test
    public void testCreateNumberWithInvalidLeadingZeros() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("01234.5"), "createNumber(String) with invalid leading zeros should fail");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-01234.5"), "createNumber(String) with invalid leading zeros should fail");
    }

    @Test
    public void testCreateNumberWithDecimalPoints() {
        assertEquals(123.45, NumberUtils.createNumber("123.45").doubleValue(), "createNumber(String) with decimal points failed");
        assertEquals(-123.45, NumberUtils.createNumber("-123.45").doubleValue(), "createNumber(String) with decimal points failed");
    }

    @Test
    public void testCreateNumberWithInvalidDecimalPoints() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("123..45"), "createNumber(String) with invalid decimal points should fail");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-123..45"), "createNumber(String) with invalid decimal points should fail");
    }
}