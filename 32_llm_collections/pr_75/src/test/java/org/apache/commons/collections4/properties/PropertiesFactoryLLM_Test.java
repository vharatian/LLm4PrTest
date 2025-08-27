package org.apache.commons.collections4.properties;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

public class PropertiesFactoryLLM_Test {

    @Test
    public void testSingletonInstance() {
        PropertiesFactory instance1 = PropertiesFactory.INSTANCE;
        PropertiesFactory instance2 = PropertiesFactory.INSTANCE;
        assertSame(instance1, instance2, "PropertiesFactory.INSTANCE should return the same instance");
    }

    @Test
    public void testCreateProperties() {
        PropertiesFactory factory = PropertiesFactory.INSTANCE;
        Properties properties = factory.createProperties();
        assertNotNull(properties, "createProperties should return a non-null Properties instance");
        assertTrue(properties.isEmpty(), "New Properties instance should be empty");
    }
}