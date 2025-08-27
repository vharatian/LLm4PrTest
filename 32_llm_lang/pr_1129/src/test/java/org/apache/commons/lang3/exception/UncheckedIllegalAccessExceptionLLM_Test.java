package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class UncheckedIllegalAccessExceptionLLM_Test extends AbstractLangTest {
    @Test
    public void testConstructWithNullCause() {
        assertSame(null, new UncheckedIllegalAccessException(null).getCause());
    }
}