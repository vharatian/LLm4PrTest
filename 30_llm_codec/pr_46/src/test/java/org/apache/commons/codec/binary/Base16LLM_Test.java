package org.apache.commons.codec.binary;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Base16LLM_Test {

    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    private final Random random = new Random();

    public Random getRandom() {
        return this.random;
    }

    @Test
    public void testDecodeWithTrailingCharacter() {
        final String encoded = "aabbccddee";
        final Base16 b16 = new Base16(true, CodecPolicy.STRICT);
        try {
            b16.decode(StringUtils.getBytesUtf8(encoded));
            fail("Expected IllegalArgumentException due to trailing character");
        } catch (IllegalArgumentException e) {
            assertEquals("Strict decoding: Last encoded character is a valid base 16 alphabet character but not a possible encoding. Decoding requires at least two characters to create one byte.", e.getMessage());
        }
    }

    @Test
    public void testDecodeWithValidTrailingCharacter() {
        final String encoded = "aabbccdd";
        final Base16 b16 = new Base16(true, CodecPolicy.STRICT);
        final byte[] decoded = b16.decode(StringUtils.getBytesUtf8(encoded));
        assertArrayEquals(new byte[]{(byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd}, decoded);
    }

    @Test
    public void testDecodeWithSingleCharacter() {
        final String encoded = "a";
        final Base16 b16 = new Base16(true, CodecPolicy.LENIENT);
        try {
            b16.decode(StringUtils.getBytesUtf8(encoded));
            fail("Expected IllegalArgumentException due to single character");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid octet in encoded value: 97", e.getMessage());
        }
    }

    @Test
    public void testDecodeWithSingleCharacterStrict() {
        final String encoded = "a";
        final Base16 b16 = new Base16(true, CodecPolicy.STRICT);
        try {
            b16.decode(StringUtils.getBytesUtf8(encoded));
            fail("Expected IllegalArgumentException due to single character");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid octet in encoded value: 97", e.getMessage());
        }
    }

    @Test
    public void testDecodeWithOddNumberOfCharacters() {
        final String encoded = "aabbccd";
        final Base16 b16 = new Base16(true, CodecPolicy.LENIENT);
        final byte[] decoded = b16.decode(StringUtils.getBytesUtf8(encoded));
        assertArrayEquals(new byte[]{(byte) 0xaa, (byte) 0xbb, (byte) 0xcc}, decoded);
    }

    @Test
    public void testDecodeWithOddNumberOfCharactersStrict() {
        final String encoded = "aabbccd";
        final Base16 b16 = new Base16(true, CodecPolicy.STRICT);
        try {
            b16.decode(StringUtils.getBytesUtf8(encoded));
            fail("Expected IllegalArgumentException due to odd number of characters");
        } catch (IllegalArgumentException e) {
            assertEquals("Strict decoding: Last encoded character is a valid base 16 alphabet character but not a possible encoding. Decoding requires at least two characters to create one byte.", e.getMessage());
        }
    }

    @Test
    public void testDecodeWithInvalidCharacter() {
        final String encoded = "aabbccddg";
        final Base16 b16 = new Base16(true, CodecPolicy.LENIENT);
        try {
            b16.decode(StringUtils.getBytesUtf8(encoded));
            fail("Expected IllegalArgumentException due to invalid character");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid octet in encoded value: 103", e.getMessage());
        }
    }

    @Test
    public void testDecodeWithInvalidCharacterStrict() {
        final String encoded = "aabbccddg";
        final Base16 b16 = new Base16(true, CodecPolicy.STRICT);
        try {
            b16.decode(StringUtils.getBytesUtf8(encoded));
            fail("Expected IllegalArgumentException due to invalid character");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid octet in encoded value: 103", e.getMessage());
        }
    }

    @Test
    public void testEncodeDecodeWithLowerCase() {
        final String content = "Hello World";
        final Base16 base16 = new Base16(true);
        final byte[] encodedBytes = base16.encode(StringUtils.getBytesUtf8(content));
        final String encodedContent = StringUtils.newStringUtf8(encodedBytes);
        assertEquals("encoding hello world", "48656c6c6f20576f726c64", encodedContent);
        final byte[] decodedBytes = base16.decode(encodedBytes);
        final String decodedContent = StringUtils.newStringUtf8(decodedBytes);
        assertEquals("decoding hello world", content, decodedContent);
    }

    @Test
    public void testEncodeDecodeWithUpperCase() {
        final String content = "Hello World";
        final Base16 base16 = new Base16(false);
        final byte[] encodedBytes = base16.encode(StringUtils.getBytesUtf8(content));
        final String encodedContent = StringUtils.newStringUtf8(encodedBytes);
        assertEquals("encoding hello world", "48656C6C6F20576F726C64", encodedContent);
        final byte[] decodedBytes = base16.decode(encodedBytes);
        final String decodedContent = StringUtils.newStringUtf8(decodedBytes);
        assertEquals("decoding hello world", content, decodedContent);
    }
}