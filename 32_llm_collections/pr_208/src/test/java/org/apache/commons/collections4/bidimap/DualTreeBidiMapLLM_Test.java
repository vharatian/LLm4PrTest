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
     * Test to ensure that the 'last' field in BidiOrderedMapIterator is properly initialized.
     */
    public void testBidiOrderedMapIteratorLastFieldInitialization() {
        DualTreeBidiMap<K, V> map = makeObject();
        map.put((K) "one", (V) "1");
        map.put((K) "two", (V) "2");
        map.put((K) "three", (V) "3");

        DualTreeBidiMap.BidiOrderedMapIterator<K, V> iterator = map.mapIterator();

        // Check initial state of 'last' field
        assertNull("The 'last' field should be null initially", getLastField(iterator));

        // Move the iterator forward and check the 'last' field
        iterator.next();
        assertNotNull("The 'last' field should not be null after calling next()", getLastField(iterator));
    }

    /**
     * Helper method to access the private 'last' field in BidiOrderedMapIterator.
     */
    private Map.Entry<K, V> getLastField(DualTreeBidiMap.BidiOrderedMapIterator<K, V> iterator) {
        try {
            java.lang.reflect.Field lastField = DualTreeBidiMap.BidiOrderedMapIterator.class.getDeclaredField("last");
            lastField.setAccessible(true);
            return (Map.Entry<K, V>) lastField.get(iterator);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}