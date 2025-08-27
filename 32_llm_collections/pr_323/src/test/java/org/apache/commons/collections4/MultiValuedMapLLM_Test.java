package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MultiValuedMapLLM_Test {

    @Test
    public void testPutIncreasesSize() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        int initialSize = map.size();
        assertTrue(map.put("key1", "value1"));
        assertEquals(initialSize + 1, map.size());
    }

    @Test
    public void testPutAllIncreasesSize() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        Map<String, String> inputMap = Map.of("key1", "value1", "key2", "value2");
        int initialSize = map.size();
        assertTrue(map.putAll(inputMap));
        assertEquals(initialSize + inputMap.size(), map.size());
    }

    @Test
    public void testRemoveMapping() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        map.put("key1", "value1");
        assertTrue(map.removeMapping("key1", "value1"));
        assertFalse(map.containsMapping("key1", "value1"));
    }

    @Test
    public void testEntriesView() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        map.put("key1", "value1");
        Collection<Map.Entry<String, String>> entries = map.entries();
        assertEquals(1, entries.size());
    }

    @Test
    public void testKeysView() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        map.put("key1", "value1");
        MultiSet<String> keys = map.keys();
        assertEquals(1, keys.size());
    }

    @Test
    public void testKeySetView() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        map.put("key1", "value1");
        Set<String> keySet = map.keySet();
        assertEquals(1, keySet.size());
    }

    @Test
    public void testValuesView() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        map.put("key1", "value1");
        Collection<String> values = map.values();
        assertEquals(1, values.size());
    }

    @Test
    public void testAsMapView() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        map.put("key1", "value1");
        Map<String, Collection<String>> asMap = map.asMap();
        assertEquals(1, asMap.size());
    }

    @Test
    public void testMapIterator() {
        MultiValuedMap<String, String> map = new MultiValuedHashMap<>();
        map.put("key1", "value1");
        MapIterator<String, String> iterator = map.mapIterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertEquals("key1", iterator.getKey());
        assertEquals("value1", iterator.getValue());
    }
}