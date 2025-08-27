package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FractionLLM_Test {

    @Test
    public void testFactory_int_int_int_withFinalNumeratorValue() {
        // Test case for the change in getFraction(int whole, int numerator, int denominator)
        Fraction f;
        
        // Test with positive whole number
        f = Fraction.getFraction(1, 1, 2);
        assertEquals(3, f.getNumerator());
        assertEquals(2, f.getDenominator());

        // Test with negative whole number
        f = Fraction.getFraction(-1, 1, 2);
        assertEquals(-3, f.getNumerator());
        assertEquals(2, f.getDenominator());

        // Test with zero whole number
        f = Fraction.getFraction(0, 1, 2);
        assertEquals(1, f.getNumerator());
        assertEquals(2, f.getDenominator());

        // Test with zero numerator
        f = Fraction.getFraction(1, 0, 2);
        assertEquals(2, f.getNumerator());
        assertEquals(2, f.getDenominator());

        // Test with zero whole number and numerator
        f = Fraction.getFraction(0, 0, 2);
        assertEquals(0, f.getNumerator());
        assertEquals(2, f.getDenominator());

        // Test with large values
        f = Fraction.getFraction(1000, 500, 2);
        assertEquals(2000, f.getNumerator());
        assertEquals(2, f.getDenominator());

        // Test with edge cases
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, -6, -10));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(-1, -6, 10));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(-1, 6, -10));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(0, 1, 0));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, 2, 0));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(-1, -3, 0));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MAX_VALUE, 1, 2));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(-Integer.MAX_VALUE, 1, 2));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(0, 4, Integer.MIN_VALUE));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, 1, Integer.MAX_VALUE));
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(-1, 2, Integer.MAX_VALUE));
    }
}