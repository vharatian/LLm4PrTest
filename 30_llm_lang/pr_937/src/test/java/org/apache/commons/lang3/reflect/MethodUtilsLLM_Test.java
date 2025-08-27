package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    private MethodUtilsTest.TestBean testBean;

    @BeforeEach
    public void setUp() {
        testBean = new MethodUtilsTest.TestBean();
    }

    @Test
    public void testInvokeExactMethodWithUpdatedClass() throws Exception {
        // Test for the change from Class<? extends Object> to Class<?> in invokeExactMethod
        assertEquals("foo()", MethodUtils.invokeExactMethod(testBean, "foo", (Object[]) ArrayUtils.EMPTY_CLASS_ARRAY));
        assertEquals("foo()", MethodUtils.invokeExactMethod(testBean, "foo"));
        assertEquals("foo()", MethodUtils.invokeExactMethod(testBean, "foo", (Object[]) null));
        assertEquals("foo()", MethodUtils.invokeExactMethod(testBean, "foo", null, null));
        assertEquals("foo(String)", MethodUtils.invokeExactMethod(testBean, "foo", ""));
        assertEquals("foo(Object)", MethodUtils.invokeExactMethod(testBean, "foo", new Object()));
        assertEquals("foo(Integer)", MethodUtils.invokeExactMethod(testBean, "foo", NumberUtils.INTEGER_ONE));
        assertEquals("foo(double)", MethodUtils.invokeExactMethod(testBean, "foo", new Object[]{NumberUtils.DOUBLE_ONE}, new Class[]{Double.TYPE}));
        assertThrows(NoSuchMethodException.class, () -> MethodUtils.invokeExactMethod(testBean, "foo", NumberUtils.BYTE_ONE));
        assertThrows(NoSuchMethodException.class, () -> MethodUtils.invokeExactMethod(testBean, "foo", NumberUtils.LONG_ONE));
        assertThrows(NoSuchMethodException.class, () -> MethodUtils.invokeExactMethod(testBean, "foo", Boolean.TRUE));
    }

    @Test
    public void testDistanceWithUpdatedClass() throws Exception {
        // Test for the change from Class<? extends Object> to Class<?> in distance method
        final Method distanceMethod = MethodUtils.getMatchingMethod(MethodUtils.class, "distance", Class[].class, Class[].class);
        distanceMethod.setAccessible(true);
        assertEquals(-1, distanceMethod.invoke(null, new Class[]{String.class}, new Class[]{Date.class}));
        assertEquals(0, distanceMethod.invoke(null, new Class[]{Date.class}, new Class[]{Date.class}));
        assertEquals(1, distanceMethod.invoke(null, new Class[]{Integer.class}, new Class[]{ClassUtils.wrapperToPrimitive(Integer.class)}));
        assertEquals(2, distanceMethod.invoke(null, new Class[]{Integer.class}, new Class[]{Object.class}));
        distanceMethod.setAccessible(false);
    }
}