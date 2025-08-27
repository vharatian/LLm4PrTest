package org.apache.commons.compress.utils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class ArchiveUtilsLLM_Test {

    @Test
    public void testToString() {
        ArchiveEntry entry = new ArchiveEntry() {
            @Override
            public boolean isDirectory() {
                return false;
            }

            @Override
            public String getName() {
                return "test.txt";
            }

            @Override
            public long getSize() {
                return 1234;
            }
        };
        String result = ArchiveUtils.toString(entry);
        assertEquals("- 1234 test.txt", result);
    }

    @Test
    public void testMatchAsciiBuffer() {
        String expected = "test";
        byte[] buffer = "test".getBytes(StandardCharsets.US_ASCII);
        assertTrue(ArchiveUtils.matchAsciiBuffer(expected, buffer));
    }

    @Test
    public void testToAsciiBytes() {
        String input = "test";
        byte[] result = ArchiveUtils.toAsciiBytes(input);
        assertArrayEquals(input.getBytes(StandardCharsets.US_ASCII), result);
    }

    @Test
    public void testToAsciiString() {
        byte[] input = "test".getBytes(StandardCharsets.US_ASCII);
        String result = ArchiveUtils.toAsciiString(input);
        assertEquals("test", result);
    }

    @Test
    public void testIsEqual() {
        byte[] buffer1 = "test".getBytes(StandardCharsets.US_ASCII);
        byte[] buffer2 = "test".getBytes(StandardCharsets.US_ASCII);
        assertTrue(ArchiveUtils.isEqual(buffer1, buffer2));
    }

    @Test
    public void testIsArrayZero() {
        byte[] array = new byte[10];
        assertTrue(ArchiveUtils.isArrayZero(array, 10));
    }

    @Test
    public void testSanitize() {
        String input = "test\u0000string";
        String result = ArchiveUtils.sanitize(input);
        assertEquals("test?string", result);
    }
}