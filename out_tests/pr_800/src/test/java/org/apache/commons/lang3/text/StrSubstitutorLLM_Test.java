package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Deprecated
public class StrSubstitutorLLM_Test {

    private Map<String, String> values;

    @BeforeEach
    public void setUp() {
        values = new HashMap<>();
        values.put("animal", "quick brown fox");
        values.put("target", "lazy dog");
    }

    @AfterEach
    public void tearDown() {
        values = null;
    }

    @Test
    public void testSubstituteWithEndMatchLenInitialization() {
        final StrSubstitutor sub = new StrSubstitutor(values);
        final String template = "The ${animal} jumps over the ${target}.";
        final String expected = "The quick brown fox jumps over the lazy dog.";
        assertEquals(expected, sub.replace(template));
    }

    @Test
    public void testSubstituteWithValueDelimiterMatchLenInitialization() {
        values.put("undefined.number", "1234567890");
        final StrSubstitutor sub = new StrSubstitutor(values);
        final String template = "The ${animal} jumps over the ${target}. ${undefined.number:-1234567890}.";
        final String expected = "The quick brown fox jumps over the lazy dog. 1234567890.";
        assertEquals(expected, sub.replace(template));
    }
}