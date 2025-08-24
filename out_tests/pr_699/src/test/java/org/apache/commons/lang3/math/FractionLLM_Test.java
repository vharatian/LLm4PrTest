package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FractionLLM_Test {

    @Test
    public void testHashCodeInitialization() {
        Fraction f = Fraction.getFraction(3, 5);
        assertEquals(0, f.hashCode);
        f.hashCode();
        assertEquals(f.hashCode(), f.hashCode);
    }

    @Test
    public void testToStringInitialization() {
        Fraction f = Fraction.getFraction(3, 5);
        assertNull(f.toString);
        assertEquals("3/5", f.toString());
        assertEquals("3/5", f.toString);
    }

    @Test
    public void testToProperStringInitialization() {
        Fraction f = Fraction.getFraction(3, 5);
        assertNull(f.toProperString);
        assertEquals("3/5", f.toProperString());
        assertEquals("3/5", f.toProperString);
    }
}