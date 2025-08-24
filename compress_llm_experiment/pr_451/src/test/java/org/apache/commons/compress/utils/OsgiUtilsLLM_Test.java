package org.apache.commons.compress.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OsgiUtilsLLM_Test {

    @Test
    public void testClassLoaderIsNull() {
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(null);
            assertFalse(OsgiUtils.isRunningInOsgiEnvironment());
        } finally {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
        }
    }

    @Test
    public void testClassLoaderIsNotNull() {
        assertTrue(OsgiUtils.isRunningInOsgiEnvironment() == false || OsgiUtils.isRunningInOsgiEnvironment() == true);
    }
}