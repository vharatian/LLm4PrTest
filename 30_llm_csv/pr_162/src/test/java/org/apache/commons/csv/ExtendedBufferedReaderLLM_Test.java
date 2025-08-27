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

    /**
     * Test the new lookAhead(char[] buf) method.
     */
    @Test
    public void testLookAheadWithBuffer() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("abcdefg")) {
            final char[] buf = new char[3];
            assertArrayEquals(new char[]{'a', 'b', 'c'}, br.lookAhead(buf));
            assertEquals('a', br.read());
            assertArrayEquals(new char[]{'b', 'c', 'd'}, br.lookAhead(buf));
            assertEquals('b', br.read());
            assertArrayEquals(new char[]{'c', 'd', 'e'}, br.lookAhead(buf));
        }
    }

    /**
     * Test the new lookAhead(char[] buf) method with end of stream.
     */
    @Test
    public void testLookAheadWithBufferEndOfStream() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("ab")) {
            final char[] buf = new char[3];
            assertArrayEquals(new char[]{'a', 'b', END_OF_STREAM}, br.lookAhead(buf));
            assertEquals('a', br.read());
            assertArrayEquals(new char[]{'b', END_OF_STREAM, END_OF_STREAM}, br.lookAhead(buf));
            assertEquals('b', br.read());
            assertArrayEquals(new char[]{END_OF_STREAM, END_OF_STREAM, END_OF_STREAM}, br.lookAhead(buf));
        }
    }

    /**
     * Test the new lookAhead(char[] buf) method with empty input.
     */
    @Test
    public void testLookAheadWithBufferEmptyInput() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("")) {
            final char[] buf = new char[3];
            assertArrayEquals(new char[]{END_OF_STREAM, END_OF_STREAM, END_OF_STREAM}, br.lookAhead(buf));
        }
    }
}