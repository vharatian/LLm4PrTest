package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    private MethodUtilsTest.TestBean testBean;

    @BeforeEach
    public void setUp() {
        testBean = new MethodUtilsTest.TestBean();
    }

    @Test
    public void testGetMatchingMethodExactMatch() throws Exception {
        Method method = MethodUtils.getMatchingMethod(MethodUtilsTest.TestBean.class, "foo", String.class);
        assertEquals("foo", method.getName());
        assertEquals(1, method.getParameterCount());
        assertEquals(String.class, method.getParameterTypes()[0]);
    }

    @Test
    public void testGetMatchingMethodInexactMatch() throws Exception {
        Method method = MethodUtils.getMatchingMethod(MethodUtilsTest.TestBean.class, "foo", Integer.TYPE);
        assertEquals("foo", method.getName());
        assertEquals(1, method.getParameterCount());
        assertEquals(Integer.TYPE, method.getParameterTypes()[0]);
    }

    @Test
    public void testGetMatchingMethodMultipleCandidates() {
        assertThrows(IllegalStateException.class, () -> {
            MethodUtils.getMatchingMethod(MethodUtilsTest.TestBean.class, "varOverload", Object.class);
        });
    }

    @Test
    public void testGetMatchingMethodNoMatch() {
        Method method = MethodUtils.getMatchingMethod(MethodUtilsTest.TestBean.class, "nonExistentMethod", String.class);
        assertEquals(null, method);
    }

    @Test
    public void testDistance() throws Exception {
        Method distanceMethod = MethodUtils.getMatchingMethod(MethodUtils.class, "distance", Class[].class, Class[].class);
        distanceMethod.setAccessible(true);
        assertEquals(-1, distanceMethod.invoke(null, new Class[]{String.class}, new Class[]{Date.class}));
        assertEquals(0, distanceMethod.invoke(null, new Class[]{Date.class}, new Class[]{Date.class}));
        assertEquals(1, distanceMethod.invoke(null, new Class[]{Integer.class}, new Class[]{ClassUtils.wrapperToPrimitive(Integer.class)}));
        assertEquals(2, distanceMethod.invoke(null, new Class[]{Integer.class}, new Class[]{Object.class}));
        distanceMethod.setAccessible(false);
    }
}