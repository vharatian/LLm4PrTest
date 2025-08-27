package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.jupiter.api.Test;

public class RegExUtilsLLM_Test {

    @Test
    public void testReplaceAll_StringPatternString_NullInputs() {
        assertNull(RegExUtils.replaceAll(null, (Pattern) null, null));
        assertEquals("any", RegExUtils.replaceAll("any", (Pattern) null, null));
        assertEquals("any", RegExUtils.replaceAll("any", Pattern.compile(""), null));
        assertEquals("any", RegExUtils.replaceAll("any", (Pattern) null, ""));
    }

    @Test
    public void testReplaceAll_StringStringString_NullInputs() {
        assertNull(RegExUtils.replaceAll(null, (String) null, null));
        assertEquals("any", RegExUtils.replaceAll("any", (String) null, null));
        assertEquals("any", RegExUtils.replaceAll("any", "", null));
        assertEquals("any", RegExUtils.replaceAll("any", (String) null, ""));
    }

    @Test
    public void testReplaceFirst_StringPatternString_NullInputs() {
        assertNull(RegExUtils.replaceFirst(null, (Pattern) null, null));
        assertEquals("any", RegExUtils.replaceFirst("any", (Pattern) null, null));
        assertEquals("any", RegExUtils.replaceFirst("any", Pattern.compile(""), null));
        assertEquals("any", RegExUtils.replaceFirst("any", (Pattern) null, ""));
    }

    @Test
    public void testReplaceFirst_StringStringString_NullInputs() {
        assertNull(RegExUtils.replaceFirst(null, (String) null, null));
        assertEquals("any", RegExUtils.replaceFirst("any", (String) null, null));
        assertEquals("any", RegExUtils.replaceFirst("any", "", null));
        assertEquals("any", RegExUtils.replaceFirst("any", (String) null, ""));
    }

    @Test
    public void testReplacePattern_StringStringString_NullInputs() {
        assertNull(RegExUtils.replacePattern(null, (String) null, null));
        assertEquals("any", RegExUtils.replacePattern("any", (String) null, null));
        assertEquals("any", RegExUtils.replacePattern("any", "", null));
        assertEquals("any", RegExUtils.replacePattern("any", (String) null, ""));
    }
}