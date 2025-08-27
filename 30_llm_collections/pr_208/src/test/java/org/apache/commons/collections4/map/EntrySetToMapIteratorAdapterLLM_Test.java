package org.apache.commons.collections4.map;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;

public class EntrySetToMapIteratorAdapterLLM_Test {

    private EntrySetToMapIteratorAdapter<String, String> adapter;
    private Map<String, String> map;

    @Before
    public void setUp() {
        map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        adapter = new EntrySetToMapIteratorAdapter<>(entrySet);
    }

    @Test
    public void testGetKey() {
        adapter.next();
        assertEquals("key1", adapter.getKey());
    }

    @Test
    public void testGetValue() {
        adapter.next();
        assertEquals("value1", adapter.getValue());
    }

    @Test
    public void testSetValue() {
        adapter.next();
        assertEquals("value1", adapter.setValue("newValue1"));
        assertEquals("newValue1", map.get("key1"));
    }

    @Test
    public void testHasNext() {
        assertTrue(adapter.hasNext());
        adapter.next();
        assertTrue(adapter.hasNext());
        adapter.next();
        assertFalse(adapter.hasNext());
    }

    @Test
    public void testNext() {
        assertEquals("key1", adapter.next());
        assertEquals("key2", adapter.next());
    }

    @Test
    public void testReset() {
        adapter.next();
        adapter.reset();
        assertEquals("key1", adapter.next());
    }

    @Test
    public void testRemove() {
        adapter.next();
        adapter.remove();
        assertFalse(map.containsKey("key1"));
    }

    @Test(expected = IllegalStateException.class)
    public void testCurrentWithoutNext() {
        adapter.current();
    }

    @Test
    public void testCurrentAfterNext() {
        adapter.next();
        assertEquals("key1", adapter.current().getKey());
    }
}