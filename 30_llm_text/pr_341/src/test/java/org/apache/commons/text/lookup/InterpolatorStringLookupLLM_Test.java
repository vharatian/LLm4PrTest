package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class InterpolatorStringLookupLLM_Test {

    @Test
    public void testLookupWithStringLookupFactoryToKey() {
        final Map<String, StringLookup> map = new HashMap<>();
        map.put("TestKey", key -> "TestValue");
        final StringLookup lookup = new InterpolatorStringLookup(map, null, false);

        // Test with prefix using StringLookupFactory.toKey
        String value = lookup.lookup("testkey:TestKey");
        assertEquals("TestValue", value);

        // Test without prefix
        value = lookup.lookup("TestKey");
        assertNull(value);
    }

    @Test
    public void testLookupWithPrefixUsingStringLookupFactoryToKey() {
        final Map<String, StringLookup> map = new HashMap<>();
        map.put("TestKey", key -> "TestValue");
        final StringLookup lookup = new InterpolatorStringLookup(map, null, false);

        // Test with prefix using StringLookupFactory.toKey
        String value = lookup.lookup("testkey:TestKey");
        assertEquals("TestValue", value);

        // Test with a non-existent prefix
        value = lookup.lookup("nonexistent:TestKey");
        assertNull(value);
    }
}