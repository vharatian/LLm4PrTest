package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FractionLLM_Test {

    @Test
    public void testGetFractionDouble() {
        // Test for the changes in getFraction(double value) method
        Fraction f;

        // Test with a value that requires multiple iterations
        f = Fraction.getFraction(0.3333333333333333d);
        assertEquals(1, f.getNumerator());
        assertEquals(3, f.getDenominator());

        // Test with a value that should result in an ArithmeticException due to too many iterations
        assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1.0d / 100000000d));
    }
}