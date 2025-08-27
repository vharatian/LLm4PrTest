package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FractionLLM_Test {

    @Test
    public void testAddNullPointerException() {
        Fraction f1 = Fraction.getFraction(1, 2);
        assertThrows(NullPointerException.class, () -> f1.add(null));
    }

    @Test
    public void testSubtractNullPointerException() {
        Fraction f1 = Fraction.getFraction(1, 2);
        assertThrows(NullPointerException.class, () -> f1.subtract(null));
    }
}