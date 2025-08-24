package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.END_OF_STREAM;
import static org.apache.commons.csv.Constants.UNDEFINED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

public class ExtendedBufferedReaderLLM_Test {

    @Test
    public void testEndOfStreamIncrementsEolCounter() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("a")) {
            assertEquals('a', br.read());
            assertEquals(0, br.getCurrentLineNumber());
            assertEquals(END_OF_STREAM, br.read());
            assertEquals(1, br.getCurrentLineNumber());
        }
    }

    @Test
    public void testEndOfStreamDoesNotIncrementEolCounterAfterCR() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("\r")) {
            assertEquals('\r', br.read());
            assertEquals(1, br.getCurrentLineNumber());
            assertEquals(END_OF_STREAM, br.read());
            assertEquals(1, br.getCurrentLineNumber());
        }
    }

    @Test
    public void testEndOfStreamDoesNotIncrementEolCounterAfterLF() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("\n")) {
            assertEquals('\n', br.read());
            assertEquals(1, br.getCurrentLineNumber());
            assertEquals(END_OF_STREAM, br.read());
            assertEquals(1, br.getCurrentLineNumber());
        }
    }

    @Test
    public void testEndOfStreamDoesNotIncrementEolCounterAfterCRLF() throws Exception {
        try (final ExtendedBufferedReader br = createBufferedReader("\r\n")) {
            assertEquals('\r', br.read());
            assertEquals(1, br.getCurrentLineNumber());
            assertEquals('\n', br.read());
            assertEquals(1, br.getCurrentLineNumber());
            assertEquals(END_OF_STREAM, br.read());
            assertEquals(1, br.getCurrentLineNumber());
        }
    }

    private ExtendedBufferedReader createBufferedReader(final String s) {
        return new ExtendedBufferedReader(new StringReader(s));
    }
}