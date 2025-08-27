package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import org.junit.jupiter.api.Test;

public class CharSequenceReaderLLM_Test {

    private static final char NONE = (new char[1])[0];

    @Test
    public void testConstructorWithStart() throws IOException {
        try (final Reader reader = new CharSequenceReader("FooBar", 3)) {
            checkRead(reader, "Bar");
            assertEquals(-1, reader.read());
        }
    }

    @Test
    public void testConstructorWithStartAndEnd() throws IOException {
        try (final Reader reader = new CharSequenceReader("FooBar", 1, 4)) {
            checkRead(reader, "ooB");
            assertEquals(-1, reader.read());
        }
    }

    @Test
    public void testConstructorWithInvalidStart() {
        assertThrows(IllegalArgumentException.class, () -> new CharSequenceReader("FooBar", -1));
    }

    @Test
    public void testConstructorWithInvalidEnd() {
        assertThrows(IllegalArgumentException.class, () -> new CharSequenceReader("FooBar", 3, 2));
    }

    @Test
    public void testCloseWithStartAndEnd() throws IOException {
        try (final Reader reader = new CharSequenceReader("FooBar", 1, 4)) {
            checkRead(reader, "ooB");
            reader.close();
            checkRead(reader, "ooB");
        }
    }

    @Test
    public void testSkipWithStartAndEnd() throws IOException {
        try (final Reader reader = new CharSequenceReader("FooBar", 1, 4)) {
            assertEquals(2, reader.skip(2));
            checkRead(reader, "B");
            assertEquals(-1, reader.skip(1));
        }
    }

    @Test
    public void testReadWithStartAndEnd() throws IOException {
        final String value = "FooBar";
        testReadWithStartAndEnd(value);
        testReadWithStartAndEnd(new StringBuilder(value));
        testReadWithStartAndEnd(new StringBuffer(value));
        testReadWithStartAndEnd(CharBuffer.wrap(value));
    }

    private void testReadWithStartAndEnd(final CharSequence charSequence) throws IOException {
        try (final Reader reader = new CharSequenceReader(charSequence, 1, 4)) {
            assertEquals('o', reader.read());
            assertEquals('o', reader.read());
            assertEquals('B', reader.read());
            assertEquals(-1, reader.read());
        }
    }

    @Test
    public void testReadCharArrayWithStartAndEnd() throws IOException {
        final String value = "FooBar";
        testReadCharArrayWithStartAndEnd(value);
        testReadCharArrayWithStartAndEnd(new StringBuilder(value));
        testReadCharArrayWithStartAndEnd(new StringBuffer(value));
        testReadCharArrayWithStartAndEnd(CharBuffer.wrap(value));
    }

    private void testReadCharArrayWithStartAndEnd(final CharSequence charSequence) throws IOException {
        try (final Reader reader = new CharSequenceReader(charSequence, 1, 4)) {
            char[] chars = new char[2];
            assertEquals(2, reader.read(chars));
            checkArray(new char[] { 'o', 'o' }, chars);
            chars = new char[3];
            assertEquals(1, reader.read(chars));
            checkArray(new char[] { 'B', NONE, NONE }, chars);
            assertEquals(-1, reader.read(chars));
        }
    }

    @Test
    public void testReadCharArrayPortionWithStartAndEnd() throws IOException {
        final String value = "FooBar";
        testReadCharArrayPortionWithStartAndEnd(value);
        testReadCharArrayPortionWithStartAndEnd(new StringBuilder(value));
        testReadCharArrayPortionWithStartAndEnd(new StringBuffer(value));
        testReadCharArrayPortionWithStartAndEnd(CharBuffer.wrap(value));
    }

    private void testReadCharArrayPortionWithStartAndEnd(final CharSequence charSequence) throws IOException {
        final char[] chars = new char[10];
        try (final Reader reader = new CharSequenceReader(charSequence, 1, 4)) {
            assertEquals(2, reader.read(chars, 3, 2));
            checkArray(new char[] { NONE, NONE, NONE, 'o', 'o' }, chars);
            assertEquals(1, reader.read(chars, 0, 1));
            checkArray(new char[] { 'B', NONE, NONE, 'o', 'o' }, chars);
            assertEquals(-1, reader.read(chars));
        }
    }

    private void checkRead(final Reader reader, final String expected) throws IOException {
        for (int i = 0; i < expected.length(); i++) {
            assertEquals(expected.charAt(i), (char)reader.read(), "Read[" + i + "] of '" + expected + "'");
        }
    }

    private void checkArray(final char[] expected, final char[] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i], "Compare[" +i + "]");
        }
    }
}