package org.apache.commons.lang3.text;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrBuilderLLM_Test {

    @Test
    public void testIndexOfCharWithNegativeStartIndex() {
        StrBuilder sb = new StrBuilder("abab");
        assertEquals(0, sb.indexOf('a', -1));
        assertEquals(0, sb.indexOf('a', 0));
        assertEquals(2, sb.indexOf('a', 1));
        assertEquals(-1, sb.indexOf('a', 4));
        assertEquals(-1, sb.indexOf('a', 5));
    }

    @Test
    public void testIndexOfStringWithNegativeStartIndex() {
        StrBuilder sb = new StrBuilder("abab");
        assertEquals(0, sb.indexOf("a", -1));
        assertEquals(0, sb.indexOf("a", 0));
        assertEquals(2, sb.indexOf("a", 1));
        assertEquals(2, sb.indexOf("a", 2));
        assertEquals(-1, sb.indexOf("a", 3));
        assertEquals(-1, sb.indexOf("a", 4));
        assertEquals(-1, sb.indexOf("a", 5));
    }

    @Test
    public void testIndexOfStrMatcherWithNegativeStartIndex() {
        StrBuilder sb = new StrBuilder("ab bd");
        assertEquals(0, sb.indexOf(StrMatcher.charMatcher('a'), -2));
        assertEquals(0, sb.indexOf(StrMatcher.charMatcher('a'), 0));
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('a'), 2));
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('a'), 20));
    }

    @Test
    public void testIndexOfCharWithValidStartIndex() {
        StrBuilder sb = new StrBuilder("xyzabc");
        assertEquals(2, sb.indexOf('z', 0));
        assertEquals(-1, sb.indexOf('z', 3));
    }

    @Test
    public void testIndexOfStringWithValidStartIndex() {
        StrBuilder sb = new StrBuilder("xyzabc");
        assertEquals(2, sb.indexOf("za", 0));
        assertEquals(-1, sb.indexOf("za", 3));
    }

    @Test
    public void testIndexOfStrMatcherWithValidStartIndex() {
        StrBuilder sb = new StrBuilder("xyzabc");
        assertEquals(2, sb.indexOf(StrMatcher.charMatcher('z'), 0));
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('z'), 3));
    }
}