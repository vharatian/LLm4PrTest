package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnrecognizedOptionExceptionLLM_Test {

    @Test
    public void testConstructorWithMessage() {
        String message = "Unrecognized option";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getOption());
    }

    @Test
    public void testConstructorWithMessageAndOption() {
        String message = "Unrecognized option";
        String option = "--unknown";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);
        assertEquals(message, exception.getMessage());
        assertEquals(option, exception.getOption());
    }

    @Test
    public void testGetOption() {
        String option = "--unknown";
        UnrecognizedOptionException exception = new UnrecognizedOptionException("Unrecognized option", option);
        assertEquals(option, exception.getOption());
    }
}