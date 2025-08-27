package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class TaggedIOExceptionLLM_Test {

    @Test
    public void testThrowCauseIfTaggedWith() {
        final Serializable tag = UUID.randomUUID();
        final IOException exception = new IOException("Test exception");
        final TaggedIOException tagged = new TaggedIOException(exception, tag);

        // Test that throwCauseIfTaggedWith throws the cause if the tag matches
        assertThrows(IOException.class, () -> {
            TaggedIOException.throwCauseIfTaggedWith(tagged, tag);
        });

        // Test that throwCauseIfTaggedWith does not throw if the tag does not match
        assertThrows(IOException.class, () -> {
            TaggedIOException.throwCauseIfTaggedWith(tagged, UUID.randomUUID());
        });
    }

    @Test
    public void testIsTaggedWith() {
        final Serializable tag = UUID.randomUUID();
        final IOException exception = new IOException("Test exception");
        final TaggedIOException tagged = new TaggedIOException(exception, tag);

        // Test that isTaggedWith returns true for matching tag
        assertTrue(TaggedIOException.isTaggedWith(tagged, tag));

        // Test that isTaggedWith returns false for non-matching tag
        assertTrue(!TaggedIOException.isTaggedWith(tagged, UUID.randomUUID()));
    }
}