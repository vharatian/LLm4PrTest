package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractHashedMapLLM_Test {

    private AbstractHashedMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new AbstractHashedMap<String, String>(16, 0.75f, 12) {
            @Override
            protected void init() {
                // No initialization needed for this test
            }
        };
    }

    @Test
    public void testEnsureCapacity() throws Exception {
        // Populate the map with some entries
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }

        // Ensure the capacity is increased
        map.ensureCapacity(32);

        // Use reflection to access the private 'data' field
        Field dataField = AbstractHashedMap.class.getDeclaredField("data");
        dataField.setAccessible(true);
        AbstractHashedMap.HashEntry<String, String>[] data = (AbstractHashedMap.HashEntry<String, String>[]) dataField.get(map);

        // Check the new capacity
        assertEquals(32, data.length);
    }

    @Test
    public void testEnsureCapacityWithEmptyMap() throws Exception {
        // Ensure the capacity is increased on an empty map
        map.ensureCapacity(32);

        // Use reflection to access the private 'data' field
        Field dataField = AbstractHashedMap.class.getDeclaredField("data");
        dataField.setAccessible(true);
        AbstractHashedMap.HashEntry<String, String>[] data = (AbstractHashedMap.HashEntry<String, String>[]) dataField.get(map);

        // Check the new capacity
        assertEquals(32, data.length);
    }
}