package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test {

    @Test
    public void test_toBoolean_y() {
        assertTrue(BooleanUtils.toBoolean("y"));
        assertTrue(BooleanUtils.toBoolean("Y"));
    }

    @Test
    public void test_toBoolean_n() {
        assertFalse(BooleanUtils.toBoolean("n"));
        assertFalse(BooleanUtils.toBoolean("N"));
    }

    @Test
    public void test_toBoolean_t() {
        assertTrue(BooleanUtils.toBoolean("t"));
        assertTrue(BooleanUtils.toBoolean("T"));
    }

    @Test
    public void test_toBoolean_f() {
        assertFalse(BooleanUtils.toBoolean("f"));
        assertFalse(BooleanUtils.toBoolean("F"));
    }
}