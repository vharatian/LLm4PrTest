package org.apache.commons.collections4.bidimap;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class AbstractDualBidiMapLLM_Test {

    private AbstractDualBidiMap<String, Integer> bidiMap;

    @Before
    public void setUp() {
        Map<String, Integer> normalMap = new HashMap<>();
        Map<Integer, String> reverseMap = new HashMap<>();
        bidiMap = new AbstractDualBidiMap<String, Integer>(normalMap, reverseMap) {
            @Override
            protected BidiMap<Integer, String> createBidiMap(Map<Integer, String> normalMap, Map<String, Integer> reverseMap, BidiMap<String, Integer> inverseMap) {
                return null;
            }
        };
    }

    @Test
    public void testRemoveIf() {
        bidiMap.put("one", 1);
        bidiMap.put("two", 2);
        bidiMap.put("three", 3);

        Set<String> keySet = bidiMap.keySet();
        boolean removed = keySet.removeIf(key -> key.startsWith("t"));

        assertTrue(removed);
        assertFalse(bidiMap.containsKey("two"));
        assertFalse(bidiMap.containsKey("three"));
        assertTrue(bidiMap.containsKey("one"));
    }

    @Test
    public void testRemoveIfWithNullPredicate() {
        bidiMap.put("one", 1);
        bidiMap.put("two", 2);
        bidiMap.put("three", 3);

        Set<String> keySet = bidiMap.keySet();
        boolean removed = keySet.removeIf(null);

        assertFalse(removed);
        assertTrue(bidiMap.containsKey("one"));
        assertTrue(bidiMap.containsKey("two"));
        assertTrue(bidiMap.containsKey("three"));
    }

    @Test
    public void testRemoveIfOnEmptyMap() {
        Set<String> keySet = bidiMap.keySet();
        boolean removed = keySet.removeIf(key -> key.startsWith("t"));

        assertFalse(removed);
    }
}