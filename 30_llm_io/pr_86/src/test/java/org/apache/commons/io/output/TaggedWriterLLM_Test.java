package org.apache.commons.io.output;

import org.apache.commons.io.TaggedIOException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class TaggedWriterLLM_Test {

    @Test
    public void testIsCauseOf() {
        StringWriter stringWriter = new StringWriter();
        TaggedWriter taggedWriter = new TaggedWriter(stringWriter);

        IOException ioException = new IOException("Test IOException");
        TaggedIOException taggedIOException = new TaggedIOException(ioException, taggedWriter.tag);

        assertTrue(taggedWriter.isCauseOf(taggedIOException));
        assertFalse(taggedWriter.isCauseOf(new IOException("Different IOException")));
    }

    @Test
    public void testThrowIfCauseOf() {
        StringWriter stringWriter = new StringWriter();
        TaggedWriter taggedWriter = new TaggedWriter(stringWriter);

        IOException ioException = new IOException("Test IOException");
        TaggedIOException taggedIOException = new TaggedIOException(ioException, taggedWriter.tag);

        assertThrows(IOException.class, () -> taggedWriter.throwIfCauseOf(taggedIOException));

        Exception differentException = new IOException("Different IOException");
        assertDoesNotThrow(() -> taggedWriter.throwIfCauseOf(differentException));
    }

    @Test
    public void testHandleIOException() {
        StringWriter stringWriter = new StringWriter();
        TaggedWriter taggedWriter = new TaggedWriter(stringWriter);

        IOException ioException = new IOException("Test IOException");

        TaggedIOException thrownException = assertThrows(TaggedIOException.class, () -> {
            taggedWriter.handleIOException(ioException);
        });

        assertEquals(ioException, thrownException.getCause());
        assertTrue(taggedWriter.isCauseOf(thrownException));
    }
}