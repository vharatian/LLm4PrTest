package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    public void test_getClass_ClassLoader_NullPointerException() {
        assertThrows(NullPointerException.class, () -> ClassUtils.getClass(ClassLoader.getSystemClassLoader(), null));
    }

    @Test
    public void test_getClass_ClassLoader_Boolean_NullPointerException() {
        assertThrows(NullPointerException.class, () -> ClassUtils.getClass(ClassLoader.getSystemClassLoader(), null, true));
    }

    @Test
    public void test_getClass_String_NullPointerException() {
        assertThrows(NullPointerException.class, () -> ClassUtils.getClass(null));
    }

    @Test
    public void test_getClass_String_Boolean_NullPointerException() {
        assertThrows(NullPointerException.class, () -> ClassUtils.getClass(null, true));
    }

    @Test
    public void test_toCanonicalName_NullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            String className = null;
            ClassUtils.getClass(className);
        });
    }
}