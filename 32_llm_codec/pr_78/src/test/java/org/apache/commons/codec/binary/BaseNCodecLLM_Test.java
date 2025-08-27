package org.apache.commons.codec.binary;

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
    public void testDecodeWithEmptyArray() {
        byte[] emptyArray = new byte[0];
        assertArrayEquals(emptyArray, codec.decode(emptyArray));
    }

    @Test
    public void testDecodeWithNullArray() {
        byte[] nullArray = null;
        assertArrayEquals(nullArray, codec.decode(nullArray));
    }

    @Test
    public void testEncodeWithEmptyArray() {
        byte[] emptyArray = new byte[0];
        assertArrayEquals(emptyArray, codec.encode(emptyArray));
    }

    @Test
    public void testEncodeWithNullArray() {
        byte[] nullArray = null;
        assertArrayEquals(nullArray, codec.encode(nullArray));
    }

    @Test
    public void testEncodeWithOffsetAndLengthEmptyArray() {
        byte[] emptyArray = new byte[0];
        assertArrayEquals(emptyArray, codec.encode(emptyArray, 0, 0));
    }

    @Test
    public void testEncodeWithOffsetAndLengthNullArray() {
        byte[] nullArray = null;
        assertArrayEquals(nullArray, codec.encode(nullArray, 0, 0));
    }
}