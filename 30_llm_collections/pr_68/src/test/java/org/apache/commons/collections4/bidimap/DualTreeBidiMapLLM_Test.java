package org.apache.commons.collections4.bidimap;

import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class DualTreeBidiMapLLM_Test<K extends Comparable<K>, V extends Comparable<V>> extends AbstractSortedBidiMapTest<K, V> {

    public static Test suite() {
        return BulkTest.makeSuite(DualTreeBidiMapTest2.class);
    }

    public DualTreeBidiMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public DualTreeBidiMap<K, V> makeObject() {
        return new DualTreeBidiMap<>();
    }

    // Test to ensure the setValue method works correctly after the code change
    public void testSetValue() {
        DualTreeBidiMap<K, V> map = makeObject();
        K key = (K) "key1";
        V value1 = (V) "value1";
        V value2 = (V) "value2";

        map.put(key, value1);
        assertEquals(value1, map.get(key));

        map.entrySet().iterator().next().setValue(value2);
        assertEquals(value2, map.get(key));
    }
}