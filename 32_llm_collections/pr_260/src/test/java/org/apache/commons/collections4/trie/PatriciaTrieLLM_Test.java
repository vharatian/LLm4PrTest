package org.apache.commons.collections4.trie;

import java.util.SortedMap;
import org.apache.commons.collections4.Trie;
import org.junit.Assert;
import org.junit.Test;

public class PatriciaTrieLLM_Test<V> extends AbstractSortedMapTest<String, V> {

    public PatriciaTrieTest2(final String testName) {
        super(testName);
    }

    @Override
    public SortedMap<String, V> makeObject() {
        return new PatriciaTrie<>();
    }

    @Override
    public boolean isAllowNullKey() {
        return false;
    }

    @Test
    public void testPrefixMapWithFullQualifiedName() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String[] keys = new String[]{
            "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
            "Alberts", "Allie", "Alliese", "Alabama", "Banane",
            "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
            "Amma"
        };
        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        Assert.assertEquals(8, map.size());
        Assert.assertEquals("Alabama", map.firstKey());
        Assert.assertEquals("Alliese", map.lastKey());
        Assert.assertEquals("Albertoo", map.get("Albertoo"));
        Assert.assertNotNull(trie.get("Xavier"));
        Assert.assertNull(map.get("Xavier"));
        Assert.assertNull(trie.get("Alice"));
        Assert.assertNull(map.get("Alice"));
    }
}