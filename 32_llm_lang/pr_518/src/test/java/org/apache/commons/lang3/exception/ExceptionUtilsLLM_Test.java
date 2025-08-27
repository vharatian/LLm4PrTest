package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionUtilsLLM_Test {

    @Test
    @DisplayName("throwableOfThrowable returns the first matching throwable from the throwable chain")
    public void testThrowableOfThrowable() {
        Throwable cause = new IllegalArgumentException("Cause");
        Throwable nested = new ExceptionUtilsTest.NestableException(cause);
        Throwable withCause = new ExceptionUtilsTest.ExceptionWithCause(nested);

        assertNull(ExceptionUtils.throwableOfThrowable(null, IllegalArgumentException.class));
        assertNull(ExceptionUtils.throwableOfThrowable(withCause, NullPointerException.class));
        assertEquals(cause, ExceptionUtils.throwableOfThrowable(withCause, IllegalArgumentException.class));
        assertEquals(nested, ExceptionUtils.throwableOfThrowable(withCause, ExceptionUtilsTest.NestableException.class));
    }

    @Test
    @DisplayName("throwableOfThrowable with fromIndex returns the first matching throwable from the throwable chain")
    public void testThrowableOfThrowableFromIndex() {
        Throwable cause = new IllegalArgumentException("Cause");
        Throwable nested = new ExceptionUtilsTest.NestableException(cause);
        Throwable withCause = new ExceptionUtilsTest.ExceptionWithCause(nested);

        assertNull(ExceptionUtils.throwableOfThrowable(null, IllegalArgumentException.class, 0));
        assertNull(ExceptionUtils.throwableOfThrowable(withCause, NullPointerException.class, 0));
        assertEquals(cause, ExceptionUtils.throwableOfThrowable(withCause, IllegalArgumentException.class, 0));
        assertEquals(nested, ExceptionUtils.throwableOfThrowable(withCause, ExceptionUtilsTest.NestableException.class, 0));
        assertNull(ExceptionUtils.throwableOfThrowable(withCause, IllegalArgumentException.class, 2));
    }

    @Test
    @DisplayName("throwableOfType returns the first matching throwable from the throwable chain")
    public void testThrowableOfType() {
        Throwable cause = new IllegalArgumentException("Cause");
        Throwable nested = new ExceptionUtilsTest.NestableException(cause);
        Throwable withCause = new ExceptionUtilsTest.ExceptionWithCause(nested);

        assertNull(ExceptionUtils.throwableOfType(null, IllegalArgumentException.class));
        assertNull(ExceptionUtils.throwableOfType(withCause, NullPointerException.class));
        assertEquals(cause, ExceptionUtils.throwableOfType(withCause, IllegalArgumentException.class));
        assertEquals(nested, ExceptionUtils.throwableOfType(withCause, ExceptionUtilsTest.NestableException.class));
    }

    @Test
    @DisplayName("throwableOfType with fromIndex returns the first matching throwable from the throwable chain")
    public void testThrowableOfTypeFromIndex() {
        Throwable cause = new IllegalArgumentException("Cause");
        Throwable nested = new ExceptionUtilsTest.NestableException(cause);
        Throwable withCause = new ExceptionUtilsTest.ExceptionWithCause(nested);

        assertNull(ExceptionUtils.throwableOfType(null, IllegalArgumentException.class, 0));
        assertNull(ExceptionUtils.throwableOfType(withCause, NullPointerException.class, 0));
        assertEquals(cause, ExceptionUtils.throwableOfType(withCause, IllegalArgumentException.class, 0));
        assertEquals(nested, ExceptionUtils.throwableOfType(withCause, ExceptionUtilsTest.NestableException.class, 0));
        assertNull(ExceptionUtils.throwableOfType(withCause, IllegalArgumentException.class, 2));
    }
}