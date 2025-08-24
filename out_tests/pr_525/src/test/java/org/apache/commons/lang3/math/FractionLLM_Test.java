package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FractionLLM_Test {

    @Test
    public void testGetFractionString_NullPointerException() {
        assertThrows(NullPointerException.class, () -> Fraction.getFraction(null));
    }

    @Test
    public void testMultiplyBy_NullPointerException() {
        Fraction f1 = Fraction.getFraction(3, 5);
        assertThrows(NullPointerException.class, () -> f1.multiplyBy(null));
    }

    @Test
    public void testDivideBy_NullPointerException() {
        Fraction f1 = Fraction.getFraction(3, 5);
        assertThrows(NullPointerException.class, () -> f1.divideBy(null));
    }
}