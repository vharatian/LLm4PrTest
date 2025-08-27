package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test {

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, true));
        assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false));
        assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true));
        assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, false));
        assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
    }

    @Test
    public void test_toBoolean_int_int_int_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertTrue(BooleanUtils.toBoolean(1, 1, 1));
        assertFalse(BooleanUtils.toBoolean(2, 1, 2));
    }

    @Test
    public void test_toBooleanObject_int_int_int_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(0, 0, 0, 3));
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(0, 0, 0, 0));
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(2, 1, 2, 2));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        final Integer zero = Integer.valueOf(0);
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer three = Integer.valueOf(3);

        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(zero, zero, zero, three));
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(zero, zero, zero, zero));
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(two, one, two, two));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertSame(Boolean.TRUE, BooleanUtils.toBooleanObject(null, null, null, null));
        assertSame(Boolean.FALSE, BooleanUtils.toBooleanObject(null, "true", null, null));
        assertSame(Boolean.FALSE, BooleanUtils.toBooleanObject(null, "true", null, "false"));
    }

    @Test
    public void test_and_boolean_array_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertTrue(BooleanUtils.and(true, true));
        assertFalse(BooleanUtils.and(true, false));
        assertFalse(BooleanUtils.and(false, true));
        assertFalse(BooleanUtils.and(false, false));
    }

    @Test
    public void test_and_Boolean_array_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertEquals(Boolean.TRUE, BooleanUtils.and(Boolean.TRUE, Boolean.TRUE));
        assertEquals(Boolean.FALSE, BooleanUtils.and(Boolean.TRUE, Boolean.FALSE));
        assertEquals(Boolean.FALSE, BooleanUtils.and(Boolean.FALSE, Boolean.TRUE));
        assertEquals(Boolean.FALSE, BooleanUtils.and(Boolean.FALSE, Boolean.FALSE));
    }

    @Test
    public void test_or_boolean_array_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertTrue(BooleanUtils.or(true, true));
        assertTrue(BooleanUtils.or(true, false));
        assertTrue(BooleanUtils.or(false, true));
        assertFalse(BooleanUtils.or(false, false));
    }

    @Test
    public void test_or_Boolean_array_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertEquals(Boolean.TRUE, BooleanUtils.or(Boolean.TRUE, Boolean.TRUE));
        assertEquals(Boolean.TRUE, BooleanUtils.or(Boolean.TRUE, Boolean.FALSE));
        assertEquals(Boolean.TRUE, BooleanUtils.or(Boolean.FALSE, Boolean.TRUE));
        assertEquals(Boolean.FALSE, BooleanUtils.or(Boolean.FALSE, Boolean.FALSE));
    }

    @Test
    public void test_xor_boolean_array_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertTrue(BooleanUtils.xor(true, false, false));
    }

    @Test
    public void test_xor_Boolean_array_additionalCases() {
        // Additional cases to cover the new examples in the javadoc
        assertEquals(Boolean.TRUE, BooleanUtils.xor(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
    }
}