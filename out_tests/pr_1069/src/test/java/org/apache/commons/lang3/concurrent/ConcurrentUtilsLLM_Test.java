package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;

public class ConcurrentUtilsLLM_Test {
    
    @Test
    public void testCheckedExceptionWithRuntimeException() {
        RuntimeException rex = new RuntimeException("Test");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ConcurrentUtils.checkedException(rex));
        assertEquals("Not a checked exception: java.lang.RuntimeException: Test", ex.getMessage());
    }

    @Test
    public void testCheckedExceptionWithError() {
        Error err = new AssertionError("Test");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ConcurrentUtils.checkedException(err));
        assertEquals("Not a checked exception: java.lang.AssertionError: Test", ex.getMessage());
    }

    @Test
    public void testCheckedExceptionWithCheckedException() {
        Exception ex = new Exception("Test");
        Throwable result = ConcurrentUtils.checkedException(ex);
        assertEquals(ex, result);
    }

    @Test
    public void testCheckedExceptionWithNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ConcurrentUtils.checkedException(null));
        assertEquals("Not a checked exception: null", ex.getMessage());
    }
}