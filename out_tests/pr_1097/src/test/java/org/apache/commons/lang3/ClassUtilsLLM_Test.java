package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    /**
     * Test for the change in the Javadoc of the getShortClassName method.
     * Ensures that the method getShortClassName(String) is correctly referenced.
     */
    @Test
    public void test_getShortClassName_JavadocReference() {
        // This test ensures that the Javadoc reference is correct and the method works as expected.
        assertEquals("ClassUtils", ClassUtils.getShortClassName("org.apache.commons.lang3.ClassUtils"));
        assertEquals("Map.Entry", ClassUtils.getShortClassName("java.util.Map.Entry"));
    }
}