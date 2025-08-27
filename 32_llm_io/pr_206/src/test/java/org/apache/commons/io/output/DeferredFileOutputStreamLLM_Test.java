package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @ParameterizedTest(name = "initialBufferSize = {0}")
    @MethodSource("data")
    public void testToInputStreamInMemory(final int initialBufferSize) throws IOException {
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length + 42, initialBufferSize, null);
        dfos.write(testBytes, 0, testBytes.length);
        dfos.close();

        assertTrue(dfos.isInMemory());

        try (InputStream inputStream = dfos.toInputStream()) {
            byte[] resultBytes = new byte[testBytes.length];
            int bytesRead = inputStream.read(resultBytes);
            assertEquals(testBytes.length, bytesRead);
            assertTrue(Arrays.equals(resultBytes, testBytes));
        }
    }

    @ParameterizedTest(name = "initialBufferSize = {0}")
    @MethodSource("data")
    public void testToInputStreamOnDisk(final int initialBufferSize) throws IOException {
        final File testFile = new File("testToInputStreamOnDisk.dat");
        testFile.delete();
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length - 5, initialBufferSize, testFile);
        dfos.write(testBytes, 0, testBytes.length);
        dfos.close();

        assertFalse(dfos.isInMemory());

        try (InputStream inputStream = dfos.toInputStream()) {
            byte[] resultBytes = new byte[testBytes.length];
            int bytesRead = inputStream.read(resultBytes);
            assertEquals(testBytes.length, bytesRead);
            assertTrue(Arrays.equals(resultBytes, testBytes));
        }

        testFile.delete();
    }

    @Test
    public void testToInputStreamNotClosed() throws IOException {
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length + 42, null);
        dfos.write(testBytes, 0, testBytes.length);

        IOException exception = assertThrows(IOException.class, dfos::toInputStream);
        assertEquals("Stream not closed", exception.getMessage());
    }
}