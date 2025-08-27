package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    @Test
    public void testInvokeMethod_NullObject() {
        assertThrows(NullPointerException.class, () -> MethodUtils.invokeMethod(null, "foo"));
    }

    @Test
    public void testInvokeMethod_NullMethodName() {
        assertThrows(NullPointerException.class, () -> MethodUtils.invokeMethod(new MethodUtilsTest.TestBean(), null));
    }

    @Test
    public void testInvokeMethodForceAccess_NullObject() {
        assertThrows(NullPointerException.class, () -> MethodUtils.invokeMethod(null, true, "foo"));
    }

    @Test
    public void testInvokeMethodForceAccess_NullMethodName() {
        assertThrows(NullPointerException.class, () -> MethodUtils.invokeMethod(new MethodUtilsTest.TestBean(), true, null));
    }

    @Test
    public void testInvokeExactMethod_NullObject() {
        assertThrows(NullPointerException.class, () -> MethodUtils.invokeExactMethod(null, "foo"));
    }

    @Test
    public void testInvokeExactMethod_NullMethodName() {
        assertThrows(NullPointerException.class, () -> MethodUtils.invokeExactMethod(new MethodUtilsTest.TestBean(), null));
    }

    @Test
    public void testGetMatchingAccessibleMethod_NullClass() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMatchingAccessibleMethod(null, "foo"));
    }

    @Test
    public void testGetMethodsWithAnnotation_NullClass() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetMethodsWithAnnotation_NullAnnotation() {
        assertThrows(NullPointerException.class, () -> MethodUtils.getMethodsWithAnnotation(MethodUtilsTest.class, null));
    }
}