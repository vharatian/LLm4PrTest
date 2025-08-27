package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    public void test_getClass_withFinalClassVariable() throws ClassNotFoundException {
        // Test for the change in getClass method where clazz is declared as final
        assertEquals(int.class, ClassUtils.getClass("int"));
        assertEquals(String.class, ClassUtils.getClass("java.lang.String"));
        assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass("non.existent.Class"));
    }

    @Test
    public void test_getPublicMethod_withFinalMethodVariable() throws Exception {
        // Test for the change in getPublicMethod where candidateMethod is declared as final
        final Method toStringMethod = ClassUtils.getPublicMethod(Object.class, "toString");
        assertEquals(Object.class.getMethod("toString"), toStringMethod);
        
        final Method hashCodeMethod = ClassUtils.getPublicMethod(Object.class, "hashCode");
        assertEquals(Object.class.getMethod("hashCode"), hashCodeMethod);
        
        assertThrows(NoSuchMethodException.class, () -> ClassUtils.getPublicMethod(Object.class, "nonExistentMethod"));
    }
}