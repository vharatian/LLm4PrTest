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

    /**
     * Test to ensure that the setValue() method on iterators will succeed only if the new value being set is not already in the bidi map.
     */
    public void testSetValueNotInBidiMap() {
        DualTreeBidiMap<String, String> map = new DualTreeBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        OrderedMapIterator<String, String> it = map.mapIterator();
        it.next();
        try {
            it.setValue("value3");
            assertEquals("value3", map.get("key1"));
        } catch (IllegalArgumentException e) {
            fail("setValue() should succeed when the new value is not already in the bidi map");
        }
    }

    /**
     * Test to ensure that the setValue() method on iterators will fail if the new value being set is already in the bidi map.
     */
    public void testSetValueAlreadyInBidiMap() {
        DualTreeBidiMap<String, String> map = new DualTreeBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        OrderedMapIterator<String, String> it = map.mapIterator();
        it.next();
        try {
            it.setValue("value2");
            fail("setValue() should throw IllegalArgumentException when the new value is already in the bidi map");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}