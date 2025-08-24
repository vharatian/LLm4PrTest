package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testCapitalizeArraySyntaxChange() {
        // Test for the change in array syntax in capitalize method
        assertEquals("Hello", StringUtils.capitalize("hello"));
        assertEquals("Hello World", StringUtils.capitalize("hello world"));
        assertEquals("Hello", StringUtils.capitalize("Hello"));
        assertEquals("", StringUtils.capitalize(""));
        assertEquals(null, StringUtils.capitalize(null));
    }

    @Test
    public void testGetLevenshteinDistanceArraySyntaxChange() {
        // Test for the change in array syntax in getLevenshteinDistance methods
        assertEquals(3, StringUtils.getLevenshteinDistance("kitten", "sitting"));
        assertEquals(1, StringUtils.getLevenshteinDistance("flaw", "lawn"));
        assertEquals(0, StringUtils.getLevenshteinDistance("gumbo", "gumbo"));
        assertEquals(7, StringUtils.getLevenshteinDistance("book", "backpack"));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.getLevenshteinDistance(null, "test"));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.getLevenshteinDistance("test", null));
    }

    @Test
    public void testGetLevenshteinDistanceWithThresholdArraySyntaxChange() {
        // Test for the change in array syntax in getLevenshteinDistance with threshold method
        assertEquals(3, StringUtils.getLevenshteinDistance("kitten", "sitting", 10));
        assertEquals(1, StringUtils.getLevenshteinDistance("flaw", "lawn", 10));
        assertEquals(0, StringUtils.getLevenshteinDistance("gumbo", "gumbo", 10));
        assertEquals(-1, StringUtils.getLevenshteinDistance("book", "backpack", 3));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.getLevenshteinDistance(null, "test", 10));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.getLevenshteinDistance("test", null, 10));
    }

    @Test
    public void testSwapCaseArraySyntaxChange() {
        // Test for the change in array syntax in swapCase method
        assertEquals("hELLO", StringUtils.swapCase("Hello"));
        assertEquals("hELLO wORLD", StringUtils.swapCase("Hello World"));
        assertEquals("hELLO", StringUtils.swapCase("hELLO"));
        assertEquals("", StringUtils.swapCase(""));
        assertEquals(null, StringUtils.swapCase(null));
    }

    @Test
    public void testUncapitalizeArraySyntaxChange() {
        // Test for the change in array syntax in uncapitalize method
        assertEquals("hello", StringUtils.uncapitalize("Hello"));
        assertEquals("hello world", StringUtils.uncapitalize("Hello World"));
        assertEquals("hello", StringUtils.uncapitalize("hello"));
        assertEquals("", StringUtils.uncapitalize(""));
        assertEquals(null, StringUtils.uncapitalize(null));
    }
}