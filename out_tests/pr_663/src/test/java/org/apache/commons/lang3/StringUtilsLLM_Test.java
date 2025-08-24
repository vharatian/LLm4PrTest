package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testAbbreviate_StringStringIntInt_Updated() {
        // Test cases for the updated abbreviate method with final int strLen
        assertEquals("abcdefghij", StringUtils.abbreviate("abcdefghij", "...", 0, 10));
        assertEquals("...fghij...", StringUtils.abbreviate("abcdefghij", "...", 3, 10));
        assertEquals("...ghij...", StringUtils.abbreviate("abcdefghij", "...", 4, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 8, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 9, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 10, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 11, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 12, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 13, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 14, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 15, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", 16, 10));
        assertEquals("...ijklmno", StringUtils.abbreviate("abcdefghijklmno", "...", Integer.MAX_VALUE, 10));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_Exceptions() {
        // Test cases for exceptions in the updated abbreviate method
        assertThrows(IllegalArgumentException.class, () -> StringUtils.abbreviate("abcdefghij", "::", 0, 2));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.abbreviate("abcdefghij", "!!!", 5, 6));
    }
}