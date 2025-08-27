package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    public void testEnsureBufferSizeWithMinCapacity() {
        final BaseNCodec ncodec = new NoOpBaseNCodec();
        final Context context = new Context();
        context.buffer = null;
        context.pos = 0;
        context.readPos = 0;

        // Ensure buffer size with a specific minimum capacity
        int minCapacity = 5000;
        ncodec.ensureBufferSize(minCapacity, context);

        // Verify that the buffer is initialized with at least the minimum capacity
        assertNotNull("Buffer should be initialized", context.buffer);
        assertEquals("Buffer should be initialized to at least the minimum capacity", Math.max(minCapacity, ncodec.getDefaultBufferSize()), context.buffer.length);
    }

    private static class NoOpBaseNCodec extends BaseNCodec {
        NoOpBaseNCodec() {
            super(0, 0, 0, 0);
        }

        @Override
        void encode(final byte[] pArray, final int i, final int length, final Context context) {
        }

        @Override
        void decode(final byte[] pArray, final int i, final int length, final Context context) {
        }

        @Override
        protected boolean isInAlphabet(final byte value) {
            return false;
        }
    }
}