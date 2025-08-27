package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtilsLLM_Test {

    private MethodUtilsTest.TestBean testBean;

    @BeforeEach
    public void setUp() {
        testBean = new MethodUtilsTest.TestBean();
    }

    @Test
    public void testInvokeMethodForceAccessNoArgs() throws Exception {
        assertEquals("privateStringStuff()", MethodUtils.invokeMethod(testBean, true, "privateStringStuff"));
    }

    @Test
    public void testInvokeMethodForceAccessWithArgs() throws Exception {
        assertEquals("privateStringStuff(Integer)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", 5));
        assertEquals("privateStringStuff(double)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", 5.0d));
        assertEquals("privateStringStuff(String)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", "Hi There"));
        assertEquals("privateStringStuff(Object)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", new java.util.Date()));
    }

    @Test
    public void testInvokeMethodForceAccessWithNullArgs() throws Exception {
        assertEquals("privateStringStuff(Object)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", (Object) null));
    }

    @Test
    public void testInvokeMethodForceAccessNoSuchMethod() {
        assertThrows(NoSuchMethodException.class, () -> MethodUtils.invokeMethod(testBean, true, "nonExistentMethod"));
    }

    @Test
    public void testInvokeMethodForceAccessIllegalAccess() {
        assertThrows(IllegalAccessException.class, () -> MethodUtils.invokeMethod(testBean, false, "privateStringStuff"));
    }

    @Test
    public void testInvokeMethodForceAccessInvocationTargetException() {
        assertThrows(InvocationTargetException.class, () -> MethodUtils.invokeMethod(testBean, true, "privateStringStuff", new Object[] {new Object() {
            @Override
            public String toString() {
                throw new RuntimeException("test exception");
            }
        }}));
    }
}