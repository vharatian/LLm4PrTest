package org.apache.commons.text.numbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParsedDecimalLLM_Test {

    @Test
    void testFromDouble() {
        ParsedDecimal pd = ParsedDecimal.from(1.2);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{1, 2}, pd.digits);
        assertEquals(2, pd.digitCount);
        assertEquals(-1, pd.exponent);
    }

    @Test
    void testFromDoubleNegative() {
        ParsedDecimal pd = ParsedDecimal.from(-0.00971);
        assertTrue(pd.negative);
        assertArrayEquals(new int[]{9, 7, 1}, pd.digits);
        assertEquals(3, pd.digitCount);
        assertEquals(-5, pd.exponent);
    }

    @Test
    void testFromDoubleZero() {
        ParsedDecimal pd = ParsedDecimal.from(0.0);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{0}, pd.digits);
        assertEquals(1, pd.digitCount);
        assertEquals(0, pd.exponent);
    }

    @Test
    void testFromDoubleNegativeZero() {
        ParsedDecimal pd = ParsedDecimal.from(-0.0);
        assertTrue(pd.negative);
        assertArrayEquals(new int[]{0}, pd.digits);
        assertEquals(1, pd.digitCount);
        assertEquals(0, pd.exponent);
    }

    @Test
    void testFromDoubleLarge() {
        ParsedDecimal pd = ParsedDecimal.from(56300);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{5, 6, 3}, pd.digits);
        assertEquals(3, pd.digitCount);
        assertEquals(2, pd.exponent);
    }

    @Test
    void testRound() {
        ParsedDecimal pd = ParsedDecimal.from(1.2345);
        pd.round(-2);
        assertArrayEquals(new int[]{1, 2, 3}, pd.digits);
        assertEquals(3, pd.digitCount);
        assertEquals(-2, pd.exponent);
    }

    @Test
    void testMaxPrecision() {
        ParsedDecimal pd = ParsedDecimal.from(1.2345);
        pd.maxPrecision(2);
        assertArrayEquals(new int[]{1, 2}, pd.digits);
        assertEquals(2, pd.digitCount);
        assertEquals(-1, pd.exponent);
    }

    @Test
    void testToPlainString() {
        ParsedDecimal pd = ParsedDecimal.from(1.2345);
        String result = pd.toPlainString(new FormatOptions() {
            @Override
            public boolean isIncludeFractionPlaceholder() {
                return true;
            }

            @Override
            public boolean isSignedZero() {
                return false;
            }

            @Override
            public char[] getDigits() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            }

            @Override
            public char getDecimalSeparator() {
                return '.';
            }

            @Override
            public char getGroupingSeparator() {
                return ',';
            }

            @Override
            public boolean isGroupThousands() {
                return false;
            }

            @Override
            public char getMinusSign() {
                return '-';
            }

            @Override
            public char[] getExponentSeparatorChars() {
                return new char[]{'E'};
            }

            @Override
            public boolean isAlwaysIncludeExponent() {
                return false;
            }
        });
        assertEquals("1.2345", result);
    }

    @Test
    void testToScientificString() {
        ParsedDecimal pd = ParsedDecimal.from(1.2345);
        String result = pd.toScientificString(new FormatOptions() {
            @Override
            public boolean isIncludeFractionPlaceholder() {
                return true;
            }

            @Override
            public boolean isSignedZero() {
                return false;
            }

            @Override
            public char[] getDigits() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            }

            @Override
            public char getDecimalSeparator() {
                return '.';
            }

            @Override
            public char getGroupingSeparator() {
                return ',';
            }

            @Override
            public boolean isGroupThousands() {
                return false;
            }

            @Override
            public char getMinusSign() {
                return '-';
            }

            @Override
            public char[] getExponentSeparatorChars() {
                return new char[]{'E'};
            }

            @Override
            public boolean isAlwaysIncludeExponent() {
                return false;
            }
        });
        assertEquals("1.2345E0", result);
    }

    @Test
    void testToEngineeringString() {
        ParsedDecimal pd = ParsedDecimal.from(12345);
        String result = pd.toEngineeringString(new FormatOptions() {
            @Override
            public boolean isIncludeFractionPlaceholder() {
                return true;
            }

            @Override
            public boolean isSignedZero() {
                return false;
            }

            @Override
            public char[] getDigits() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            }

            @Override
            public char getDecimalSeparator() {
                return '.';
            }

            @Override
            public char getGroupingSeparator() {
                return ',';
            }

            @Override
            public boolean isGroupThousands() {
                return false;
            }

            @Override
            public char getMinusSign() {
                return '-';
            }

            @Override
            public char[] getExponentSeparatorChars() {
                return new char[]{'E'};
            }

            @Override
            public boolean isAlwaysIncludeExponent() {
                return false;
            }
        });
        assertEquals("12.345E3", result);
    }
}