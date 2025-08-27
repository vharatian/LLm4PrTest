package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultiValuedMapLLM_Test {

    @Test
    public void testPutMethodDocumentation() {
        // This test is to ensure that the documentation change does not affect functionality.
        // The functionality of the put method should remain the same.
        MultiValuedMap<String, String> multiValuedMap = new MultiValuedHashMap<>();
        multiValuedMap.put("key1", "value1");
        multiValuedMap.put("key1", "value2");

        Collection<String> values = multiValuedMap.get("key1");
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertEquals(2, values.size());
    }
}