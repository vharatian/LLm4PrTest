package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    private MethodUtilsTest.TestBean testBean;

    @BeforeEach
    public void setUp() {
        testBean = new MethodUtilsTest.TestBean();
    }

    @Test
    public void testGetMatchingAccessibleMethod_SortedBySignature() {
        Method[] methods = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", String.class).getDeclaringClass().getMethods();
        List<Method> methodList = new ArrayList<>();
        for (Method method : methods) {
            if (method.getName().equals("foo")) {
                methodList.add(method);
            }
        }
        List<Method> sortedMethodList = new ArrayList<>(methodList);
        sortedMethodList.sort((m1, m2) -> m1.toString().compareTo(m2.toString()));
        assertEquals(sortedMethodList, methodList);
    }

    @Test
    public void testGetMatchingAccessibleMethod_NoSuchMethodException() {
        assertThrows(NoSuchMethodException.class, () -> MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "nonExistentMethod", String.class));
    }

    @Test
    public void testInvokeMethod_SortedBySignature() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", String.class);
        assertNotNull(method);
        assertEquals("foo(String)", method.invoke(testBean, "test"));
    }

    @Test
    public void testInvokeStaticMethod_SortedBySignature() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "bar", String.class);
        assertNotNull(method);
        assertEquals("bar(String)", method.invoke(null, "test"));
    }

    @Test
    public void testInvokeExactMethod_SortedBySignature() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class[]{String.class});
        assertNotNull(method);
        assertEquals("foo(String)", method.invoke(testBean, "test"));
    }

    @Test
    public void testInvokeExactStaticMethod_SortedBySignature() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "bar", new Class[]{String.class});
        assertNotNull(method);
        assertEquals("bar(String)", method.invoke(null, "test"));
    }
}