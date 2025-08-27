package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.MethodUtilsTest.GetMatchingMethodClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    private MethodUtilsTest.TestBean testBean;

    @BeforeEach
    public void setUp() {
        testBean = new MethodUtilsTest.TestBean();
    }

    @Test
    public void testGetAllSuperclassesAndInterfaces() throws Exception {
        // Test case to ensure the change in getAllSuperclassesAndInterfaces method works correctly
        final Method method = MethodUtils.class.getDeclaredMethod("getAllSuperclassesAndInterfaces", Class.class);
        method.setAccessible(true);

        List<Class<?>> result = (List<Class<?>>) method.invoke(null, GetMatchingMethodClass.class);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Object.class, result.get(0));
        assertEquals(GetMatchingMethodClass.class.getSuperclass(), result.get(1));
    }

    @Test
    public void testGetMatchingMethodWithNullParameter() throws Exception {
        // Test case to ensure getMatchingMethod works correctly with null parameters
        assertEquals(MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod", (Class<?>) null),
                GetMatchingMethodClass.class.getMethod("testMethod", Long.class));
        assertThrows(IllegalStateException.class,
                () -> MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod2", (Class<?>) null));
    }

    @Test
    public void testDistanceMethod() throws Exception {
        // Test case to ensure distance method works correctly
        final Method distanceMethod = MethodUtils.getMatchingMethod(MethodUtils.class, "distance", Class[].class, Class[].class);
        distanceMethod.setAccessible(true);
        assertEquals(-1, distanceMethod.invoke(null, new Class[]{String.class}, new Class[]{Object.class}));
        assertEquals(0, distanceMethod.invoke(null, new Class[]{Object.class}, new Class[]{Object.class}));
        assertEquals(1, distanceMethod.invoke(null, new Class[]{Integer.class}, new Class[]{ClassUtils.wrapperToPrimitive(Integer.class)}));
        assertEquals(2, distanceMethod.invoke(null, new Class[]{Integer.class}, new Class[]{Object.class}));
    }
}