package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BitMapLLM_Test {

    /**
     * Test for the mod method in BitMap class.
     */
    @Test
    public final void testMod() {
        // Test cases for the mod method
        assertEquals(0, BitMap.mod(10L, 2));
        assertEquals(1, BitMap.mod(10L, 3));
        assertEquals(2, BitMap.mod(10L, 4));
        assertEquals(3, BitMap.mod(10L, 7));
        assertEquals(0, BitMap.mod(0L, 5));
        assertEquals(1, BitMap.mod(1L, 5));
        assertEquals(4, BitMap.mod(9L, 5));
        assertEquals(0, BitMap.mod(Long.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(1, BitMap.mod(Long.MAX_VALUE, Integer.MAX_VALUE - 1));

        // Test for divisor being 1, which should always return 0
        assertEquals(0, BitMap.mod(123456789L, 1));

        // Test for large dividend and small divisor
        assertEquals(0, BitMap.mod(Long.MAX_VALUE, 1));
        assertEquals(1, BitMap.mod(Long.MAX_VALUE, 2));
        assertEquals(3, BitMap.mod(Long.MAX_VALUE, 4));

        // Test for negative divisor, should throw an exception
        assertThrows(ArithmeticException.class, () -> BitMap.mod(10L, -1));
    }
}