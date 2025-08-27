package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class DeferredFileOutputStreamLLM_Test {

    private final String testString = "0123456789";
    private final byte[] testBytes = testString.getBytes();

    @Test
    public void testWriteToThrowsIOExceptionWhenNotClosed() {
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length + 42, null);
        try {
            dfos.write(testBytes, 0, testBytes.length);
        } catch (final IOException e) {
            fail("Unexpected IOException");
        }

        assertThrows(IOException.class, () -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            dfos.writeTo(baos);
        }, "Stream not closed");
    }

    @Test
    public void testWriteToThrowsIOExceptionWhenNotClosedWithFile() {
        final File testFile = new File("testWriteToThrowsIOExceptionWhenNotClosedWithFile.dat");
        testFile.delete();
        final DeferredFileOutputStream dfos = new DeferredFileOutputStream(testBytes.length - 5, testFile);
        try {
            dfos.write(testBytes, 0, testBytes.length);
        } catch (final IOException e) {
            fail("Unexpected IOException");
        }

        assertThrows(IOException.class, () -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            dfos.writeTo(baos);
        }, "Stream not closed");

        testFile.delete();
    }
}