package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class RangeLLM_Test extends AbstractLangTest {

    private Range<Integer> intRange = Range.of(10, 20);

    @Test
    public void testElementCompareToNull() {
        // Test that elementCompareTo throws NullPointerException when element is null
        assertThrows(NullPointerException.class, () -> intRange.elementCompareTo(null));
    }

    @Test
    public void testFitNull() {
        // Test that fit throws NullPointerException when element is null
        assertThrows(NullPointerException.class, () -> intRange.fit(null));
    }

    @Test
    public void testElementCompareTo() {
        // Test elementCompareTo with valid elements
        assertEquals(-1, intRange.elementCompareTo(5));
        assertEquals(0, intRange.elementCompareTo(10));
        assertEquals(0, intRange.elementCompareTo(15));
        assertEquals(0, intRange.elementCompareTo(20));
        assertEquals(1, intRange.elementCompareTo(25));
    }

    @Test
    public void testFit() {
        // Test fit with valid elements
        assertEquals(intRange.getMinimum(), intRange.fit(Integer.MIN_VALUE));
        assertEquals(intRange.getMinimum(), intRange.fit(intRange.getMinimum()));
        assertEquals(intRange.getMaximum(), intRange.fit(Integer.MAX_VALUE));
        assertEquals(intRange.getMaximum(), intRange.fit(intRange.getMaximum()));
        assertEquals(15, intRange.fit(15));
    }
}