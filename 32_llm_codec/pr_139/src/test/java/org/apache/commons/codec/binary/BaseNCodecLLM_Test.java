package org.apache.commons.codec.binary;

import org.apache.commons.codec.binary.BaseNCodec.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaseNCodecLLM_Test {

    BaseNCodec codec;

    @BeforeEach
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
    public void testContextToStringPlaceholders() {
        final Context context = new Context();
        context.ibitWorkArea = 123;
        context.lbitWorkArea = 456L;
        final String text = context.toString();
        assertTrue(text.contains("ibitWorkArea=123"));
        assertTrue(text.contains("lbitWorkArea=456"));
    }

    @Test
    public void testLineLengthRounding() {
        codec = new BaseNCodec(3, 4, 10, 2) {
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
        assertEquals(8, codec.lineLength); // 10 rounded down to nearest multiple of 4
    }

    @Test
    public void testGetEncodedLength() {
        byte[] input = new byte[100];
        long encodedLength = codec.getEncodedLength(input);
        assertTrue(encodedLength > 100);
    }
}