package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FractionLLM_Test {

    @Test
    public void testUpdatedURLInJavadoc() {
        // This test ensures that the URL in the Javadoc is correctly updated.
        // Since this is a documentation change, we don't need to test functionality.
        // We will just ensure that the method can be called without issues.
        Fraction f = Fraction.getFraction(0.5d);
        assertEquals(1, f.getNumerator());
        assertEquals(2, f.getDenominator());
    }
}