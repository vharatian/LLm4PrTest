package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.lookup.StringLookupFactory;
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
    public void testXmlDecoderStringLookup() {
        StringSubstitutor sub = new StringSubstitutor(StringLookupFactory.INSTANCE.xmlDecoderStringLookup());
        String template = "The &lt;animal&gt; jumps over the &lt;target&gt;.";
        String expected = "The <animal> jumps over the <target>.";
        assertEquals(expected, sub.replace(template));
    }

    @Test
    public void testXmlEncoderStringLookup() {
        StringSubstitutor sub = new StringSubstitutor(StringLookupFactory.INSTANCE.xmlEncoderStringLookup());
        String template = "The <animal> jumps over the <target>.";
        String expected = "The &lt;animal&gt; jumps over the &lt;target&gt;.";
        assertEquals(expected, sub.replace(template));
    }

    @Test
    public void testCreateInterpolatorWithXmlDecoder() {
        StringSubstitutor sub = StringSubstitutor.createInterpolator();
        sub.setVariableResolver(StringLookupFactory.INSTANCE.xmlDecoderStringLookup());
        String template = "The &lt;animal&gt; jumps over the &lt;target&gt;.";
        String expected = "The <animal> jumps over the <target>.";
        assertEquals(expected, sub.replace(template));
    }

    @Test
    public void testCreateInterpolatorWithXmlEncoder() {
        StringSubstitutor sub = StringSubstitutor.createInterpolator();
        sub.setVariableResolver(StringLookupFactory.INSTANCE.xmlEncoderStringLookup());
        String template = "The <animal> jumps over the <target>.";
        String expected = "The &lt;animal&gt; jumps over the &lt;target&gt;.";
        assertEquals(expected, sub.replace(template));
    }
}