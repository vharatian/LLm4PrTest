package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    public void test_getShortCanonicalName_Class() {
        // Test with a regular class
        assertEquals("ClassUtils", ClassUtils.getShortCanonicalName(ClassUtils.class));
        // Test with an array class
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName(ClassUtils[].class));
        // Test with a primitive array class
        assertEquals("int[]", ClassUtils.getShortCanonicalName(int[].class));
        // Test with a nested class
        class Named {}
        assertEquals("ClassUtilsTest2.1Named", ClassUtils.getShortCanonicalName(Named.class));
        // Test with null input
        assertEquals("", ClassUtils.getShortCanonicalName((Class<?>) null));
    }

    @Test
    public void test_getShortCanonicalName_Object() {
        // Test with a regular object
        assertEquals("ClassUtils", ClassUtils.getShortCanonicalName(new ClassUtils(), "<null>"));
        // Test with an array object
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName(new ClassUtils[0], "<null>"));
        // Test with a primitive array object
        assertEquals("int[]", ClassUtils.getShortCanonicalName(new int[0], "<null>"));
        // Test with a nested object
        class Named {}
        assertEquals("ClassUtilsTest2.2Named", ClassUtils.getShortCanonicalName(new Named(), "<null>"));
        // Test with null input
        assertEquals("<null>", ClassUtils.getShortCanonicalName(null, "<null>"));
    }
}