package org.apache.commons.collections4;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderedMapLLM_Test {

    @Test
    public void testMapIterator() {
        // Assuming a concrete implementation of OrderedMap is available for testing
        OrderedMap<String, String> orderedMap = new ConcreteOrderedMap<>();
        orderedMap.put("key1", "value1");
        orderedMap.put("key2", "value2");

        OrderedMapIterator<String, String> iterator = orderedMap.mapIterator();
        assertNotNull(iterator);

        // Check forward iteration
        assertTrue(iterator.hasNext());
        assertEquals("key1", iterator.next());
        assertEquals("value1", iterator.getValue());

        assertTrue(iterator.hasNext());
        assertEquals("key2", iterator.next());
        assertEquals("value2", iterator.getValue());

        // Check backward iteration
        assertTrue(iterator.hasPrevious());
        assertEquals("key2", iterator.previous());
        assertEquals("value2", iterator.getValue());

        assertTrue(iterator.hasPrevious());
        assertEquals("key1", iterator.previous());
        assertEquals("value1", iterator.getValue());
    }

    @Test
    public void testFirstKey() {
        OrderedMap<String, String> orderedMap = new ConcreteOrderedMap<>();
        orderedMap.put("key1", "value1");
        orderedMap.put("key2", "value2");

        assertEquals("key1", orderedMap.firstKey());
    }

    @Test
    public void testLastKey() {
        OrderedMap<String, String> orderedMap = new ConcreteOrderedMap<>();
        orderedMap.put("key1", "value1");
        orderedMap.put("key2", "value2");

        assertEquals("key2", orderedMap.lastKey());
    }

    @Test
    public void testNextKey() {
        OrderedMap<String, String> orderedMap = new ConcreteOrderedMap<>();
        orderedMap.put("key1", "value1");
        orderedMap.put("key2", "value2");

        assertEquals("key2", orderedMap.nextKey("key1"));
        assertNull(orderedMap.nextKey("key2"));
    }

    @Test
    public void testPreviousKey() {
        OrderedMap<String, String> orderedMap = new ConcreteOrderedMap<>();
        orderedMap.put("key1", "value1");
        orderedMap.put("key2", "value2");

        assertEquals("key1", orderedMap.previousKey("key2"));
        assertNull(orderedMap.previousKey("key1"));
    }
}