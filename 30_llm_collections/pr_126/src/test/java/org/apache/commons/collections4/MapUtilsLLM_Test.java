package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import org.junit.Test;

public class MapUtilsLLM_Test {

    @Test(expected = NullPointerException.class)
    public void testInvertMapNull() {
        MapUtils.invertMap(null);
    }

    @Test
    public void testInvertMapNotNull() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Map<String, String> invertedMap = MapUtils.invertMap(map);
        assertEquals("key1", invertedMap.get("value1"));
        assertEquals("key2", invertedMap.get("value2"));
    }

    @Test(expected = NullPointerException.class)
    public void testSafeAddToMapNull() {
        MapUtils.safeAddToMap(null, "key", "value");
    }

    @Test
    public void testSafeAddToMapNotNull() {
        Map<String, Object> map = new HashMap<>();
        MapUtils.safeAddToMap(map, "key1", "value1");
        MapUtils.safeAddToMap(map, "key2", null);
        assertEquals("value1", map.get("key1"));
        assertEquals("", map.get("key2"));
    }

    @Test(expected = NullPointerException.class)
    public void testToMapNull() {
        MapUtils.toMap(null);
    }

    @Test
    public void testToMapNotNull() {
        ResourceBundle bundle = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if ("key1".equals(key)) {
                    return "value1";
                }
                return null;
            }

            @Override
            public java.util.Enumeration<String> getKeys() {
                return java.util.Collections.enumeration(java.util.Arrays.asList("key1"));
            }
        };
        Map<String, Object> map = MapUtils.toMap(bundle);
        assertEquals("value1", map.get("key1"));
    }
}