package org.apache.commons.codec.binary;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class Base16LLM_Test {

    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    private final Random random = new Random();

    public Random getRandom() {
        return this.random;
    }

    /**
     * Test for the corrected Javadoc in the isInAlphabet method.
     */
    @Test
    public void testIsInAlphabetJavadocCorrection() {
        Base16 b16 = new Base16(true);
        assertFalse(b16.isInAlphabet((byte) 0));
        assertFalse(b16.isInAlphabet((byte) 1));
        assertFalse(b16.isInAlphabet((byte) -1));
        assertFalse(b16.isInAlphabet((byte) -15));
        assertFalse(b16.isInAlphabet((byte) -16));
        assertFalse(b16.isInAlphabet((byte) 128));
        assertFalse(b16.isInAlphabet((byte) 255));
        b16 = new Base16(true);
        for (char c = '0'; c <= '9'; c++) {
            assertTrue(b16.isInAlphabet((byte) c));
        }
        for (char c = 'a'; c <= 'f'; c++) {
            assertTrue(b16.isInAlphabet((byte) c));
        }
        for (char c = 'A'; c <= 'F'; c++) {
            assertFalse(b16.isInAlphabet((byte) c));
        }
        assertFalse(b16.isInAlphabet((byte) ('0' - 1)));
        assertFalse(b16.isInAlphabet((byte) ('9' + 1)));
        assertFalse(b16.isInAlphabet((byte) ('a' - 1)));
        assertFalse(b16.isInAlphabet((byte) ('f' + 1)));
        assertFalse(b16.isInAlphabet((byte) ('z' + 1)));
        b16 = new Base16(false);
        for (char c = '0'; c <= '9'; c++) {
            assertTrue(b16.isInAlphabet((byte) c));
        }
        for (char c = 'a'; c <= 'f'; c++) {
            assertFalse(b16.isInAlphabet((byte) c));
        }
        for (char c = 'A'; c <= 'F'; c++) {
            assertTrue(b16.isInAlphabet((byte) c));
        }
        assertFalse(b16.isInAlphabet((byte) ('0' - 1)));
        assertFalse(b16.isInAlphabet((byte) ('9' + 1)));
        assertFalse(b16.isInAlphabet((byte) ('A' - 1)));
        assertFalse(b16.isInAlphabet((byte) ('F' + 1)));
        assertFalse(b16.isInAlphabet((byte) ('Z' + 1)));
    }
}