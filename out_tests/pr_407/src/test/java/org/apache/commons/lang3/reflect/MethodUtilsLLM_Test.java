package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public void testGetMatchingAccessibleMethodWithNullLastParameter() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{String.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullParameter() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgs() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{Integer.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndPrimitive() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{int.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndObject() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{Object.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndString() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{String.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndDouble() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{double.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndLong() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{long.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndBoolean() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{boolean.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndByte() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{byte.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndShort() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{short.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndChar() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{char.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndFloat() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{float.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndInteger() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{Integer.class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndLongArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{long[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndStringArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{String[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndObjectArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{Object[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndDoubleArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{double[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndFloatArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{float[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndBooleanArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{boolean[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndByteArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{byte[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndShortArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{short[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndCharArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{char[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndIntegerArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{Integer[].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndLongArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{long[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndStringArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{String[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndObjectArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{Object[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndDoubleArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{double[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndFloatArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{float[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndBooleanArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{boolean[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndByteArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{byte[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndShortArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{short[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndCharArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{char[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }

    @Test
    public void testGetMatchingAccessibleMethodWithNullVarArgsAndIntegerArrayArray() throws Exception {
        Method method = MethodUtils.getMatchingAccessibleMethod(MethodUtilsTest.TestBean.class, "foo", new Class<?>[]{Integer[][].class, null});
        assertNotNull(method);
        assertEquals("foo", method.getName());
    }
}