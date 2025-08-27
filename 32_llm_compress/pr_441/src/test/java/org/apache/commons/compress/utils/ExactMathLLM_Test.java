package org.apache.commons.compress.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExactMathLLM_Test {

    @Test
    public void testAddWithValidInputs() {
        assertEquals(5, ExactMath.add(2, 3L));
        assertEquals(-1, ExactMath.add(-2, 1L));
        assertEquals(0, ExactMath.add(0, 0L));
    }

    @Test
    public void testAddWithLongOverflow() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ExactMath.add(1, Long.MAX_VALUE);
        });
        assertEquals("Argument too large or result overflows", exception.getMessage());
    }

    @Test
    public void testAddWithResultOverflow() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ExactMath.add(Integer.MAX_VALUE, 1L);
        });
        assertEquals("Argument too large or result overflows", exception.getMessage());
    }
}