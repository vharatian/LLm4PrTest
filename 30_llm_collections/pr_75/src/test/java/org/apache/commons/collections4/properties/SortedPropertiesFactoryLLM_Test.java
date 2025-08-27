package org.apache.commons.collections4.properties;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortedPropertiesFactoryLLM_Test {

    @Test
    public void testSingletonInstance() {
        // Test that the singleton instance is not null
        assertNotNull(SortedPropertiesFactory.INSTANCE);
    }

    @Test
    public void testCreateProperties() {
        // Test that createProperties method returns a new SortedProperties instance
        SortedProperties properties = SortedPropertiesFactory.INSTANCE.createProperties();
        assertNotNull(properties);
        assertTrue(properties instanceof SortedProperties);
    }
}