package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FractionLLM_Test extends AbstractLangTest {

    @Test
    public void testPowJavadocLink() {
        Fraction f = Fraction.getFraction(3, 5);
        assertEquals(Fraction.ONE, f.pow(0), "The power of any fraction raised to 0 should be ONE");
    }
}