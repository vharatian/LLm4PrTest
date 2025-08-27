package org.apache.commons.collections4.map;

import java.util.HashMap;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class ListOrderedMapLLM_Test<K, V> extends AbstractOrderedMapTest<K, V> {

    public ListOrderedMapTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(ListOrderedMapTest2.class);
    }

    @Override
    public ListOrderedMap<K, V> makeObject() {
        return ListOrderedMap.listOrderedMap(new HashMap<>());
    }

    public void testConstructorWithDiamondOperator() {
        ListOrderedMap<K, V> lom = new ListOrderedMap<>();
        assertTrue(lom.isEmpty());
        assertEquals(0, lom.size());
    }
}