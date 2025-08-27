package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassLoaderUtilsLLM_Test {

    @Test
    public void testToStringWithURLClassLoader() throws Exception {
        URL[] urls = {new URL("http://example.com")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        String expected = urlClassLoader.toString() + "[http://example.com]";
        String actual = ClassLoaderUtils.toString(urlClassLoader);
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWithClassLoader() {
        ClassLoader classLoader = new ClassLoader() {};
        String expected = classLoader.toString();
        String actual = ClassLoaderUtils.toString(classLoader);
        assertEquals(expected, actual);
    }
}