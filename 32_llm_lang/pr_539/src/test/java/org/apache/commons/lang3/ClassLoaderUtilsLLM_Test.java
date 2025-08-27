package org.apache.commons.lang3;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassLoaderUtilsLLM_Test {

    @Test
    public void testToString_ClassLoader_NonURLClassLoader() {
        // Create a custom class loader that is not an instance of URLClassLoader
        ClassLoader customClassLoader = new ClassLoader() {};
        // Verify that the toString method returns the default toString representation
        Assertions.assertEquals(customClassLoader.toString(), ClassLoaderUtils.toString(customClassLoader));
    }
}