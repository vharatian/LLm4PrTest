package org.apache.commons.io.input;

import org.apache.commons.io.TaggedIOException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class TaggedReaderLLM_Test {

    @Test
    public void testIsCauseOf() throws IOException {
        Reader reader = new StringReader("test");
        TaggedReader taggedReader = new TaggedReader(reader);

        IOException ioException = new IOException("Test Exception");
        TaggedIOException taggedIOException = new TaggedIOException(ioException, taggedReader.tag);

        assertTrue(taggedReader.isCauseOf(taggedIOException));
        assertFalse(taggedReader.isCauseOf(new IOException("Other Exception")));
    }

    @Test
    public void testThrowIfCauseOf() {
        Reader reader = new StringReader("test");
        TaggedReader taggedReader = new TaggedReader(reader);

        IOException ioException = new IOException("Test Exception");
        TaggedIOException taggedIOException = new TaggedIOException(ioException, taggedReader.tag);

        assertThrows(IOException.class, () -> taggedReader.throwIfCauseOf(taggedIOException));
        assertDoesNotThrow(() -> taggedReader.throwIfCauseOf(new IOException("Other Exception")));
    }

    @Test
    public void testHandleIOException() {
        Reader reader = new StringReader("test");
        TaggedReader taggedReader = new TaggedReader(reader);

        IOException ioException = new IOException("Test Exception");

        TaggedIOException thrown = assertThrows(TaggedIOException.class, () -> taggedReader.handleIOException(ioException));
        assertEquals(ioException, thrown.getCause());
        assertTrue(taggedReader.isCauseOf(thrown));
    }
}