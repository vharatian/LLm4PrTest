package org.apache.commons.text.lookup;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XmlEncoderStringLookupLLM_Test {

    @Test
    void testLookupWithSpecialCharacters() {
        XmlEncoderStringLookup lookup = XmlEncoderStringLookup.INSTANCE;
        String input = "<tag>value</tag>";
        String expected = "&lt;tag&gt;value&lt;/tag&gt;";
        assertEquals(expected, lookup.lookup(input));
    }

    @Test
    void testLookupWithNull() {
        XmlEncoderStringLookup lookup = XmlEncoderStringLookup.INSTANCE;
        assertNull(lookup.lookup(null));
    }

    @Test
    void testLookupWithEmptyString() {
        XmlEncoderStringLookup lookup = XmlEncoderStringLookup.INSTANCE;
        String input = "";
        String expected = "";
        assertEquals(expected, lookup.lookup(input));
    }

    @Test
    void testLookupWithNoSpecialCharacters() {
        XmlEncoderStringLookup lookup = XmlEncoderStringLookup.INSTANCE;
        String input = "plainText";
        String expected = "plainText";
        assertEquals(expected, lookup.lookup(input));
    }

    @Test
    void testLookupWithAmpersand() {
        XmlEncoderStringLookup lookup = XmlEncoderStringLookup.INSTANCE;
        String input = "Fish & Chips";
        String expected = "Fish &amp; Chips";
        assertEquals(expected, lookup.lookup(input));
    }
}