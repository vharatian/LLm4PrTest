package org.apache.commons.text;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class StrSubstitutorLLM_Test {

    @Test
    public void testPreserveEscapesDefault() {
        // Test that the default value of preserveEscapes is false
        StrSubstitutor sub = new StrSubstitutor();
        assertFalse(sub.isPreserveEscapes());
    }

    @Test
    public void testSetPreserveEscapes() {
        // Test setting the preserveEscapes flag
        StrSubstitutor sub = new StrSubstitutor();
        sub.setPreserveEscapes(true);
        assertTrue(sub.isPreserveEscapes());
        sub.setPreserveEscapes(false);
        assertFalse(sub.isPreserveEscapes());
    }

    @Test
    public void testPreserveEscapesFunctionality() {
        // Test the functionality of preserveEscapes flag
        Map<String, String> values = new HashMap<>();
        values.put("animal", "fox");
        StrSubstitutor sub = new StrSubstitutor(values, "${", "}", '$');
        
        // When preserveEscapes is false
        sub.setPreserveEscapes(false);
        assertEquals("The ${animal} jumps over the lazy dog.", sub.replace("The $${animal} jumps over the lazy dog."));
        
        // When preserveEscapes is true
        sub.setPreserveEscapes(true);
        assertEquals("The $${animal} jumps over the lazy dog.", sub.replace("The $${animal} jumps over the lazy dog."));
    }
}