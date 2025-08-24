package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ObjectUtilsLLM_Test {

    @Test
    public void testAllNull() {
        assertTrue(ObjectUtils.allNull((Object) null));
        assertTrue(ObjectUtils.allNull((Object[]) null));
        assertTrue(ObjectUtils.allNull(null, null, null));
        assertFalse(ObjectUtils.allNull(null, "foo", null));
        assertFalse(ObjectUtils.allNull("foo", "bar", null));
        assertFalse(ObjectUtils.allNull("foo", "bar", "baz"));
    }

    @Test
    public void testAnyNull() {
        assertTrue(ObjectUtils.anyNull((Object) null));
        assertTrue(ObjectUtils.anyNull((Object[]) null));
        assertTrue(ObjectUtils.anyNull(null, null, null));
        assertTrue(ObjectUtils.anyNull(null, "foo", null));
        assertTrue(ObjectUtils.anyNull("foo", "bar", null));
        assertFalse(ObjectUtils.anyNull("foo", "bar", "baz"));
    }
}