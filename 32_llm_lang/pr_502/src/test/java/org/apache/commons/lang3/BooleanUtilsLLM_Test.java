package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test {

    @Test
    public void test_toBooleanObject_String_with_1() {
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("1"));
    }

    @Test
    public void test_toBooleanObject_String_with_0() {
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("0"));
    }

    @Test
    public void test_toBooleanObject_String_with_invalid() {
        assertNull(BooleanUtils.toBooleanObject("2"));
    }
}