package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ObjectUtilsLLM_Test {

    @Test
    public void testGetFirstNonNullJavadocCorrection() {
        // This test ensures that the Javadoc correction does not affect functionality.
        assertEquals("1", ObjectUtils.getFirstNonNull(() -> null, () -> "1", () -> "2"));
        assertEquals("123", ObjectUtils.getFirstNonNull(() -> "123", () -> null, () -> "456"));
        assertEquals("123", ObjectUtils.getFirstNonNull(() -> null, () -> "123", () -> fail("Supplier after first non-null value should not be evaluated")));
        assertNull(ObjectUtils.getFirstNonNull(null, () -> null));
        assertNull(ObjectUtils.getFirstNonNull());
        assertNull(ObjectUtils.getFirstNonNull((Supplier<Object>) null));
        assertNull(ObjectUtils.getFirstNonNull((Supplier<Object>[]) null));
        assertEquals(1, ObjectUtils.getFirstNonNull(() -> null, () -> 1));
        assertEquals(Boolean.TRUE, ObjectUtils.getFirstNonNull(() -> null, () -> Boolean.TRUE));
    }
}