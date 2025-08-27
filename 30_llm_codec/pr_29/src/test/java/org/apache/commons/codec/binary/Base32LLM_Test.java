package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Arrays;
import org.junit.Test;

public class Base32LLM_Test {

    private static final byte[] ENCODE_TABLE = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '2', '3', '4', '5', '6', '7',
    };

    @Test
    public void testBase32DecodingOfTrailing10Bits() {
        assertBase32DecodingOfTrailingBits(10, 0x03L);
    }

    @Test
    public void testBase32DecodingOfTrailing15Bits() {
        assertBase32DecodingOfTrailingBits(15, 0x7fL);
    }

    @Test
    public void testBase32DecodingOfTrailing20Bits() {
        assertBase32DecodingOfTrailingBits(20, 0x0fL);
    }

    @Test
    public void testBase32DecodingOfTrailing25Bits() {
        assertBase32DecodingOfTrailingBits(25, 0x01L);
    }

    @Test
    public void testBase32DecodingOfTrailing30Bits() {
        assertBase32DecodingOfTrailingBits(30, 0x3fL);
    }

    @Test
    public void testBase32DecodingOfTrailing35Bits() {
        assertBase32DecodingOfTrailingBits(35, 0x07L);
    }

    private static void assertBase32DecodingOfTrailingBits(int nbits, long emptyBitsMask) {
        final Base32 codec = new Base32();
        final byte[] encoded = new byte[nbits / 5];
        Arrays.fill(encoded, ENCODE_TABLE[0]);
        final int discard = nbits % 8;
        final int last = encoded.length - 1;
        for (int i = 0; i < 32; i++) {
            encoded[last] = ENCODE_TABLE[i];
            if ((i & emptyBitsMask) != 0) {
                try {
                    codec.decode(encoded);
                    fail("Final base-32 digit should not be allowed");
                } catch (final IllegalArgumentException ex) {
                    // Expected exception
                }
            } else {
                final byte[] decoded = codec.decode(encoded);
                final int bitsEncoded = i >> discard;
                assertEquals("Invalid decoding of last character", bitsEncoded, decoded[decoded.length - 1]);
            }
        }
    }
}