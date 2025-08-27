package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.SortedMap;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractPatriciaTrieLLM_Test {

    private AbstractPatriciaTrie<String, String> trie;

    @BeforeEach
    public void setUp() {
        trie = new AbstractPatriciaTrie<String, String>(new StringKeyAnalyzer()) {
            @Override
            protected boolean compare(String a, String b) {
                return a.equals(b);
            }

            @Override
            protected int lengthInBits(String key) {
                return key.length() * 8;
            }

            @Override
            protected boolean isBitSet(String key, int bitIndex, int lengthInBits) {
                int byteIndex = bitIndex / 8;
                int bitInByte = bitIndex % 8;
                return (key.charAt(byteIndex) & (1 << (7 - bitInByte))) != 0;
            }

            @Override
            protected String castKey(Object key) {
                return (String) key;
            }
        };
    }

    @Test
    public void testLastKeyWithNullToKey() {
        trie.put("a", "value1");
        trie.put("b", "value2");
        trie.put("c", "value3");

        SortedMap<String, String> rangeMap = trie.tailMap("a");
        assertEquals("c", rangeMap.lastKey());
    }

    @Test
    public void testLastKeyWithNonNullToKey() {
        trie.put("a", "value1");
        trie.put("b", "value2");
        trie.put("c", "value3");

        SortedMap<String, String> rangeMap = trie.subMap("a", "c");
        assertEquals("b", rangeMap.lastKey());
    }
}