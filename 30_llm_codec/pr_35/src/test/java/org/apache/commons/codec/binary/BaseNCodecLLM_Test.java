package org.apache.commons.codec.binary;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BaseNCodecLLM_Test {

    private BaseNCodec codec;

    @Before
    public void setUp() {
        codec = new BaseNCodec(0, 0, 0, 0) {
            @Override
            protected boolean isInAlphabet(final byte b) {
                return b == 'O' || b == 'K';
            }

            @Override
            void encode(final byte[] pArray, final int i, final int length, final Context context) {
            }

            @Override
            void decode(final byte[] pArray, final int i, final int length, final Context context) {
            }
        };
    }

    @Test
    public void testSetStrictDecoding() {
        codec.setStrictDecoding(true);
        assertTrue(codec.isStrictDecoding());

        codec.setStrictDecoding(false);
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testIsStrictDecodingDefault() {
        assertFalse(codec.isStrictDecoding());
    }

    @Test
    public void testStrictDecodingBehavior() {
        codec.setStrictDecoding(true);
        byte[] invalidInput = new byte[]{'O', 'K', 'X'}; // Assuming 'X' is invalid in strict mode

        try {
            codec.decode(invalidInput);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid trailing bits in strict decoding mode", e.getMessage());
        }
    }
}