package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.END_OF_STREAM;
import static org.apache.commons.csv.Constants.UNDEFINED;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

public class ExtendedBufferedReaderLLM_Test {

    private ExtendedBufferedReader createBufferedReader(final String s) {
        return new ExtendedBufferedReader(new StringReader(s));
    }

    @Test
    public void testReadLineWithCRLF() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("foo\r\nbar\r\nbaz")) {
            assertEquals("foo", br.readLine());
            assertEquals("bar", br.readLine());
            assertEquals("baz", br.readLine());
            assertNull(br.readLine());
        }
    }

    @Test
    public void testReadLineWithCR() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("foo\rbar\rbaz")) {
            assertEquals("foo", br.readLine());
            assertEquals("bar", br.readLine());
            assertEquals("baz", br.readLine());
            assertNull(br.readLine());
        }
    }

    @Test
    public void testReadLineWithMixedEOL() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("foo\rbar\nbaz\r\nqux")) {
            assertEquals("foo", br.readLine());
            assertEquals("bar", br.readLine());
            assertEquals("baz", br.readLine());
            assertEquals("qux", br.readLine());
            assertNull(br.readLine());
        }
    }

    @Test
    public void testReadLineWithOnlyCR() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("\r")) {
            assertEquals("", br.readLine());
            assertNull(br.readLine());
        }
    }

    @Test
    public void testReadLineWithOnlyLF() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("\n")) {
            assertEquals("", br.readLine());
            assertNull(br.readLine());
        }
    }

    @Test
    public void testReadLineWithOnlyCRLF() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("\r\n")) {
            assertEquals("", br.readLine());
            assertNull(br.readLine());
        }
    }

    @Test
    public void testReadLineWithEmptyString() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("")) {
            assertNull(br.readLine());
        }
    }
}