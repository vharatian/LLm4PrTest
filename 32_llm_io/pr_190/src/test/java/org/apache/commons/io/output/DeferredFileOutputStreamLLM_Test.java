package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DeferredFileOutputStreamLLM_Test {

    public static IntStream data() {
        return IntStream.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096);
    }

    private final String testString = "0123456789";
    private final byte[] testBytes = testString.getBytes();

    /**
     * Test to ensure the 'closed' field is correctly initialized to false.
     */
    @Test
    public void testClosedFieldInitialization() {
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length + 42, null);
        assertFalse(dfos.closed, "The 'closed' field should be initialized to false.");
    }

    /**
     * Test to ensure the 'closed' field is correctly set to true after closing the stream.
     */
    @ParameterizedTest(name = "initialBufferSize = {0}")
    @MethodSource("data")
    public void testClosedFieldAfterClose(final int initialBufferSize) {
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length + 42, initialBufferSize, null);
        try {
            dfos.write(testBytes, 0, testBytes.length);
            dfos.close();
        } catch (final IOException e) {
            fail("Unexpected IOException");
        }
        assertTrue(dfos.closed, "The 'closed' field should be set to true after closing the stream.");
    }
}