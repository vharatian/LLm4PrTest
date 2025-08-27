package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ListOrderedMapLLM_Test<K, V> extends ListOrderedMapTest<K, V> {

    @Test
    public void testPutWithIndexOutOfBounds() {
        ListOrderedMap<K, V> lom = getMap();
        assertThrows(IndexOutOfBoundsException.class, () -> lom.put(-1, (K) "key", (V) "value"));
        assertThrows(IndexOutOfBoundsException.class, () -> lom.put(lom.size() + 1, (K) "key", (V) "value"));
    }

    @Test
    public void testPutAllWithIndexOutOfBounds() {
        ListOrderedMap<K, V> lom = getMap();
        Map<K, V> map = new HashMap<>();
        map.put((K) "key1", (V) "value1");
        map.put((K) "key2", (V) "value2");
        assertThrows(IndexOutOfBoundsException.class, () -> lom.putAll(-1, map));
        assertThrows(IndexOutOfBoundsException.class, () -> lom.putAll(lom.size() + 1, map));
    }
}