package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    private MethodUtilsTest.TestBean testBean;

    @BeforeEach
    public void setUp() {
        testBean = new MethodUtilsTest.TestBean();
    }

    /**
     * Test for the change in MethodUtils.invokeMethod to ensure no regression.
     */
    @Test
    public void testInvokeMethodForceAccess() throws Exception {
        // Test invoking a private method with forceAccess = true
        assertEquals("privateStringStuff()", MethodUtils.invokeMethod(testBean, true, "privateStringStuff"));
        assertEquals("privateStringStuff(Integer)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", 5));
        assertEquals("privateStringStuff(double)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", 5.0d));
        assertEquals("privateStringStuff(String)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", "Hi There"));
        assertEquals("privateStringStuff(Object)", MethodUtils.invokeMethod(testBean, true, "privateStringStuff", new java.util.Date()));

        // Test invoking a public method with forceAccess = true
        assertEquals("foo()", MethodUtils.invokeMethod(testBean, true, "foo"));
        assertEquals("foo(String)", MethodUtils.invokeMethod(testBean, true, "foo", ""));
        assertEquals("foo(Object)", MethodUtils.invokeMethod(testBean, true, "foo", new Object()));
        assertEquals("foo(Integer)", MethodUtils.invokeMethod(testBean, true, "foo", NumberUtils.INTEGER_ONE));
        assertEquals("foo(int)", MethodUtils.invokeMethod(testBean, true, "foo", NumberUtils.BYTE_ONE));
        assertEquals("foo(long)", MethodUtils.invokeMethod(testBean, true, "foo", NumberUtils.LONG_ONE));
        assertEquals("foo(double)", MethodUtils.invokeMethod(testBean, true, "foo", NumberUtils.DOUBLE_ONE));
        assertEquals("foo(String...)", MethodUtils.invokeMethod(testBean, true, "foo", "a", "b", "c"));
        assertEquals("foo(int, String...)", MethodUtils.invokeMethod(testBean, true, "foo", 5, "a", "b", "c"));
        assertEquals("foo(long...)", MethodUtils.invokeMethod(testBean, true, "foo", 1L, 2L));
    }

    /**
     * Test for the change in MethodUtils.invokeMethod to ensure no regression when method is not found.
     */
    @Test
    public void testInvokeMethodForceAccessNoSuchMethod() {
        assertThrows(NoSuchMethodException.class, () -> MethodUtils.invokeMethod(testBean, true, "nonExistentMethod"));
    }
}