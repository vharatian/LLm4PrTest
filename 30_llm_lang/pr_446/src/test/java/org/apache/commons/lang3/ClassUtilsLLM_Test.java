package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClassUtilsLLM_Test {

    @Test
    public void test_getAbbreviatedName_Class_LengthHint() {
        assertEquals("", ClassUtils.getAbbreviatedName((Class<?>) null, 1));
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 1));
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 5));
        assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 13));
        assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 15));
        assertEquals("java.lang.String", ClassUtils.getAbbreviatedName(String.class, 20));
        assertEquals("o.a.c.l.ClassUtils", ClassUtils.getAbbreviatedName(ClassUtils.class, 18));
    }

    @Test
    public void test_getAbbreviatedName_Class_NegativeLengthHint() {
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName(String.class, -10));
    }

    @Test
    public void test_getAbbreviatedName_Class_ZeroLengthHint() {
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName(String.class, 0));
    }

    @Test
    public void test_getAbbreviatedName_String_LengthHint() {
        assertEquals("", ClassUtils.getAbbreviatedName((String) null, 1));
        assertEquals("WithoutPackage", ClassUtils.getAbbreviatedName("WithoutPackage", 1));
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName("java.lang.String", 1));
        assertEquals("o.a.c.l.ClassUtils", ClassUtils.getAbbreviatedName("org.apache.commons.lang3.ClassUtils", 18));
    }

    @Test
    public void test_getAbbreviatedName_String_NegativeLengthHint() {
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("java.lang.String", -10));
    }

    @Test
    public void test_getAbbreviatedName_String_ZeroLengthHint() {
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("java.lang.String", 0));
    }
}