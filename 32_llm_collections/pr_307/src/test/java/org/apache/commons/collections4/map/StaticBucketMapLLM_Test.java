package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class StaticBucketMapLLM_Test<K, V> extends AbstractIterableMapTest<K, V> {

    public StaticBucketMapTest2() {
        super(StaticBucketMapTest2.class.getSimpleName());
    }

    @Override
    public StaticBucketMap<K, V> makeObject() {
        return new StaticBucketMap<>(30);
    }

    @Override
    public boolean isFailFastExpected() {
        return false;
    }

    @Override
    public String[] ignoredTests() {
        final String pre = "StaticBucketMapTest2.bulkTestMap";
        final String post = ".testCollectionIteratorFailFast";
        return new String[] {
            pre + "EntrySet" + post,
            pre + "KeySet" + post,
            pre + "Values" + post
        };
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_get_withObjectsEquals() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        map.put((K) "A", (V) "ValueA");
        map.put((K) "B", (V) "ValueB");
        assertEquals("ValueA", map.get("A"));
        assertEquals("ValueB", map.get("B"));
        assertNull(map.get("C"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_containsKey_withObjectsEquals() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        map.put((K) "A", (V) "ValueA");
        map.put((K) "B", (V) "ValueB");
        assertTrue(map.containsKey("A"));
        assertTrue(map.containsKey("B"));
        assertFalse(map.containsKey("C"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_containsValue_withObjectsEquals() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        map.put((K) "A", (V) "ValueA");
        map.put((K) "B", (V) "ValueB");
        assertTrue(map.containsValue("ValueA"));
        assertTrue(map.containsValue("ValueB"));
        assertFalse(map.containsValue("ValueC"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_put_withObjectsEquals() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        assertNull(map.put((K) "A", (V) "ValueA"));
        assertEquals("ValueA", map.put((K) "A", (V) "ValueAUpdated"));
        assertEquals("ValueAUpdated", map.get("A"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_remove_withObjectsEquals() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        map.put((K) "A", (V) "ValueA");
        map.put((K) "B", (V) "ValueB");
        assertEquals("ValueA", map.remove("A"));
        assertNull(map.get("A"));
        assertEquals("ValueB", map.remove("B"));
        assertNull(map.get("B"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_keySet_remove_withObjectsEquals() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        map.put((K) "A", (V) "ValueA");
        map.put((K) "B", (V) "ValueB");
        assertTrue(map.keySet().remove("A"));
        assertFalse(map.containsKey("A"));
        assertTrue(map.keySet().remove("B"));
        assertFalse(map.containsKey("B"));
    }
}