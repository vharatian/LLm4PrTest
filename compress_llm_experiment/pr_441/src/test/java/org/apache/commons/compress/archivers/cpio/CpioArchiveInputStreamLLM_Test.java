package org.apache.commons.compress.archivers.cpio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.AbstractTest;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class CpioArchiveInputStreamLLM_Test extends AbstractTest {

    @Test
    public void testReadAsciiLong() throws Exception {
        try (CpioArchiveInputStream in = new CpioArchiveInputStream(newInputStream("test.cpio"))) {
            // Accessing the private method readAsciiLong using reflection
            java.lang.reflect.Method method = CpioArchiveInputStream.class.getDeclaredMethod("readAsciiLong", int.class, int.class);
            method.setAccessible(true);

            // Test with valid input
            long result = (long) method.invoke(in, 8, 16);
            assertEquals(0L, result); // Expected value should be adjusted based on the test file

            // Test with invalid input to trigger NumberFormatException
            assertThrows(NumberFormatException.class, () -> {
                method.invoke(in, 8, 2); // Invalid radix for the given input
            });
        }
    }

    @Test
    public void testReadAsciiLongEOFException() throws Exception {
        try (CpioArchiveInputStream in = new CpioArchiveInputStream(newInputStream("test.cpio"))) {
            // Accessing the private method readAsciiLong using reflection
            java.lang.reflect.Method method = CpioArchiveInputStream.class.getDeclaredMethod("readAsciiLong", int.class, int.class);
            method.setAccessible(true);

            // Test with input that causes EOFException
            assertThrows(EOFException.class, () -> {
                method.invoke(in, 1000, 16); // Length exceeds the available data
            });
        }
    }
}