package org.apache.commons.lang3.text.translate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.CharArrayWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

@Deprecated
public class UnicodeUnpairedSurrogateRemoverLLM_Test {
    final UnicodeUnpairedSurrogateRemover subject = new UnicodeUnpairedSurrogateRemover();
    final CharArrayWriter writer = new CharArrayWriter();

    @Test
    public void testValidCharacters() throws IOException {
        assertFalse(subject.translate(0xd7ff, writer));
        assertFalse(subject.translate(0xe000, writer));
        assertEquals(0, writer.size());
    }

    @Test
    public void testInvalidCharacters() throws IOException {
        assertTrue(subject.translate(0xd800, writer));
        assertTrue(subject.translate(0xdfff, writer));
        assertEquals(0, writer.size());
    }

    // Additional test to ensure the refactored return statement works as expected
    @Test
    public void testBoundaryCharacters() throws IOException {
        assertTrue(subject.translate(Character.MIN_SURROGATE, writer));
        assertTrue(subject.translate(Character.MAX_SURROGATE, writer));
        assertEquals(0, writer.size());
    }
}