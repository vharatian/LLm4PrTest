package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testIsMixedCase() {
        // Test cases where the string contains both uppercase and lowercase characters
        assertTrue(StringUtils.isMixedCase("aBc"));
        assertTrue(StringUtils.isMixedCase("aBc "));
        assertTrue(StringUtils.isMixedCase("A c"));
        assertTrue(StringUtils.isMixedCase("aBc\n"));
        assertTrue(StringUtils.isMixedCase("A1c"));
        assertTrue(StringUtils.isMixedCase("a/C"));

        // Test cases where the string does not contain both uppercase and lowercase characters
        assertFalse(StringUtils.isMixedCase(null));
        assertFalse(StringUtils.isMixedCase(""));
        assertFalse(StringUtils.isMixedCase(" "));
        assertFalse(StringUtils.isMixedCase("A"));
        assertFalse(StringUtils.isMixedCase("a"));
        assertFalse(StringUtils.isMixedCase("/"));
        assertFalse(StringUtils.isMixedCase("A/"));
        assertFalse(StringUtils.isMixedCase("/b"));
        assertFalse(StringUtils.isMixedCase("abc"));
        assertFalse(StringUtils.isMixedCase("ABC"));
    }
}