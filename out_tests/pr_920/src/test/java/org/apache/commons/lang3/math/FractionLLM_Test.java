package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FractionLLM_Test {

    @Test
    public void testGreatestCommonDivisor() {
        // Test cases for greatestCommonDivisor method
        assertEquals(1, invokeGreatestCommonDivisor(1, 1));
        assertEquals(1, invokeGreatestCommonDivisor(1, 2));
        assertEquals(2, invokeGreatestCommonDivisor(2, 4));
        assertEquals(5, invokeGreatestCommonDivisor(10, 15));
        assertEquals(1, invokeGreatestCommonDivisor(Integer.MAX_VALUE, Integer.MAX_VALUE - 1));
        assertEquals(1, invokeGreatestCommonDivisor(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2));
        assertThrows(ArithmeticException.class, () -> invokeGreatestCommonDivisor(Integer.MIN_VALUE, 0));
        assertThrows(ArithmeticException.class, () -> invokeGreatestCommonDivisor(0, Integer.MIN_VALUE));
    }

    private int invokeGreatestCommonDivisor(int u, int v) {
        try {
            java.lang.reflect.Method method = Fraction.class.getDeclaredMethod("greatestCommonDivisor", int.class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(null, u, v);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}