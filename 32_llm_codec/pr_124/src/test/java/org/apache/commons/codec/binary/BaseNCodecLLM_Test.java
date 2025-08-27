package org.apache.commons.codec.binary;

import org.apache.commons.codec.binary.BaseNCodec.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
    public void testCreatePositiveCapacity() {
        // Test when minCapacity is less than MAX_BUFFER_SIZE
        int minCapacity = BaseNCodec.MAX_BUFFER_SIZE - 1;
        int result = BaseNCodec.createPositiveCapacity(minCapacity);
        assertEquals(BaseNCodec.MAX_BUFFER_SIZE, result);

        // Test when minCapacity is greater than MAX_BUFFER_SIZE
        minCapacity = BaseNCodec.MAX_BUFFER_SIZE + 1;
        result = BaseNCodec.createPositiveCapacity(minCapacity);
        assertEquals(minCapacity, result);

        // Test when minCapacity is equal to MAX_BUFFER_SIZE
        minCapacity = BaseNCodec.MAX_BUFFER_SIZE;
        result = BaseNCodec.createPositiveCapacity(minCapacity);
        assertEquals(BaseNCodec.MAX_BUFFER_SIZE, result);

        // Test when minCapacity is negative (should throw OutOfMemoryError)
        minCapacity = -1;
        assertThrows(OutOfMemoryError.class, () -> BaseNCodec.createPositiveCapacity(minCapacity));
    }
}