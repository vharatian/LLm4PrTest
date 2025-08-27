package org.apache.commons.io.input;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.Reader;
import org.junit.Test;

public class CharSequenceReaderLLM_Test {

    @Test
    public void testReadCharArrayWithString() throws IOException {
        try (final Reader reader = new CharSequenceReader("FooBar")) {
            char[] chars = new char[6];
            assertEquals(6, reader.read(chars, 0, 6));
            checkArray(new char[] { 'F', 'o', 'o', 'B', 'a', 'r' }, chars);
        }
    }

    @Test
    public void testReadCharArrayWithStringBuilder() throws IOException {
        try (final Reader reader = new CharSequenceReader(new StringBuilder("FooBar"))) {
            char[] chars = new char[6];
            assertEquals(6, reader.read(chars, 0, 6));
            checkArray(new char[] { 'F', 'o', 'o', 'B', 'a', 'r' }, chars);
        }
    }

    @Test
    public void testReadCharArrayWithStringBuffer() throws IOException {
        try (final Reader reader = new CharSequenceReader(new StringBuffer("FooBar"))) {
            char[] chars = new char[6];
            assertEquals(6, reader.read(chars, 0, 6));
            checkArray(new char[] { 'F', 'o', 'o', 'B', 'a', 'r' }, chars);
        }
    }

    private void checkArray(final char[] expected, final char[] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertEquals("Compare[" + i + "]", expected[i], actual[i]);
        }
    }
}