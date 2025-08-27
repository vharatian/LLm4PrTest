package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.io.TempDir;
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
    public void testThresholdReachedWithNewOutputStream(final int initialBufferSize, final @TempDir Path tempDir) throws IOException {
        final File testFile = tempDir.resolve("testThresholdReachedWithNewOutputStream.dat").toFile();
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length / 2, initialBufferSize, testFile);
        final int chunkSize = testBytes.length / 3;
        try {
            dfos.write(testBytes, 0, chunkSize);
            dfos.write(testBytes, chunkSize, chunkSize);
            dfos.write(testBytes, chunkSize * 2, testBytes.length - chunkSize * 2);
            dfos.close();
        } catch (final IOException e) {
            fail("Unexpected IOException");
        }
        assertFalse(dfos.isInMemory());
        assertNull(dfos.getData());
        verifyResultFile(testFile);
    }

    @ParameterizedTest(name = "initialBufferSize = {0}")
    @MethodSource("data")
    public void testWriteToWithNewInputStream(final int initialBufferSize, final @TempDir Path tempDir) throws IOException {
        final File testFile = tempDir.resolve("testWriteToWithNewInputStream.dat").toFile();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(initialBufferSize);
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length / 2, testFile);
        try {
            dfos.write(testBytes);
            assertTrue(testFile.exists());
            assertFalse(dfos.isInMemory());
            try {
                dfos.writeTo(baos);
                fail("Should not have been able to write before closing");
            } catch (final IOException ioe) {
                // Expected exception
            }
            dfos.close();
            dfos.writeTo(baos);
        } catch (final IOException ioe) {
            fail("Unexpected IOException");
        }
        final byte[] copiedBytes = baos.toByteArray();
        assertArrayEquals(testBytes, copiedBytes);
        verifyResultFile(testFile);
    }

    private void verifyResultFile(final File testFile) {
        try (InputStream fis = Files.newInputStream(testFile.toPath())) {
            assertEquals(testBytes.length, fis.available());
            final byte[] resultBytes = new byte[testBytes.length];
            assertEquals(testBytes.length, fis.read(resultBytes));
            assertArrayEquals(resultBytes, testBytes);
            assertEquals(-1, fis.read(resultBytes));
        } catch (final IOException e) {
            fail("Unexpected IOException");
        }
    }
}