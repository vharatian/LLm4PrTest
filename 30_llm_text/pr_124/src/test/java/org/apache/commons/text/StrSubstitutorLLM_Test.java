package org.apache.commons.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StrSubstitutorLLM_Test {
    private Map<String, String> values;

    @BeforeEach
    public void setUp() throws Exception {
        values = new HashMap<>();
        values.put("animal", "quick brown fox");
        values.put("target", "lazy dog");
    }

    @Test
    public void testReplaceSimpleWithGenerics() {
        // Test case to ensure that the example in the documentation with generics works correctly
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}.";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        assertEquals("The quick brown fox jumped over the lazy dog.", resolvedString);
    }

    @Test
    public void testReplaceWithDefaultValueWithGenerics() {
        // Test case to ensure that the example with default values in the documentation with generics works correctly
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}. ${undefined.number:-1234567890}.";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        assertEquals("The quick brown fox jumped over the lazy dog. 1234567890.", resolvedString);
    }

    @Test
    public void testReplaceInVariableWithGenerics() {
        // Test case to ensure that the example with substitution in variables in the documentation with generics works correctly
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name", "Douglas ${surname}");
        valuesMap.put("surname", "Crockford");
        String templateString = "Hi ${name}";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        sub.setEnableSubstitutionInVariables(true);
        String resolvedString = sub.replace(templateString);
        assertEquals("Hi Douglas Crockford", resolvedString);
    }
}