package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StrSubstitutorLLM_Test {

    @Test
    public void testDefaultPreserveEscapes() {
        StrSubstitutor sub = new StrSubstitutor();
        assertFalse(sub.isPreserveEscapes(), "Default value of preserveEscapes should be false");
    }

    @Test
    public void testSetPreserveEscapes() {
        StrSubstitutor sub = new StrSubstitutor();
        sub.setPreserveEscapes(true);
        assertTrue(sub.isPreserveEscapes(), "preserveEscapes should be true after setting it to true");
        sub.setPreserveEscapes(false);
        assertFalse(sub.isPreserveEscapes(), "preserveEscapes should be false after setting it to false");
    }
}