package org.apache.commons.text.lookup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IllegalArgumentExceptionsLLM_Test {

    @Test
    void testFormatWithMessage() {
        String format = "This is a %s test";
        String arg = "simple";
        IllegalArgumentException exception = IllegalArgumentExceptions.format(format, arg);
        assertEquals("This is a simple test", exception.getMessage());
    }

    @Test
    void testFormatWithThrowableAndMessage() {
        String format = "This is a %s test";
        String arg = "complex";
        Throwable cause = new RuntimeException("Cause");
        IllegalArgumentException exception = IllegalArgumentExceptions.format(cause, format, arg);
        assertEquals("This is a complex test", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}