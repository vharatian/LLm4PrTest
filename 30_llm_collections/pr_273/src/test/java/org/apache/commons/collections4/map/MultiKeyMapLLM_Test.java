package org.apache.commons.collections4.map;

import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class MultiKeyMapLLM_Test<K, V> extends AbstractIterableMapTest<MultiKey<? extends K>, V> {

    public MultiKeyMapTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(MultiKeyMapTest2.class);
    }

    @Override
    public MultiKeyMap<K, V> makeObject() {
        return new MultiKeyMap<>();
    }

    /**
     * Test the constructor change to ensure it works correctly.
     */
    public void testConstructor() {
        MultiKeyMap<K, V> map = new MultiKeyMap<>();
        assertNotNull(map);
        assertTrue(map.isEmpty());
    }
}