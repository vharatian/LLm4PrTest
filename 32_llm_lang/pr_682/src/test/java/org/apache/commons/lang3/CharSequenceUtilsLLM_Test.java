package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CharSequenceUtilsLLM_Test {

    @Test
    public void testIndexOfWithSupplementaryChar() {
        assertEquals(-1, CharSequenceUtils.indexOf("abc", 0x1F600, 0)); // Supplementary character not present
        assertEquals(1, CharSequenceUtils.indexOf("a\uD83D\uDE00b", 0x1F600, 0)); // Supplementary character present
    }

    @Test
    public void testLastIndexOfWithSupplementaryChar() {
        assertEquals(-1, CharSequenceUtils.lastIndexOf("abc", 0x1F600, 2)); // Supplementary character not present
        assertEquals(1, CharSequenceUtils.lastIndexOf("a\uD83D\uDE00b", 0x1F600, 2)); // Supplementary character present
    }

    @Test
    public void testLastIndexOfWithCharSequence() {
        assertEquals(-1, CharSequenceUtils.lastIndexOf("abc", "d", 2)); // Character sequence not present
        assertEquals(1, CharSequenceUtils.lastIndexOf("abc", "b", 2)); // Character sequence present
    }

    @Test
    public void testLastIndexOfWithCharSequenceNegativeStart() {
        assertEquals(-1, CharSequenceUtils.lastIndexOf("abc", "b", -1)); // Negative start index
    }

    @Test
    public void testLastIndexOfWithCharSequenceEmptySearch() {
        assertEquals(2, CharSequenceUtils.lastIndexOf("abc", "", 2)); // Empty search sequence
    }
}