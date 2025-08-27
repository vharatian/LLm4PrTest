package org.apache.commons.text.lookup;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlDecoderStringLookupLLM_Test {

    @Test
    public void testLookup() {
        XmlDecoderStringLookup lookup = XmlDecoderStringLookup.INSTANCE;
        assertEquals("Hello & World", lookup.lookup("Hello &amp; World"));
        assertEquals("<tag>", lookup.lookup("&lt;tag&gt;"));
        assertEquals("\"quote\"", lookup.lookup("&quot;quote&quot;"));
        assertEquals("Apostrophe's", lookup.lookup("Apostrophe&apos;s"));
        assertEquals("10 > 5", lookup.lookup("10 &gt; 5"));
        assertEquals("5 < 10", lookup.lookup("5 &lt; 10"));
    }

    @Test
    public void testLookupWithNoEscapeSequences() {
        XmlDecoderStringLookup lookup = XmlDecoderStringLookup.INSTANCE;
        assertEquals("plain text", lookup.lookup("plain text"));
    }

    @Test
    public void testLookupWithEmptyString() {
        XmlDecoderStringLookup lookup = XmlDecoderStringLookup.INSTANCE;
        assertEquals("", lookup.lookup(""));
    }

    @Test
    public void testLookupWithNull() {
        XmlDecoderStringLookup lookup = XmlDecoderStringLookup.INSTANCE;
        assertEquals(null, lookup.lookup(null));
    }
}