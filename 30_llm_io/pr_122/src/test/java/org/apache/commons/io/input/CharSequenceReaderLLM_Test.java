package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.Reader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CharSequenceReaderLLM_Test {

    /**
     * Test the ready() method to ensure it correctly identifies when the stream is ready to be read.
     */
    @Test
    public void testReady() throws IOException {
        try (final Reader reader = new CharSequenceReader("FooBar")) {
            assertTrue(reader.ready(), "Reader should be ready at the beginning");
            reader.read();
            assertTrue(reader.ready(), "Reader should be ready after reading one character");
            reader.skip(5);
            assertFalse(reader.ready(), "Reader should not be ready after reading all characters");
        }

        try (final Reader reader = new CharSequenceReader("xFooBarx", 1, 7)) {
            assertTrue(reader.ready(), "SubReader should be ready at the beginning");
            reader.read();
            assertTrue(reader.ready(), "SubReader should be ready after reading one character");
            reader.skip(5);
            assertFalse(reader.ready(), "SubReader should not be ready after reading all characters");
        }
    }
}