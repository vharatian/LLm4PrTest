package org.apache.commons.text.matcher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractStringMatcherLLM_Test {

    @Test
    void testCharArrayMatcherConstructorWithChars() {
        char[] chars = {'a', 'b', 'c'};
        AbstractStringMatcher.CharArrayMatcher matcher = new AbstractStringMatcher.CharArrayMatcher(chars);
        assertNotNull(matcher);
        assertEquals(3, matcher.size());
    }

    @Test
    void testCharArrayMatcherIsMatchWithCharArray() {
        char[] chars = {'a', 'b', 'c'};
        AbstractStringMatcher.CharArrayMatcher matcher = new AbstractStringMatcher.CharArrayMatcher(chars);
        char[] buffer = {'a', 'b', 'c', 'd'};
        assertEquals(3, matcher.isMatch(buffer, 0, 0, buffer.length));
        assertEquals(0, matcher.isMatch(buffer, 1, 0, buffer.length));
    }

    @Test
    void testCharArrayMatcherIsMatchWithCharSequence() {
        char[] chars = {'a', 'b', 'c'};
        AbstractStringMatcher.CharArrayMatcher matcher = new AbstractStringMatcher.CharArrayMatcher(chars);
        CharSequence buffer = "abcd";
        assertEquals(3, matcher.isMatch(buffer, 0, 0, buffer.length()));
        assertEquals(0, matcher.isMatch(buffer, 1, 0, buffer.length()));
    }

    @Test
    void testCharArrayMatcherToString() {
        char[] chars = {'a', 'b', 'c'};
        AbstractStringMatcher.CharArrayMatcher matcher = new AbstractStringMatcher.CharArrayMatcher(chars);
        assertTrue(matcher.toString().contains("[\"abc\"]"));
    }
}