package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    public void test_isAssignable_CharacterToWiderTypes() {
        // Test widening conversion from char to int, long, float, double
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Integer.TYPE));
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Long.TYPE));
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Float.TYPE));
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Double.TYPE));
    }

    @Test
    public void test_isAssignable_ShortToWiderTypes() {
        // Test widening conversion from short to int, long, float, double
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Integer.TYPE));
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Long.TYPE));
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Float.TYPE));
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Double.TYPE));
    }

    @Test
    public void test_isAssignable_CharacterToNonWiderTypes() {
        // Test non-widening conversion from char to other types
        assertFalse(ClassUtils.isAssignable(Character.TYPE, Byte.TYPE));
        assertFalse(ClassUtils.isAssignable(Character.TYPE, Short.TYPE));
        assertFalse(ClassUtils.isAssignable(Character.TYPE, Boolean.TYPE));
    }

    @Test
    public void test_isAssignable_ShortToNonWiderTypes() {
        // Test non-widening conversion from short to other types
        assertFalse(ClassUtils.isAssignable(Short.TYPE, Byte.TYPE));
        assertFalse(ClassUtils.isAssignable(Short.TYPE, Character.TYPE));
        assertFalse(ClassUtils.isAssignable(Short.TYPE, Boolean.TYPE));
    }
}