package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UncheckedAppendableLLM_Test {

    private IOException exception;
    private UncheckedAppendable appendableBroken;
    private UncheckedAppendable appendableString;

    @SuppressWarnings("resource")
    @BeforeEach
    public void setUp() {
        exception = new IOException("test exception");
        appendableBroken = UncheckedAppendable.on(new BrokenWriter(exception));
        appendableString = UncheckedAppendable.on(new StringWriter());
    }

    @Test
    public void testOnMethodWithValidAppendable() {
        Appendable appendable = new StringWriter();
        UncheckedAppendable uncheckedAppendable = UncheckedAppendable.on(appendable);
        uncheckedAppendable.append('a');
        assertEquals("a", appendable.toString());
    }

    @Test
    public void testOnMethodWithBrokenAppendable() {
        Appendable appendable = new BrokenWriter(exception);
        UncheckedAppendable uncheckedAppendable = UncheckedAppendable.on(appendable);
        try {
            uncheckedAppendable.append('a');
            fail("Expected exception not thrown.");
        } catch (UncheckedIOException e) {
            assertEquals(exception, e.getCause());
        }
    }
}