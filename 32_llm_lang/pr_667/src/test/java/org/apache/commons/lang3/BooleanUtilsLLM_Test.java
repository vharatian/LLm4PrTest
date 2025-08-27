package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test {

    @Test
    public void test_toBoolean_String() {
        assertTrue(BooleanUtils.toBoolean("y"));
        assertFalse(BooleanUtils.toBoolean("n"));
        assertTrue(BooleanUtils.toBoolean("t"));
        assertFalse(BooleanUtils.toBoolean("f"));
    }

    @Test
    public void test_toBooleanObject_Integer() {
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(0)));
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(1)));
        assertNull(BooleanUtils.toBooleanObject((Integer) null));
    }
}