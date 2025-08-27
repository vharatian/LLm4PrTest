package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IOIndexedExceptionLLM_Test {

    @Test
    public void testToMessageWithNullCause() {
        int index = 1;
        Throwable cause = null;
        String expectedMessage = "Null #1: Null";
        String actualMessage = IOIndexedException.toMessage(index, cause);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testToMessageWithNonNullCause() {
        int index = 2;
        Throwable cause = new IOException("Test Message");
        String expectedMessage = "IOException #2: Test Message";
        String actualMessage = IOIndexedException.toMessage(index, cause);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetIndex() {
        int index = 3;
        Throwable cause = new IOException();
        IOIndexedException exception = new IOIndexedException(index, cause);
        assertEquals(index, exception.getIndex());
    }
}