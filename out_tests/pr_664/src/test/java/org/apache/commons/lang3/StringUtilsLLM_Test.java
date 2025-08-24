package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testLastIndexOfIgnoreCase() {
        // Test cases for the updated lastIndexOfIgnoreCase method
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, "a"));
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("abc", null));
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, null));
        assertEquals(0, StringUtils.lastIndexOfIgnoreCase("abc", ""));
        assertEquals(2, StringUtils.lastIndexOfIgnoreCase("abc", "c"));
        assertEquals(2, StringUtils.lastIndexOfIgnoreCase("abc", "C"));
        assertEquals(2, StringUtils.lastIndexOfIgnoreCase("abcabc", "c"));
        assertEquals(2, StringUtils.lastIndexOfIgnoreCase("abcabc", "C"));
        assertEquals(5, StringUtils.lastIndexOfIgnoreCase("abcabc", "c", 5));
        assertEquals(5, StringUtils.lastIndexOfIgnoreCase("abcabc", "C", 5));
        assertEquals(2, StringUtils.lastIndexOfIgnoreCase("abcabc", "c", 2));
        assertEquals(2, StringUtils.lastIndexOfIgnoreCase("abcabc", "C", 2));
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("abcabc", "d"));
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("abcabc", "D"));
    }
}