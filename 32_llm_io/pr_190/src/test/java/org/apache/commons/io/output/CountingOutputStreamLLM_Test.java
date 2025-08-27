package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.NullInputStream;
import org.junit.jupiter.api.Test;

public class CountingOutputStreamLLM_Test {

    @Test
    public void testInitialCount() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (final CountingOutputStream cos = new CountingOutputStream(baos)) {
            // Verify that the initial count is zero
            assertEquals(0, cos.getCount(), "Initial count should be zero");
            assertEquals(0, cos.getByteCount(), "Initial byte count should be zero");
        }
    }

    @Test
    public void testResetCountAfterInitialization() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (final CountingOutputStream cos = new CountingOutputStream(baos)) {
            // Verify that resetCount after initialization returns zero
            assertEquals(0, cos.resetCount(), "Reset count after initialization should return zero");
            assertEquals(0, cos.resetByteCount(), "Reset byte count after initialization should return zero");
        }
    }
}