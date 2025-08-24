package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParseExceptionLLM_Test {

    @Test
    public void testParseExceptionMessage() {
        String message = "Test message";
        ParseException exception = new ParseException(message);
        assertEquals(message, exception.getMessage());
    }
}