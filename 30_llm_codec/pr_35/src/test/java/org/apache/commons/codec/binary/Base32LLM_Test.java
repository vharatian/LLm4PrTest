package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class Base32LLM_Test {

    @Test
    public void testBase32DecodingOfTrailing5Bits() {
        assertBase32DecodingOfTrailingBits(5);
    }

    @Test
    public void testBase32DecodingOfTrailing10Bits() {
        assertBase32DecodingOfTrailingBits(10);
    }

    @Test
    public void testBase32DecodingOfTrailing15Bits() {
        assertBase32DecodingOfTrailingBits(15);
    }

    @Test
    public void testBase32DecodingOfTrailing20Bits() {
        assertBase32DecodingOfTrailingBits(20);
    }

    @Test
    public void testBase32DecodingOfTrailing25Bits() {
        assertBase32DecodingOfTrailingBits(25);
    }

    @Test
    public void testBase32DecodingOfTrailing30Bits() {
        assertBase32DecodingOfTrailingBits(30);
    }

    @Test
    public void testBase32DecodingOfTrailing35Bits() {
        assertBase32DecodingOfTrailingBits(35);
    }

    private static void assertBase32DecodingOfTrailingBits(final int nbits) {
        final Base32 codec = new Base32();
        final byte[] encoded = new byte[nbits / 5];
        Arrays.fill(encoded, ENCODE_TABLE[0]);
        final int discard = nbits % 8;
        final int emptyBitsMask = (1 << discard) - 1;
        final int last = encoded.length - 1;
        for (int i = 0; i < 32; i++) {
            encoded[last] = ENCODE_TABLE[i];
            if ((i & emptyBitsMask) != 0) {
                try {
                    codec.decode(encoded);
                    fail("Final base-32 digit should not be allowed");
                } catch (final IllegalArgumentException ex) {
                }
            } else {
                final byte[] decoded = codec.decode(encoded);
                final int bitsEncoded = i >> discard;
                assertEquals("Invalid decoding of last character", bitsEncoded, decoded[decoded.length - 1]);
            }
        }
    }

    @Test
    public void testBase32StrictDecodingTrailingCharacters() {
        final Base32 codec = new Base32();
        codec.setStrictDecoding(true);

        final String[] invalidEncodings = {
            "M=======", // 1 trailing character
            "MZX======", // 3 trailing characters
            "MZXW6Y====" // 6 trailing characters
        };

        for (String encoding : invalidEncodings) {
            try {
                codec.decode(encoding);
                fail("Expected IllegalArgumentException for encoding: " + encoding);
            } catch (IllegalArgumentException e) {
                // Expected exception
            }
        }
    }
}