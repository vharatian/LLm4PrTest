package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class UncheckedReflectiveOperationExceptionLLM_Test extends AbstractLangTest {
    @Test
    public void testConstructWithNullCause() {
        assertSame(null, new UncheckedReflectiveOperationException(null).getCause());
    }
}