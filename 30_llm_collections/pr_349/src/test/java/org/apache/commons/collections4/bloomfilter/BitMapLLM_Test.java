package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BitMapLLM_Test {

    @Test
    public final void testGetLongIndexWithNegativeIndex() {
        // Test to ensure that a negative index produces a negative value
        // and throws an ArrayIndexOutOfBoundsException when used as an index into an array
        final long[] bitMaps = new long[1];
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> BitMap.getLongIndex(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> BitMap.contains(bitMaps, -1));
    }
}