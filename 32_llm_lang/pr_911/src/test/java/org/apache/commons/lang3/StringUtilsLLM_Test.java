package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testUpperCaseWithLocale() {
        // Test the upperCase method with a specific locale
        assertEquals("FOO TEST THING", StringUtils.upperCase("fOo test THING", Locale.ENGLISH));
        assertEquals("FOO TEST THING", StringUtils.upperCase("fOo test THING", Locale.FRENCH));
        assertEquals("FOO TEST THING", StringUtils.upperCase("fOo test THING", Locale.GERMAN));
    }
}