package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractPatriciaTrieLLM_Test {

    private AbstractPatriciaTrie<String, String> trie;

    @BeforeEach
    public void setUp() {
        trie = new AbstractPatriciaTrie<String, String>(new StringKeyAnalyzer()) {
            @Override
            public boolean containsValue(Object value) {
                return false;
            }
        };
    }

    @Test
    public void testConstructorWithKeyAnalyzer() {
        assertNotNull(trie);
    }

    @Test
    public void testPutAndGet() {
        trie.put("key1", "value1");
        assertEquals("value1", trie.get("key1"));
    }

    @Test
    public void testClear() {
        trie.put("key1", "value1");
        trie.clear();
        assertNull(trie.get("key1"));
    }

    @Test
    public void testSize() {
        assertEquals(0, trie.size());
        trie.put("key1", "value1");
        assertEquals(1, trie.size());
    }

    @Test
    public void testRemove() {
        trie.put("key1", "value1");
        assertEquals("value1", trie.remove("key1"));
        assertNull(trie.get("key1"));
    }

    @Test
    public void testContainsKey() {
        trie.put("key1", "value1");
        assertTrue(trie.containsKey("key1"));
        assertFalse(trie.containsKey("key2"));
    }

    @Test
    public void testFirstKey() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        assertEquals("key1", trie.firstKey());
    }

    @Test
    public void testLastKey() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        assertEquals("key2", trie.lastKey());
    }

    @Test
    public void testNextKey() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        assertEquals("key2", trie.nextKey("key1"));
    }

    @Test
    public void testPreviousKey() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        assertEquals("key1", trie.previousKey("key2"));
    }

    @Test
    public void testMapIterator() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        OrderedMapIterator<String, String> iterator = trie.mapIterator();
        assertTrue(iterator.hasNext());
        assertEquals("key1", iterator.next());
        assertEquals("value1", iterator.getValue());
        assertTrue(iterator.hasNext());
        assertEquals("key2", iterator.next());
        assertEquals("value2", iterator.getValue());
    }

    @Test
    public void testPrefixMap() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        SortedMap<String, String> prefixMap = trie.prefixMap("key");
        assertEquals(2, prefixMap.size());
        assertTrue(prefixMap.containsKey("key1"));
        assertTrue(prefixMap.containsKey("key2"));
    }

    @Test
    public void testHeadMap() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        SortedMap<String, String> headMap = trie.headMap("key2");
        assertEquals(1, headMap.size());
        assertTrue(headMap.containsKey("key1"));
    }

    @Test
    public void testSubMap() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        SortedMap<String, String> subMap = trie.subMap("key1", "key2");
        assertEquals(1, subMap.size());
        assertTrue(subMap.containsKey("key1"));
    }

    @Test
    public void testTailMap() {
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        SortedMap<String, String> tailMap = trie.tailMap("key1");
        assertEquals(2, tailMap.size());
        assertTrue(tailMap.containsKey("key1"));
        assertTrue(tailMap.containsKey("key2"));
    }
}