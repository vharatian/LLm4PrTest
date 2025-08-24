package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testTruncate_StringInt_NegativeMaxWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -1), "maxWidth cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -10), "maxWidth cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", Integer.MIN_VALUE), "maxWidth cannot be negative");
    }

    @Test
    public void testTruncate_StringIntInt_NegativeOffsetOrMaxWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -1, 0), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -10, 0), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -100, 1), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, 0), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -1, -1), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -10, -10), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -100, -100), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, Integer.MIN_VALUE), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", 0, -1), "maxWidth cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", 0, -10), "maxWidth cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", 0, -100), "maxWidth cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", 1, -100), "maxWidth cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", 0, Integer.MIN_VALUE), "maxWidth cannot be negative");
    }

    @Test
    public void testTruncate_StringIntInt_ValidCases() {
        final String raspberry = "raspberry peach";
        assertEquals("peach", StringUtils.truncate(raspberry, 10, 15));
        assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 0, 10));
        assertEquals("abcdefghijklmno", StringUtils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE));
        assertEquals("bcdefghijk", StringUtils.truncate("abcdefghijklmno", 1, 10));
        assertEquals("cdefghijkl", StringUtils.truncate("abcdefghijklmno", 2, 10));
        assertEquals("defghijklm", StringUtils.truncate("abcdefghijklmno", 3, 10));
        assertEquals("efghijklmn", StringUtils.truncate("abcdefghijklmno", 4, 10));
        assertEquals("fghijklmno", StringUtils.truncate("abcdefghijklmno", 5, 10));
        assertEquals("fghij", StringUtils.truncate("abcdefghijklmno", 5, 5));
        assertEquals("fgh", StringUtils.truncate("abcdefghijklmno", 5, 3));
        assertEquals("klm", StringUtils.truncate("abcdefghijklmno", 10, 3));
        assertEquals("klmno", StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE));
        assertEquals("n", StringUtils.truncate("abcdefghijklmno", 13, 1));
        assertEquals("no", StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE));
        assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, 1));
        assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE));
        assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, 1));
        assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE));
        assertEquals("", StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}