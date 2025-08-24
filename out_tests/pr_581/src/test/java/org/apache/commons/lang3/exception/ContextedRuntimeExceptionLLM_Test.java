package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContextedRuntimeExceptionLLM_Test extends AbstractExceptionContextTest<ContextedRuntimeException> {

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        exceptionContext = new ContextedRuntimeException(new Exception(TEST_MESSAGE));
        super.setUp();
    }

    @Test
    public void testContextedException() {
        exceptionContext = new ContextedRuntimeException();
        final String message = exceptionContext.getMessage();
        final String trace = ExceptionUtils.getStackTrace(exceptionContext);
        assertTrue(trace.contains("ContextedException"));
        assertTrue(StringUtils.isEmpty(message));
    }

    @Test
    public void testContextedExceptionString() {
        exceptionContext = new ContextedRuntimeException(TEST_MESSAGE);
        assertEquals(TEST_MESSAGE, exceptionContext.getMessage());
        final String trace = ExceptionUtils.getStackTrace(exceptionContext);
        assertTrue(trace.contains(TEST_MESSAGE));
    }

    @Test
    public void testContextedExceptionThrowable() {
        exceptionContext = new ContextedRuntimeException(new Exception(TEST_MESSAGE));
        final String message = exceptionContext.getMessage();
        final String trace = ExceptionUtils.getStackTrace(exceptionContext);
        assertTrue(trace.contains("ContextedException"));
        assertTrue(trace.contains(TEST_MESSAGE));
        assertTrue(message.contains(TEST_MESSAGE));
    }

    @Test
    public void testContextedExceptionStringThrowable() {
        exceptionContext = new ContextedRuntimeException(TEST_MESSAGE_2, new Exception(TEST_MESSAGE));
        final String message = exceptionContext.getMessage();
        final String trace = ExceptionUtils.getStackTrace(exceptionContext);
        assertTrue(trace.contains("ContextedException"));
        assertTrue(trace.contains(TEST_MESSAGE));
        assertTrue(trace.contains(TEST_MESSAGE_2));
        assertTrue(message.contains(TEST_MESSAGE_2));
    }

    @Test
    public void testContextedExceptionStringThrowableContext() {
        exceptionContext = new ContextedRuntimeException(TEST_MESSAGE_2, new Exception(TEST_MESSAGE),
                new DefaultExceptionContext() {
                    private static final long serialVersionUID = 1L;
                });
        final String message = exceptionContext.getMessage();
        final String trace = ExceptionUtils.getStackTrace(exceptionContext);
        assertTrue(trace.contains("ContextedException"));
        assertTrue(trace.contains(TEST_MESSAGE));
        assertTrue(trace.contains(TEST_MESSAGE_2));
        assertTrue(message.contains(TEST_MESSAGE_2));
    }

    @Test
    public void testNullExceptionPassing() {
        exceptionContext = new ContextedRuntimeException(TEST_MESSAGE_2, new Exception(TEST_MESSAGE), null)
                .addContextValue("test1", null)
                .addContextValue("test2", "some value")
                .addContextValue("test Date", new Date())
                .addContextValue("test Nbr", Integer.valueOf(5))
                .addContextValue("test Poorly written obj", new ObjectWithFaultyToString());
        final String message = exceptionContext.getMessage();
        assertNotNull(message);
    }

    @Test
    public void testRawMessage() {
        assertEquals(Exception.class.getName() + ": " + TEST_MESSAGE, exceptionContext.getRawMessage());
        exceptionContext = new ContextedRuntimeException(TEST_MESSAGE_2, new Exception(TEST_MESSAGE), new DefaultExceptionContext());
        assertEquals(TEST_MESSAGE_2, exceptionContext.getRawMessage());
        exceptionContext = new ContextedRuntimeException(null, new Exception(TEST_MESSAGE), new DefaultExceptionContext());
        assertNull(exceptionContext.getRawMessage());
    }
}