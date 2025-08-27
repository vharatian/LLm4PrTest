package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testAppendIfMissingIgnoreCase() {
        // Test case-insensitive suffix appending
        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz"));
        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abcXYZ", "xyz"));
        assertEquals("abcXYZ", StringUtils.appendIfMissingIgnoreCase("abcXYZ", "XYZ"));
    }

    @Test
    public void testEndsWithCaseSensitive() {
        // Test case-sensitive endsWith
        assertEquals(true, StringUtils.endsWith("abcdef", "def"));
        assertEquals(false, StringUtils.endsWith("abcdef", "DEF"));
    }

    @Test
    public void testEndsWithIgnoreCase() {
        // Test case-insensitive endsWith
        assertEquals(true, StringUtils.endsWithIgnoreCase("abcdef", "DEF"));
        assertEquals(true, StringUtils.endsWithIgnoreCase("abcdef", "def"));
    }

    @Test
    public void testReplaceIgnoreCase() {
        // Test case-insensitive replace
        assertEquals("barbarbar", StringUtils.replaceIgnoreCase("foofoofoo", "foo", "bar"));
        assertEquals("barbarbar", StringUtils.replaceIgnoreCase("fooFOOfoo", "foo", "bar"));
    }

    @Test
    public void testReplaceIgnoreCaseWithMax() {
        // Test case-insensitive replace with max occurrences
        assertEquals("barbarfoo", StringUtils.replaceIgnoreCase("foofoofoo", "foo", "bar", 2));
        assertEquals("barFOOfoo", StringUtils.replaceIgnoreCase("fooFOOfoo", "foo", "bar", 1));
    }

    @Test
    public void testRemoveEndIgnoreCase() {
        // Test case-insensitive removeEnd
        assertEquals("www.domain", StringUtils.removeEndIgnoreCase("www.domain.com", ".com"));
        assertEquals("www.domain", StringUtils.removeEndIgnoreCase("www.domain.COM", ".com"));
    }

    @Test
    public void testStartsWithCaseSensitive() {
        // Test case-sensitive startsWith
        assertEquals(true, StringUtils.startsWith("abcdef", "abc"));
        assertEquals(false, StringUtils.startsWith("abcdef", "ABC"));
    }

    @Test
    public void testStartsWithIgnoreCase() {
        // Test case-insensitive startsWith
        assertEquals(true, StringUtils.startsWithIgnoreCase("abcdef", "ABC"));
        assertEquals(true, StringUtils.startsWithIgnoreCase("abcdef", "abc"));
    }

    @Test
    public void testPrependIfMissingIgnoreCase() {
        // Test case-insensitive prefix prepending
        assertEquals("xyzabc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz"));
        assertEquals("xyzabc", StringUtils.prependIfMissingIgnoreCase("XYZabc", "xyz"));
        assertEquals("XYZabc", StringUtils.prependIfMissingIgnoreCase("XYZabc", "XYZ"));
    }

    @Test
    public void testCountMatches() {
        // Test countMatches with comment change
        assertEquals(3, StringUtils.countMatches("abba", 'a'));
        assertEquals(0, StringUtils.countMatches("abba", 'z'));
    }

    @Test
    public void testOrdinalIndexOf() {
        // Test ordinalIndexOf with comment change
        assertEquals(2, StringUtils.ordinalIndexOf("aabaabaa", "b", 1));
        assertEquals(5, StringUtils.ordinalIndexOf("aabaabaa", "b", 2));
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", 3));
    }
}