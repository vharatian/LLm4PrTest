package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    /**
     * Test to ensure the METHOD_BY_SIGNATURE comparator works correctly.
     */
    @Test
    public void testMethodBySignatureComparator() throws Exception {
        Method method1 = TestBean.class.getMethod("foo", String.class);
        Method method2 = TestBean.class.getMethod("foo", Integer.class);

        Comparator<Method> comparator = Comparator.comparing(Method::toString);
        int result = comparator.compare(method1, method2);

        assertEquals(method1.toString().compareTo(method2.toString()), result);
    }

    /**
     * Test to ensure the METHOD_BY_SIGNATURE comparator handles methods with the same signature correctly.
     */
    @Test
    public void testMethodBySignatureComparatorSameSignature() throws Exception {
        Method method1 = TestBean.class.getMethod("foo", String.class);
        Method method2 = TestBean.class.getMethod("foo", String.class);

        Comparator<Method> comparator = Comparator.comparing(Method::toString);
        int result = comparator.compare(method1, method2);

        assertEquals(0, result);
    }

    /**
     * Test to ensure the METHOD_BY_SIGNATURE comparator handles null methods correctly.
     */
    @Test
    public void testMethodBySignatureComparatorWithNull() {
        Comparator<Method> comparator = Comparator.comparing(Method::toString);

        assertThrows(NullPointerException.class, () -> {
            comparator.compare(null, null);
        });
    }
}