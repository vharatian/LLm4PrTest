package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringSubstitutorLLM_Test {

    private Map<String, String> values;

    @BeforeEach
    public void setUp() {
        values = new HashMap<>();
        values.put("animal", "quick brown fox");
        values.put("target", "lazy dog");
    }

    @Test
    public void testReplaceWithGenericsInMap() {
        // Test case to ensure the usage of generics in the map
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}.";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        assertEquals("The quick brown fox jumped over the lazy dog.", sub.replace(templateString));
    }

    @Test
    public void testReplaceWithGenericsInMapWithDefaultValue() {
        // Test case to ensure the usage of generics in the map with default value
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}. ${undefined.number:-1234567890}.";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        assertEquals("The quick brown fox jumped over the lazy dog. 1234567890.", sub.replace(templateString));
    }

    @Test
    public void testReplaceWithGenericsInMapWithNestedVariables() {
        // Test case to ensure the usage of generics in the map with nested variables
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name", "Douglas ${surname}");
        valuesMap.put("surname", "Crockford");
        String templateString = "Hi ${name}";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        assertEquals("Hi Douglas Crockford", sub.replace(templateString));
    }
}