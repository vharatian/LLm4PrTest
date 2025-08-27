package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.Date;

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
    public void testGetMatchingMethodWithDifferentDeclaringClasses() throws NoSuchMethodException {
        // Test case to cover the change in getMatchingMethod where the declaring classes are different
        Method method = MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod4", Long.class, Long.class);
        assertEquals(GetMatchingMethodClass.class.getMethod("testMethod4", Long.class, Long.class), method);

        method = MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod4", Color.class, Color.class);
        assertEquals(GetMatchingMethodClass.class.getMethod("testMethod4", Color.class, Color.class), method);
    }

    @Test
    public void testGetMatchingMethodWithSameDeclaringClasses() throws NoSuchMethodException {
        // Test case to cover the change in getMatchingMethod where the declaring classes are the same
        Method method = MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod3", Long.TYPE, Long.class);
        assertEquals(GetMatchingMethodClass.class.getMethod("testMethod3", Long.TYPE, Long.class), method);

        method = MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod3", Long.class, Long.TYPE);
        assertEquals(GetMatchingMethodClass.class.getMethod("testMethod3", Long.class, Long.TYPE), method);
    }

    @Test
    public void testGetMatchingMethodWithMultipleCandidates() {
        // Test case to cover the scenario where multiple candidates exist and an exception is expected
        assertThrows(IllegalStateException.class, () -> MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod4", null, null));
    }

    private static final class GetMatchingMethodClass {
        public void testMethod() {
        }

        public void testMethod(final Long aLong) {
        }

        public void testMethod(final long aLong) {
        }

        public void testMethod2(final Long aLong) {
        }

        public void testMethod2(final Color aColor) {
        }

        public void testMethod2(final long aLong) {
        }

        public void testMethod3(final long aLong, final Long anotherLong) {
        }

        public void testMethod3(final Long aLong, final long anotherLong) {
        }

        public void testMethod3(final Long aLong, final Long anotherLong) {
        }

        public void testMethod4(final Long aLong, final Long anotherLong) {
        }

        public void testMethod4(final Color aColor1, final Color aColor2) {
        }
    }
}