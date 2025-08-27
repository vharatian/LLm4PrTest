package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractDualBidiMapLLM_Test {

    private AbstractDualBidiMap<String, Integer> bidiMap;

    @BeforeEach
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
    public void testCreateBidiMap() {
        BidiMap<Integer, String> inverse = bidiMap.createBidiMap(new HashMap<>(), new HashMap<>(), bidiMap);
        assertNull(inverse);
    }

    @Test
    public void testMapIterator() {
        bidiMap.put("one", 1);
        bidiMap.put("two", 2);
        MapIterator<String, Integer> iterator = bidiMap.mapIterator();
        assertTrue(iterator.hasNext());
        assertEquals("one", iterator.next());
        assertEquals(1, iterator.getValue());
        iterator.remove();
        assertFalse(bidiMap.containsKey("one"));
        assertFalse(bidiMap.containsValue(1));
    }
}