package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class StrBuilderLLM_Test {

    @Test
    public void testSetLengthIncreaseBuffer() {
        StrBuilder sb = new StrBuilder("abc");
        sb.setLength(5);
        assertEquals(5, sb.length());
        assertEquals("abc\0\0", sb.toString());
    }

    @Test
    public void testSetLengthDecreaseBuffer() {
        StrBuilder sb = new StrBuilder("abc");
        sb.setLength(2);
        assertEquals(2, sb.length());
        assertEquals("ab", sb.toString());
    }

    @Test
    public void testSetLengthNegative() {
        StrBuilder sb = new StrBuilder("abc");
        assertThrows(StringIndexOutOfBoundsException.class, () -> sb.setLength(-1));
    }
}