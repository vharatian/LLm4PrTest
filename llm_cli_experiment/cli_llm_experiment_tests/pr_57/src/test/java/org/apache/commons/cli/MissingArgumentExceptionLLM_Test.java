package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MissingArgumentExceptionLLM_Test {

    @Test
    public void testMissingArgumentExceptionWithMessage() {
        String message = "Test message";
        MissingArgumentException exception = new MissingArgumentException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testMissingArgumentExceptionWithOption() {
        Option option = new Option("a", "optionA", true, "Option A");
        MissingArgumentException exception = new MissingArgumentException(option);
        assertEquals("Missing argument for option: a", exception.getMessage());
        assertEquals(option, exception.getOption());
    }
}