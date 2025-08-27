package org.apache.commons.imaging.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SafeOperationsLLM_Test {

    @Test
    void testAddWithValidInput() {
        assertEquals(10, SafeOperations.add(1, 2, 3, 4));
        assertEquals(0, SafeOperations.add(0, 0, 0, 0));
        assertEquals(-10, SafeOperations.add(-1, -2, -3, -4));
    }

    @Test
    void testAddWithSingleElement() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            SafeOperations.add(1);
        });
        assertEquals("You must provide at least two elements to be added", exception.getMessage());
    }

    @Test
    void testAddWithNoElements() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            SafeOperations.add();
        });
        assertEquals("You must provide at least two elements to be added", exception.getMessage());
    }

    @Test
    void testAddWithNullInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            SafeOperations.add((int[]) null);
        });
        assertEquals("You must provide at least two elements to be added", exception.getMessage());
    }

    @Test
    void testAddWithOverflow() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            SafeOperations.add(Integer.MAX_VALUE, 1);
        });
        assertEquals("integer overflow", exception.getMessage());
    }

    @Test
    void testAddWithUnderflow() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            SafeOperations.add(Integer.MIN_VALUE, -1);
        });
        assertEquals("integer overflow", exception.getMessage());
    }
}