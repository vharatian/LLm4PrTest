package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testStripAccents() {
        // Test with null input
        assertNull(StringUtils.stripAccents(null));

        // Test with empty string
        assertEquals("", StringUtils.stripAccents(""));

        // Test with string containing no accents
        assertEquals("abc", StringUtils.stripAccents("abc"));

        // Test with string containing accents
        assertEquals("eclair", StringUtils.stripAccents("éclair"));
        assertEquals("a", StringUtils.stripAccents("à"));
        assertEquals("cafe", StringUtils.stripAccents("café"));
        assertEquals("resume", StringUtils.stripAccents("résumé"));

        // Test with string containing mixed characters
        assertEquals("resume cafe", StringUtils.stripAccents("résumé café"));
        assertEquals("Cafe au lait", StringUtils.stripAccents("Café au lait"));
    }
}