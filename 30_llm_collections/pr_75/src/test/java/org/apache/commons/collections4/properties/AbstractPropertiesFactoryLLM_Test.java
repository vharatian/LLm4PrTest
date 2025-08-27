package org.apache.commons.collections4.properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractPropertiesFactoryLLM_Test {

    private AbstractPropertiesFactory<Properties> factory;

    @BeforeEach
    public void setUp() {
        factory = new AbstractPropertiesFactory<Properties>() {
            @Override
            protected Properties createProperties() {
                return new Properties();
            }
        };
    }

    @AfterEach
    public void tearDown() {
        factory = null;
    }

    @Test
    public void testLoadFromClassLoader() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties properties = factory.load(classLoader, "test.properties");
        assertNotNull(properties);
        assertEquals("value", properties.getProperty("key"));
    }

    @Test
    public void testLoadFromFile() throws IOException {
        File file = new File("src/test/resources/test.properties");
        Properties properties = factory.load(file);
        assertNotNull(properties);
        assertEquals("value", properties.getProperty("key"));
    }

    @Test
    public void testLoadFromInputStream() throws IOException {
        try (InputStream inputStream = new FileInputStream("src/test/resources/test.properties")) {
            Properties properties = factory.load(inputStream);
            assertNotNull(properties);
            assertEquals("value", properties.getProperty("key"));
        }
    }

    @Test
    public void testLoadFromPath() throws IOException {
        Path path = Paths.get("src/test/resources/test.properties");
        Properties properties = factory.load(path);
        assertNotNull(properties);
        assertEquals("value", properties.getProperty("key"));
    }

    @Test
    public void testLoadFromReader() throws IOException {
        try (Reader reader = new FileReader("src/test/resources/test.properties")) {
            Properties properties = factory.load(reader);
            assertNotNull(properties);
            assertEquals("value", properties.getProperty("key"));
        }
    }

    @Test
    public void testLoadFromString() throws IOException {
        Properties properties = factory.load("src/test/resources/test.properties");
        assertNotNull(properties);
        assertEquals("value", properties.getProperty("key"));
    }

    @Test
    public void testLoadFromURI() throws IOException {
        URI uri = Paths.get("src/test/resources/test.properties").toUri();
        Properties properties = factory.load(uri);
        assertNotNull(properties);
        assertEquals("value", properties.getProperty("key"));
    }

    @Test
    public void testLoadFromURL() throws IOException {
        URL url = getClass().getClassLoader().getResource("test.properties");
        Properties properties = factory.load(url);
        assertNotNull(properties);
        assertEquals("value", properties.getProperty("key"));
    }
}