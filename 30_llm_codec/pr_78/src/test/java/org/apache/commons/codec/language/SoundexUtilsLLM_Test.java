package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SoundexUtilsLLM_Test {

    @Test
    void testCleanWithNull() {
        assertNull(SoundexUtils.clean(null));
    }

    @Test
    void testCleanWithEmptyString() {
        assertEquals("", SoundexUtils.clean(""));
    }

    @Test
    void testCleanWithNonLetterCharacters() {
        assertEquals("ABC", SoundexUtils.clean("A1B2C3"));
    }

    @Test
    void testCleanWithAllLetters() {
        assertEquals("HELLO", SoundexUtils.clean("Hello"));
    }

    @Test
    void testDifferenceWithNullEncoder() {
        assertThrows(EncoderException.class, () -> {
            SoundexUtils.difference(null, "test", "test");
        });
    }

    @Test
    void testDifferenceWithValidEncoder() throws EncoderException {
        StringEncoder encoder = new MockStringEncoder();
        assertEquals(4, SoundexUtils.difference(encoder, "test", "test"));
    }

    @Test
    void testDifferenceEncodedWithNulls() {
        assertEquals(0, SoundexUtils.differenceEncoded(null, null));
    }

    @Test
    void testDifferenceEncodedWithValidStrings() {
        assertEquals(3, SoundexUtils.differenceEncoded("test", "tent"));
    }

    @Test
    void testIsEmptyWithNull() {
        assertTrue(SoundexUtils.isEmpty(null));
    }

    @Test
    void testIsEmptyWithEmptyString() {
        assertTrue(SoundexUtils.isEmpty(""));
    }

    @Test
    void testIsEmptyWithNonEmptyString() {
        assertFalse(SoundexUtils.isEmpty("test"));
    }

    private static class MockStringEncoder implements StringEncoder {
        @Override
        public Object encode(Object source) throws EncoderException {
            return source.toString();
        }

        @Override
        public String encode(String source) throws EncoderException {
            return source;
        }
    }
}