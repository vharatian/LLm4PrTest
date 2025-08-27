package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class UncheckedInterruptedExceptionLLM_Test extends AbstractLangTest {
    @Test
    public void testConstructWithNullCause() {
        // Test the constructor with a null cause
        assertSame(null, new UncheckedInterruptedException(null).getCause());
    }
}