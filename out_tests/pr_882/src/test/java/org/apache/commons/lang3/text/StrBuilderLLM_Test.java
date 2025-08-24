package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class StrBuilderLLM_Test {

    @Test
    public void testSetLengthIncreaseBuffer() {
        StrBuilder sb = new StrBuilder("Hello");
        sb.setLength(10);
        assertEquals(10, sb.length());
        assertEquals("Hello\0\0\0\0\0", sb.toString());
    }

    @Test
    public void testSetLengthDecreaseBuffer() {
        StrBuilder sb = new StrBuilder("Hello");
        sb.setLength(3);
        assertEquals(3, sb.length());
        assertEquals("Hel", sb.toString());
    }

    @Test
    public void testSetLengthNegative() {
        StrBuilder sb = new StrBuilder("Hello");
        assertThrows(StringIndexOutOfBoundsException.class, () -> sb.setLength(-1));
    }
}