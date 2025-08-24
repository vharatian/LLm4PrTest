package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContextedExceptionLLM_Test extends AbstractExceptionContextTest<ContextedException> {

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        exceptionContext = new ContextedException(new Exception(TEST_MESSAGE));
        super.setUp();
    }

    /**
     * Test to ensure that the ContextedException can handle multiple context values
     * being added in a chain.
     */
    @Test
    public void testAddMultipleContextValues() {
        exceptionContext = new ContextedException(TEST_MESSAGE)
            .addContextValue("Account Number", "123456")
            .addContextValue("Amount Posted", 100.0)
            .addContextValue("Previous Balance", 50.0);

        assertEquals(3, exceptionContext.getContextEntries().size());
        assertEquals("123456", exceptionContext.getFirstContextValue("Account Number"));
        assertEquals(100.0, exceptionContext.getFirstContextValue("Amount Posted"));
        assertEquals(50.0, exceptionContext.getFirstContextValue("Previous Balance"));
    }
}