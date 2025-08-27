package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class InterpolatorStringLookupLLM_Test {

    @Test
    public void testLookupWithKey() {
        final Map<String, String> map = new HashMap<>();
        map.put("testKey", "testValue");
        final StringLookup lookup = new InterpolatorStringLookup(StringLookupFactory.INSTANCE.mapStringLookup(map));
        
        // Test with key containing prefix separator
        String value = lookup.lookup("ctx:testKey");
        assertEquals("testValue", value);
        
        // Test with key without prefix separator
        value = lookup.lookup("testKey");
        assertEquals("testValue", value);
        
        // Test with key that does not exist
        value = lookup.lookup("nonExistentKey");
        assertNull(value);
    }

    @Test
    public void testLookupWithNullKey() {
        final StringLookup lookup = new InterpolatorStringLookup();
        
        // Test with null key
        String value = lookup.lookup(null);
        assertNull(value);
    }
}