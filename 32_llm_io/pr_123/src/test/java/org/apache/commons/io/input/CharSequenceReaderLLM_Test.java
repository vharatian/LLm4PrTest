package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class CharSequenceReaderLLM_Test {

    private static final char NONE = (new char[1])[0];

    @Test
    public void testSkipZero() throws IOException {
        final Reader reader = new CharSequenceReader("FooBar");
        assertEquals(0, reader.skip(0));
        checkRead(reader, "FooBar");
        reader.close();
        assertEquals(0, reader.skip(0));
        assertEquals(-1, reader.read());
        
        final Reader subReader = new CharSequenceReader("xFooBarx", 1, 7);
        assertEquals(0, subReader.skip(0));
        checkRead(subReader, "FooBar");
        subReader.close();
        assertEquals(0, subReader.skip(0));
        assertEquals(-1, subReader.read());
    }

    private void checkRead(final Reader reader, final String expected) throws IOException {
        for (int i = 0; i < expected.length(); i++) {
            assertEquals(expected.charAt(i), (char)reader.read(), "Read[" + i + "] of '" + expected + "'");
        }
    }
}