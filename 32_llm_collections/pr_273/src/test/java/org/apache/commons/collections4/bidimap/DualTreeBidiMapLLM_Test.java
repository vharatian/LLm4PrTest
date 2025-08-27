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

    @Override
    public String[] ignoredTests() {
        final String recursiveTest = "DualTreeBidiMapTest2.bulkTestInverseMap.bulkTestInverseMap";
        return new String[] { recursiveTest };
    }

    // Test for the constructor with no arguments
    public void testConstructorNoArgs() {
        DualTreeBidiMap<K, V> map = new DualTreeBidiMap<>();
        assertNotNull("Comparator should be null", map.comparator());
        assertNotNull("Value comparator should be null", map.valueComparator());
    }

    // Test for the constructor with a map argument
    public void testConstructorWithMap() {
        Map<K, V> initialMap = new TreeMap<>();
        DualTreeBidiMap<K, V> map = new DualTreeBidiMap<>(initialMap);
        assertNotNull("Comparator should be null", map.comparator());
        assertNotNull("Value comparator should be null", map.valueComparator());
    }

    // Test for the constructor with key and value comparators
    public void testConstructorWithComparators() {
        Comparator<K> keyComparator = Comparator.naturalOrder();
        Comparator<V> valueComparator = Comparator.naturalOrder();
        DualTreeBidiMap<K, V> map = new DualTreeBidiMap<>(keyComparator, valueComparator);
        assertEquals("Key comparator should match", keyComparator, map.comparator());
        assertEquals("Value comparator should match", valueComparator, map.valueComparator());
    }
}