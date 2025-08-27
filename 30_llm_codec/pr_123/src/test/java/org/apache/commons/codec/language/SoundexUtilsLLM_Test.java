package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SoundexUtilsLLM_Test {

    @Test
    void testClean() {
        assertEquals("HELLO", SoundexUtils.clean("hello"));
        assertEquals("HELLO", SoundexUtils.clean("HeLLo"));
        assertEquals("HELLO", SoundexUtils.clean("HELLO"));
        assertEquals("HLL", SoundexUtils.clean("H3LL0"));
        assertEquals("", SoundexUtils.clean(""));
        assertNull(SoundexUtils.clean(null));
    }

    @Test
    void testDifference() throws EncoderException {
        StringEncoder encoder = new Soundex();
        assertEquals(4, SoundexUtils.difference(encoder, "Robert", "Rupert"));
        assertEquals(4, SoundexUtils.difference(encoder, "Ashcraft", "Ashcroft"));
        assertEquals(0, SoundexUtils.difference(encoder, "Tymczak", "Pfister"));
    }

    @Test
    void testDifferenceEncoded() {
        assertEquals(4, SoundexUtils.differenceEncoded("R163", "R163"));
        assertEquals(0, SoundexUtils.differenceEncoded("R163", "P123"));
        assertEquals(0, SoundexUtils.differenceEncoded(null, "R163"));
        assertEquals(0, SoundexUtils.differenceEncoded("R163", null));
    }

    @Test
    void testIsEmpty() {
        assertTrue(SoundexUtils.isEmpty(null));
        assertTrue(SoundexUtils.isEmpty(""));
        assertFalse(SoundexUtils.isEmpty(" "));
        assertFalse(SoundexUtils.isEmpty("a"));
    }
}