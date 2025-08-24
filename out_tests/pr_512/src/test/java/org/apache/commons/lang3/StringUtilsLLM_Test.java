package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testReplaceWithMax() {
        // Test case for the modified replace method with max parameter
        assertEquals("barbarbar", StringUtils.replace("foofoofoo", "foo", "bar", 3));
        assertEquals("barfoofoo", StringUtils.replace("foofoofoo", "foo", "bar", 1));
        assertEquals("foofoofoo", StringUtils.replace("foofoofoo", "foo", "bar", 0));
        assertEquals("foofoofoo", StringUtils.replace("foofoofoo", "foo", "bar", -1));
        assertEquals("barbarbar", StringUtils.replace("foofoofoo", "foo", "bar", 10));
    }

    @Test
    public void testReplaceWithMaxEdgeCases() {
        // Edge cases for the modified replace method with max parameter
        assertEquals("barbarbar", StringUtils.replace("foofoofoo", "foo", "bar", 3));
        assertEquals("barfoofoo", StringUtils.replace("foofoofoo", "foo", "bar", 1));
        assertEquals("foofoofoo", StringUtils.replace("foofoofoo", "foo", "bar", 0));
        assertEquals("foofoofoo", StringUtils.replace("foofoofoo", "foo", "bar", -1));
        assertEquals("barbarbar", StringUtils.replace("foofoofoo", "foo", "bar", 10));
    }

    @Test
    public void testTruncateWithOffset() {
        // Test case for the modified truncate method with offset parameter
        assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 0, 10));
        assertEquals("klmno", StringUtils.truncate("abcdefghijklmno", 10, 10));
        assertEquals("klmno", StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE));
        assertEquals("no", StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE));
        assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, 1));
        assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, 1));
    }

    @Test
    public void testTruncateWithOffsetEdgeCases() {
        // Edge cases for the modified truncate method with offset parameter
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -1, 0), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", -10, 0), "offset cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", 0, -1), "maxWith cannot be negative");
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("abcdefghij", 0, -10), "maxWith cannot be negative");
    }
}