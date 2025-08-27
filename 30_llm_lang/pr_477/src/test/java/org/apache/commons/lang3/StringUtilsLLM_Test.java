package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testAbbreviate_StringStringIntInt_EmptyAbbrevMarker() {
        assertEquals("abc", StringUtils.abbreviate("abcdef", "", 3));
        assertEquals("abcdef", StringUtils.abbreviate("abcdef", "", 6));
        assertEquals("abcdef", StringUtils.abbreviate("abcdef", "", 10));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_EmptyAbbrevMarkerWithOffset() {
        assertEquals("abc", StringUtils.abbreviate("abcdef", "", 0, 3));
        assertEquals("bcdef", StringUtils.abbreviate("abcdef", "", 1, 5));
        assertEquals("cdef", StringUtils.abbreviate("abcdef", "", 2, 4));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_NullInputs() {
        assertNull(StringUtils.abbreviate(null, null, 10));
        assertNull(StringUtils.abbreviate(null, "", 10));
        assertNull(StringUtils.abbreviate("", null, 10));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_InvalidMaxWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.abbreviate("abcdef", "", 0, -1));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.abbreviate("abcdef", "", 0, 0));
    }
}