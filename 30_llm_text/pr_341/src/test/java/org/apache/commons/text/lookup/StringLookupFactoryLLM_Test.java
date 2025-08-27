package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class StringLookupFactoryLLM_Test {

    @Test
    public void testAddDefaultStringLookupsWithSystemProperty() {
        System.setProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY, "BASE64_ENCODER,ENVIRONMENT");
        final Map<String, StringLookup> stringLookupMap = new HashMap<>();
        StringLookupFactory.INSTANCE.addDefaultStringLookups(stringLookupMap);
        assertTrue(stringLookupMap.containsKey(InterpolatorStringLookup.toKey(StringLookupFactory.KEY_BASE64_ENCODER)));
        assertTrue(stringLookupMap.containsKey(InterpolatorStringLookup.toKey(StringLookupFactory.KEY_ENV)));
        assertEquals(2, stringLookupMap.size());
        System.clearProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY);
    }

    @Test
    public void testAddDefaultStringLookupsWithEmptySystemProperty() {
        System.setProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY, "");
        final Map<String, StringLookup> stringLookupMap = new HashMap<>();
        StringLookupFactory.INSTANCE.addDefaultStringLookups(stringLookupMap);
        assertTrue(stringLookupMap.isEmpty());
        System.clearProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY);
    }

    @Test
    public void testAddDefaultStringLookupsWithInvalidSystemProperty() {
        System.setProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY, "INVALID_LOOKUP");
        final Map<String, StringLookup> stringLookupMap = new HashMap<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            StringLookupFactory.INSTANCE.addDefaultStringLookups(stringLookupMap);
        });
        assertTrue(exception.getMessage().contains("Invalid default string lookups definition"));
        System.clearProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY);
    }

    @Test
    public void testToKey() {
        assertEquals("testkey", StringLookupFactory.toKey("TestKey"));
        assertEquals("testkey", StringLookupFactory.toKey("testkey"));
        assertEquals("testkey", StringLookupFactory.toKey("TESTKEY"));
    }

    @Test
    public void testDefaultStringLookupsHolder() {
        Properties props = new Properties();
        props.setProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY, "BASE64_ENCODER,ENVIRONMENT");
        StringLookupFactory.DefaultStringLookupsHolder holder = new StringLookupFactory.DefaultStringLookupsHolder(props);
        Map<String, StringLookup> lookups = holder.getDefaultStringLookups();
        assertTrue(lookups.containsKey(InterpolatorStringLookup.toKey(StringLookupFactory.KEY_BASE64_ENCODER)));
        assertTrue(lookups.containsKey(InterpolatorStringLookup.toKey(StringLookupFactory.KEY_ENV)));
        assertEquals(2, lookups.size());
    }
}