package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class InterpolatorStringLookupLLM_Test {

    @Test
    public void testConstructorWithNullDefaultMap() {
        // Test the constructor with a null default map
        InterpolatorStringLookup lookup = new InterpolatorStringLookup((Map<String, Object>) null);
        String value = lookup.lookup("anyKey");
        assertNull(value);
    }

    @Test
    public void testConstructorWithEmptyDefaultMap() {
        // Test the constructor with an empty default map
        Map<String, Object> emptyMap = new HashMap<>();
        InterpolatorStringLookup lookup = new InterpolatorStringLookup(emptyMap);
        String value = lookup.lookup("anyKey");
        assertNull(value);
    }

    @Test
    public void testConstructorWithNonEmptyDefaultMap() {
        // Test the constructor with a non-empty default map
        Map<String, Object> defaultMap = new HashMap<>();
        defaultMap.put("key1", "value1");
        InterpolatorStringLookup lookup = new InterpolatorStringLookup(defaultMap);
        String value = lookup.lookup("key1");
        assertEquals("value1", value);
    }
}