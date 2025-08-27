package org.apache.commons.io.output;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.Test;

public class ThresholdingOutputStreamLLM_Test {

    @Test
    public void testWriteByteArray() throws Exception {
        final AtomicBoolean reached = new AtomicBoolean(false);
        try (final ThresholdingOutputStream tos = new ThresholdingOutputStream(3) {
            @Override
            protected OutputStream getStream() throws IOException {
                return new ByteArrayOutputStream(4);
            }
            @Override
            protected void thresholdReached() throws IOException {
                reached.set(true);
            }
        }) {
            tos.write(new byte[]{1, 2});
            assertFalse(reached.get());
            tos.write(new byte[]{3, 4});
            assertTrue(reached.get());
        }
    }

    @Test
    public void testWriteByteArrayWithOffsetAndLength() throws Exception {
        final AtomicBoolean reached = new AtomicBoolean(false);
        try (final ThresholdingOutputStream tos = new ThresholdingOutputStream(3) {
            @Override
            protected OutputStream getStream() throws IOException {
                return new ByteArrayOutputStream(4);
            }
            @Override
            protected void thresholdReached() throws IOException {
                reached.set(true);
            }
        }) {
            tos.write(new byte[]{1, 2, 3, 4}, 0, 2);
            assertFalse(reached.get());
            tos.write(new byte[]{5, 6, 7, 8}, 2, 2);
            assertTrue(reached.get());
        }
    }
}