package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultiMapLLM_Test {

    @Test
    public void testPutAddsValueToCollection() {
        MultiMap<String, String> multiMap = new MultiValueMap<>();
        multiMap.put("key1", "value1");
        multiMap.put("key1", "value2");

        Collection<Object> values = multiMap.get("key1");
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertEquals(2, values.size());
    }

    @Test
    public void testPutDoesNotReplacePreviousValue() {
        MultiMap<String, String> multiMap = new MultiValueMap<>();
        multiMap.put("key1", "value1");
        multiMap.put("key1", "value2");

        Collection<Object> values = multiMap.get("key1");
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertEquals(2, values.size());
    }
}