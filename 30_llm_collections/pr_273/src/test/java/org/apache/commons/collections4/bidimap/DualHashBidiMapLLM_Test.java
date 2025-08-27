package org.apache.commons.collections4.bidimap;

import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class DualHashBidiMapLLM_Test<K, V> extends AbstractBidiMapTest<K, V> {
    public static Test suite() {
        return BulkTest.makeSuite(DualHashBidiMapTest2.class);
    }

    public DualHashBidiMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public DualHashBidiMap<K, V> makeObject() {
        return new DualHashBidiMap<>();
    }

    @Override
    public String[] ignoredTests() {
        return new String[] { "DualHashBidiMapTest2.bulkTestInverseMap.bulkTestInverseMap" };
    }

    // Test to ensure the constructor initializes the maps correctly with diamond operator
    public void testConstructorWithDiamondOperator() {
        DualHashBidiMap<String, Integer> map = new DualHashBidiMap<>();
        assertTrue(map.isEmpty());
    }

    // Test to ensure the constructor with map parameter initializes the maps correctly with diamond operator
    public void testConstructorWithMapAndDiamondOperator() {
        Map<String, Integer> initialMap = new HashMap<>();
        initialMap.put("one", 1);
        initialMap.put("two", 2);

        DualHashBidiMap<String, Integer> map = new DualHashBidiMap<>(initialMap);
        assertEquals(2, map.size());
        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));
    }
}