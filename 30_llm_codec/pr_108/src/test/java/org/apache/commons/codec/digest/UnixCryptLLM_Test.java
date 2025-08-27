package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class UnixCryptLLM_Test {

    @Test
    public void testCtor() {
        assertNotNull(new UnixCrypt());
    }

    @Test
    public void testUnixCryptStrings() {
        assertEquals("xxWAum7tHdIUw", Crypt.crypt("secret", "xx"));
        assertEquals("12UFlHxel6uMM", Crypt.crypt("", "12"));
        assertEquals("12FJgqDtVOg7Q", Crypt.crypt("secret", "12"));
        assertEquals("12FJgqDtVOg7Q", Crypt.crypt("secret", "12345678"));
    }

    @Test
    public void testUnixCryptBytes() {
        assertEquals("12UFlHxel6uMM", Crypt.crypt(new byte[0], "12"));
        assertEquals("./287bds2PjVw", Crypt.crypt("t\u00e4st", "./"));
        assertEquals("./bLIFNqo9XKQ", Crypt.crypt("t\u00e4st".getBytes(StandardCharsets.ISO_8859_1), "./"));
        assertEquals("./bLIFNqo9XKQ", Crypt.crypt(new byte[]{(byte) 0x74, (byte) 0xe4, (byte) 0x73, (byte) 0x74}, "./"));
    }

    @Test
    public void testUnixCryptExplicitCall() {
        assertTrue(UnixCrypt.crypt("secret".getBytes()).matches("^[a-zA-Z0-9./]{13}$"));
        assertTrue(UnixCrypt.crypt("secret".getBytes(), null).matches("^[a-zA-Z0-9./]{13}$"));
    }

    @Test
    public void testUnixCryptWithHalfSalt() {
        assertThrows(IllegalArgumentException.class, () -> UnixCrypt.crypt("secret", "x"));
    }

    @Test
    public void testUnicCryptInvalidSalt() {
        assertThrows(IllegalArgumentException.class, () -> UnixCrypt.crypt("secret", "$a"));
    }

    @Test
    public void testUnixCryptNullData() {
        assertThrows(NullPointerException.class, () -> UnixCrypt.crypt((byte[]) null));
    }

    @Test
    public void testUnixCryptWithEmptySalt() {
        assertThrows(IllegalArgumentException.class, () -> UnixCrypt.crypt("secret", ""));
    }

    @Test
    public void testUnixCryptWithoutSalt() {
        final String hash = UnixCrypt.crypt("foo");
        assertTrue(hash.matches("^[a-zA-Z0-9./]{13}$"));
        final String hash2 = UnixCrypt.crypt("foo");
        assertNotSame(hash, hash2);
    }

    // New tests to cover changes in the diff file

    @Test
    public void testArrayDeclarations() {
        // Test to ensure array declarations are correctly handled
        int[] conSalt = UnixCrypt.CON_SALT;
        assertNotNull(conSalt);
        assertEquals(128, conSalt.length);

        int[] cov2char = UnixCrypt.COV2CHAR;
        assertNotNull(cov2char);
        assertEquals(64, cov2char.length);

        char[] saltChars = UnixCrypt.SALT_CHARS;
        assertNotNull(saltChars);
        assertEquals(64, saltChars.length);

        boolean[] shift2 = UnixCrypt.SHIFT2;
        assertNotNull(shift2);
        assertEquals(16, shift2.length);

        int[][] skb = UnixCrypt.SKB;
        assertNotNull(skb);
        assertEquals(8, skb.length);

        int[][] sptrans = UnixCrypt.SPTRANS;
        assertNotNull(sptrans);
        assertEquals(8, sptrans.length);
    }

    @Test
    public void testCryptMethodWithByteArray() {
        // Test to ensure crypt method works with byte array input
        byte[] input = "test".getBytes(StandardCharsets.UTF_8);
        String result = UnixCrypt.crypt(input);
        assertNotNull(result);
        assertTrue(result.matches("^[a-zA-Z0-9./]{13}$"));
    }

    @Test
    public void testCryptMethodWithSalt() {
        // Test to ensure crypt method works with provided salt
        byte[] input = "test".getBytes(StandardCharsets.UTF_8);
        String salt = "ab";
        String result = UnixCrypt.crypt(input, salt);
        assertNotNull(result);
        assertEquals('a', result.charAt(0));
        assertEquals('b', result.charAt(1));
    }

    @Test
    public void testCryptMethodWithInvalidSalt() {
        // Test to ensure crypt method throws exception for invalid salt
        byte[] input = "test".getBytes(StandardCharsets.UTF_8);
        String invalidSalt = "!";
        assertThrows(IllegalArgumentException.class, () -> UnixCrypt.crypt(input, invalidSalt));
    }
}