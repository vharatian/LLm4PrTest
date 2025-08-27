package org.apache.commons.io.input;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractCharacterFilterReaderLLM_Test {

    private static class TestCharacterFilterReader extends AbstractCharacterFilterReader {
        protected TestCharacterFilterReader(Reader reader) {
            super(reader);
        }

        @Override
        protected boolean filter(int ch) {
            // Example filter: filter out vowels
            return "AEIOUaeiou".indexOf(ch) != -1;
        }
    }

    @Test
    public void testReadSingleCharacter() throws IOException {
        String input = "hello";
        try (TestCharacterFilterReader reader = new TestCharacterFilterReader(new StringReader(input))) {
            assertEquals('h', reader.read());
            assertEquals('l', reader.read());
            assertEquals('l', reader.read());
            assertEquals(-1, reader.read());
        }
    }

    @Test
    public void testReadCharArray() throws IOException {
        String input = "hello world";
        char[] buffer = new char[20];
        try (TestCharacterFilterReader reader = new TestCharacterFilterReader(new StringReader(input))) {
            int read = reader.read(buffer, 0, buffer.length);
            assertEquals(8, read);
            assertEquals('h', buffer[0]);
            assertEquals('l', buffer[1]);
            assertEquals('l', buffer[2]);
            assertEquals(' ', buffer[3]);
            assertEquals('w', buffer[4]);
            assertEquals('r', buffer[5]);
            assertEquals('l', buffer[6]);
            assertEquals('d', buffer[7]);
        }
    }

    @Test
    public void testReadCharArrayWithOffset() throws IOException {
        String input = "hello world";
        char[] buffer = new char[20];
        try (TestCharacterFilterReader reader = new TestCharacterFilterReader(new StringReader(input))) {
            int read = reader.read(buffer, 5, 10);
            assertEquals(8, read);
            assertEquals('h', buffer[5]);
            assertEquals('l', buffer[6]);
            assertEquals('l', buffer[7]);
            assertEquals(' ', buffer[8]);
            assertEquals('w', buffer[9]);
            assertEquals('r', buffer[10]);
            assertEquals('l', buffer[11]);
            assertEquals('d', buffer[12]);
        }
    }
}