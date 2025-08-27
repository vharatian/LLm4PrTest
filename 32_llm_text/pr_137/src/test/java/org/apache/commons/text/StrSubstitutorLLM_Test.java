package org.apache.commons.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StrSubstitutorLLM_Test {

    @Test
    public void testValueDelimiterMatcherDocumentation() {
        final StrSubstitutor sub = new StrSubstitutor();
        sub.setValueDelimiter(":-");
        assertEquals(":-", sub.getValueDelimiterMatcher().toString());
    }

    @Test
    public void testValueDelimiterMatcherDocumentationWithChar() {
        final StrSubstitutor sub = new StrSubstitutor();
        sub.setValueDelimiter(':');
        assertEquals(":", sub.getValueDelimiterMatcher().toString());
    }

    @Test
    public void testValueDelimiterMatcherDocumentationWithNull() {
        final StrSubstitutor sub = new StrSubstitutor();
        sub.setValueDelimiter((String) null);
        assertEquals(null, sub.getValueDelimiterMatcher());
    }
}