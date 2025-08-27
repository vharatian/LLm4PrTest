package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

@Deprecated
public class StrBuilderLLM_Test {

    @Test
    public void testIndexOfStringWithFinalParameter() {
        final StrBuilder sb = new StrBuilder("abab");
        assertEquals(0, sb.indexOf("a", 0));
        assertEquals(2, sb.indexOf("a", 1));
        assertEquals(-1, sb.indexOf("a", 4));
        assertEquals(1, sb.indexOf("b", 1));
        assertEquals(-1, sb.indexOf("z", 0));
        assertEquals(-1, sb.indexOf("z", 1));
    }

    @Test
    public void testLastIndexOfStringWithFinalParameter() {
        final StrBuilder sb = new StrBuilder("abab");
        assertEquals(2, sb.lastIndexOf("a", 3));
        assertEquals(0, sb.lastIndexOf("a", 1));
        assertEquals(-1, sb.lastIndexOf("a", -1));
        assertEquals(3, sb.lastIndexOf("b", 3));
        assertEquals(1, sb.lastIndexOf("b", 1));
        assertEquals(-1, sb.lastIndexOf("z", 3));
        assertEquals(-1, sb.lastIndexOf("z", 1));
    }
}