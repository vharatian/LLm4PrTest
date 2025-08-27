package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StrSubstitutorLLM_Test {

    @Test
    public void testSubstituteMethodCommentCorrection() {
        // This test ensures that the comment correction in the substitute method does not affect functionality.
        final Map<String, String> values = new HashMap<>();
        values.put("animal", "quick brown fox");
        values.put("target", "lazy dog");
        final StrSubstitutor sub = new StrSubstitutor(values);
        assertEquals("The quick brown fox jumps over the lazy dog.", sub.replace("The ${animal} jumps over the ${target}."));
    }
}