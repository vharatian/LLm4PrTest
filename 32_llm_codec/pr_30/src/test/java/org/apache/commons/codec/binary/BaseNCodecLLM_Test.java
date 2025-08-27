package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import org.apache.commons.codec.binary.BaseNCodec.Context;
import org.junit.Before;
import org.junit.Test;

public class BaseNCodecLLM_Test {

    BaseNCodec codec;

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
    public void testResizeBuffer() {
        final Context context = new Context();
        context.buffer = new byte[10];
        byte[] resizedBuffer = BaseNCodec.resizeBuffer(context, 20);
        assertNotNull(resizedBuffer);
        assertTrue(resizedBuffer.length >= 20);
    }

    @Test
    public void testResizeBufferWithOverflow() {
        final Context context = new Context();
        context.buffer = new byte[10];
        assertThrows(OutOfMemoryError.class, () -> {
            BaseNCodec.resizeBuffer(context, Integer.MAX_VALUE);
        });
    }

    @Test
    public void testCompareUnsigned() {
        assertEquals(0, BaseNCodec.compareUnsigned(10, 10));
        assertTrue(BaseNCodec.compareUnsigned(10, 20) < 0);
        assertTrue(BaseNCodec.compareUnsigned(20, 10) > 0);
    }

    @Test
    public void testCreatePositiveCapacity() {
        assertEquals(BaseNCodec.MAX_BUFFER_SIZE, BaseNCodec.createPositiveCapacity(BaseNCodec.MAX_BUFFER_SIZE));
        assertEquals(Integer.MAX_VALUE, BaseNCodec.createPositiveCapacity(Integer.MAX_VALUE));
    }

    @Test(expected = OutOfMemoryError.class)
    public void testCreatePositiveCapacityThrowsOnNegative() {
        BaseNCodec.createPositiveCapacity(-1);
    }
}