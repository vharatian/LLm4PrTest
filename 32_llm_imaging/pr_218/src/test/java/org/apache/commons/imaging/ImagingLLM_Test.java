package org.apache.commons.imaging;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ImagingLLM_Test {

    @Test
    public void testCompareBytePairInvalidLength() {
        int[] a = {0x47};
        int[] b = {0x49, 0x47};
        assertThrows(IllegalArgumentException.class, () -> {
            Imaging.compareBytePair(a, b);
        });
    }

    @Test
    public void testCompareBytePairValidLength() {
        int[] a = {0x47, 0x49};
        int[] b = {0x47, 0x49};
        assertTrue(Imaging.compareBytePair(a, b));
    }

    @Test
    public void testCompareBytePairDifferentValues() {
        int[] a = {0x47, 0x49};
        int[] b = {0x47, 0x50};
        assertFalse(Imaging.compareBytePair(a, b));
    }
}