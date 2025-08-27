package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

public class ThresholdingOutputStreamLLM_Test {

    @Test
    public void testNoopOutputStreamGetter() throws Exception {
        final AtomicBoolean reached = new AtomicBoolean(false);
        try (final ThresholdingOutputStream tos = new ThresholdingOutputStream(3, os -> reached.set(true), null)) {
            tos.write('a');
            assertFalse(reached.get());
            tos.write('a');
            assertFalse(reached.get());
            tos.write('a');
            assertTrue(reached.get());
        }
    }

    @Test
    public void testNoopOutputStreamGetterWithNullConsumer() throws Exception {
        try (final ThresholdingOutputStream tos = new ThresholdingOutputStream(3, null, null)) {
            tos.write('a');
            tos.write('a');
            tos.write('a');
            assertFalse(tos.isThresholdExceeded());
            tos.write('a');
            assertTrue(tos.isThresholdExceeded());
        }
    }
}