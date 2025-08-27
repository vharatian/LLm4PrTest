package org.apache.commons.codec.binary;

import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base32LLM_Test {

    @Test
    public void testConstructorWithNegativeLineLength() {
        assertThrows(IllegalArgumentException.class, () -> new Base32(-1, null), "Negative line length with null line separator");
    }

    @Test
    public void testConstructorWithNullLineSeparator() {
        assertThrows(IllegalArgumentException.class, () -> new Base32(32, null), "Null line separator");
    }

    @Test
    public void testConstructorWithLineSeparatorContainingAlphabet() {
        assertThrows(IllegalArgumentException.class, () -> new Base32(32, new byte[] {'A'}), "'A' as a line separator");
        assertThrows(IllegalArgumentException.class, () -> new Base32(32, new byte[] {'='}), "'=' as a line separator");
        assertThrows(IllegalArgumentException.class, () -> new Base32(32, new byte[] {'A', '$'}), "'A$' as a line separator");
    }

    @Test
    public void testConstructorWithInvalidPadding() {
        assertThrows(IllegalArgumentException.class, () -> new Base32(32, new byte[] {'\n'}, false, (byte) 'A'), "'A' as padding");
        assertThrows(IllegalArgumentException.class, () -> new Base32(32, new byte[] {'\n'}, false, (byte) ' '), "' ' as padding");
    }

    @Test
    public void testValidConstructor() {
        Base32 base32 = new Base32(32, new byte[] {' ', '$', '\n', '\r', '\t'});
        assertNotNull(base32);
    }

    @Test
    public void testIsInAlphabetWithVariousBytes() {
        Base32 b32 = new Base32(true);
        assertFalse(b32.isInAlphabet((byte)0));
        assertFalse(b32.isInAlphabet((byte)1));
        assertFalse(b32.isInAlphabet((byte)-1));
        assertFalse(b32.isInAlphabet((byte)-15));
        assertFalse(b32.isInAlphabet((byte)-32));
        assertFalse(b32.isInAlphabet((byte)127));
        assertFalse(b32.isInAlphabet((byte)128));
        assertFalse(b32.isInAlphabet((byte)255));
    }

    @Test
    public void testIsInAlphabetWithBase32Characters() {
        Base32 b32 = new Base32(false);
        for (char c = '2'; c <= '7'; c++) {
            assertTrue(b32.isInAlphabet((byte) c));
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            assertTrue(b32.isInAlphabet((byte) c));
        }
        for (char c = 'a'; c <= 'z'; c++) {
            assertTrue(b32.isInAlphabet((byte) c));
        }
        assertFalse(b32.isInAlphabet((byte) ('1')));
        assertFalse(b32.isInAlphabet((byte) ('8')));
        assertFalse(b32.isInAlphabet((byte) ('A' - 1)));
        assertFalse(b32.isInAlphabet((byte) ('Z' + 1)));
    }

    @Test
    public void testIsInAlphabetWithBase32HexCharacters() {
        Base32 b32 = new Base32(true);
        for (char c = '0'; c <= '9'; c++) {
            assertTrue(b32.isInAlphabet((byte) c));
        }
        for (char c = 'A'; c <= 'V'; c++) {
            assertTrue(b32.isInAlphabet((byte) c));
        }
        for (char c = 'a'; c <= 'v'; c++) {
            assertTrue(b32.isInAlphabet((byte) c));
        }
        assertFalse(b32.isInAlphabet((byte) ('0' - 1)));
        assertFalse(b32.isInAlphabet((byte) ('9' + 1)));
        assertFalse(b32.isInAlphabet((byte) ('A' - 1)));
        assertFalse(b32.isInAlphabet((byte) ('V' + 1)));
        assertFalse(b32.isInAlphabet((byte) ('a' - 1)));
        assertFalse(b32.isInAlphabet((byte) ('v' + 1)));
    }
}