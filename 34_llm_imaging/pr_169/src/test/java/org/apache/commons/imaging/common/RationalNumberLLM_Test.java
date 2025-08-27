package org.apache.commons.imaging.common;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.imaging.ImagingTest;
import org.apache.commons.imaging.internal.Debug;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class RationalNumberLLM_Test extends ImagingTest {

    /**
     * Test data for parameterized tests.
     */
    public static Stream<Double> data() {
        return Arrays.asList(
            0d, 0.1d, 0.01d, 0.001d, 0.0001d, 0.00001d, 0.000001d, 0.0000001d, 0.123456789d, 1d, 0.9d, 1.1d, 2.0d, 123.123d, 123123123.123d, 
            (double) 123 / 45678, (double) 12 / 34, (double) 1 / 2, (double) 1 / 3, (double) 1 / 4, (double) 1 / 5, (double) 1 / 6, (double) 1 / 7, (double) 1 / 8, (double) 1 / 9, (double) 3 / 2, (double) 2 / 3, (double) 3 / 4, (double) 4 / 5, (double) 5 / 6, (double) 6 / 7, (double) 7 / 8, (double) 8 / 9, (double) 3 / 5, (double) 3 / 6, (double) 3 / 7, (double) 3 / 8, (double) 3 / 9, 
            -0.1d, -0.01d, -0.001d, -0.0001d, -0.00001d, -0.000001d, -0.0000001d, -0.123456789d, -1d, -0.9d, -1.1d, -2.0d, -123.123d, -123123123.123d, 
            34d, 
            (double) Integer.MAX_VALUE, Integer.MAX_VALUE + 0.1, Integer.MAX_VALUE - 0.1, (double) -(Integer.MAX_VALUE), -(Integer.MAX_VALUE + 0.1), -(Integer.MAX_VALUE - 0.1), 
            (double) Long.MAX_VALUE, Long.MAX_VALUE + 0.1, Long.MAX_VALUE - 0.1, (double) -(Long.MAX_VALUE), -(Long.MAX_VALUE + 0.1), -(Long.MAX_VALUE - 0.1)
        ).stream();
    }

    /**
     * Parameterized test for RationalNumber creation and value conversion.
     */
    @ParameterizedTest
    @MethodSource("data")
    public void testRationalNumber(final double testValue) {
        final RationalNumber rational = RationalNumber.valueOf(testValue);
        final double difference = Math.abs(testValue - rational.doubleValue());
        final NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(15);
        Debug.debug("value: " + nf.format(testValue));
        Debug.debug("rational: " + rational);
        Debug.debug("difference: " + difference);
        Debug.debug();
    }

    /**
     * Test for unsigned integer support and negation of large unsigned values.
     */
    @Test
    public void testSpecialRationalNumber() {
        RationalNumber test = new RationalNumber(0xF5937B1F, 70_000_000, true);
        assertEquals(58.858331871428570, test.doubleValue(), 1.0e-14, "Unsigned integer support failed for double conversion");
        assertEquals(58.858334f, test.floatValue(), 1.0e-6f, "Float conversion failed");
        assertEquals(58L, test.longValue(), "Long value conversion failed");
        assertEquals(58, test.intValue(), "Int value conversion failed");
        assertThrows(NumberFormatException.class, () -> test.negate(), "Failed to detect negation of large unsigned value");
    }

    /**
     * Test for the new constructor with unsignedType parameter.
     */
    @Test
    public void testConstructorWithUnsignedType() {
        RationalNumber signedRational = new RationalNumber(10, 2, false);
        assertEquals(10L, signedRational.numerator);
        assertEquals(2L, signedRational.divisor);
        assertEquals(false, signedRational.unsignedType);

        RationalNumber unsignedRational = new RationalNumber(0xFFFFFFFF, 2, true);
        assertEquals(0xFFFFFFFFL, unsignedRational.numerator);
        assertEquals(2L, unsignedRational.divisor);
        assertEquals(true, unsignedRational.unsignedType);
    }

    /**
     * Test for the toDisplayString method with long numerator and divisor.
     */
    @Test
    public void testToDisplayString() {
        RationalNumber rational = new RationalNumber(123456789012345L, 1000000000000L, false);
        assertEquals("123.457", rational.toDisplayString());
    }
}