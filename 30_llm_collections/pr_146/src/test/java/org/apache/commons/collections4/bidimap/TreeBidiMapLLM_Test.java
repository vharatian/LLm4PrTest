package org.apache.commons.collections4.bidimap;

import java.util.TreeMap;
import junit.framework.Test;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.BulkTest;

public class TreeBidiMapLLM_Test<K extends Comparable<K>, V extends Comparable<V>> extends AbstractOrderedBidiMapTest<K, V> {

    public static Test suite() {
        return BulkTest.makeSuite(TreeBidiMapTest2.class);
    }

    public TreeBidiMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public BidiMap<K, V> makeObject() {
        return new TreeBidiMap<>();
    }

    @Override
    public TreeMap<K, V> makeConfirmedMap() {
        return new TreeMap<>();
    }

    @Override
    public String[] ignoredTests() {
        return new String[] {"TreeBidiMapTest2.bulkTestInverseMap.bulkTestInverseMap"};
    }

    @Override
    public boolean isAllowNullKey() {
        return false;
    }

    @Override
    public boolean isAllowNullValue() {
        return false;
    }

    @Override
    public boolean isSetValueSupported() {
        return false;
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    // Test to ensure Javadoc changes do not affect functionality
    public void testJavadocChanges() {
        TreeBidiMap<String, String> map = new TreeBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        // Test lookupKey method
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));

        // Test lookupValue method
        assertEquals("key1", map.getKey("value1"));
        assertEquals("key2", map.getKey("value2"));

        // Test nextGreater method
        assertEquals("key2", map.nextKey("key1"));
        assertNull(map.nextKey("key2"));

        // Test nextSmaller method
        assertEquals("key1", map.previousKey("key2"));
        assertNull(map.previousKey("key1"));

        // Test leastNode and greatestNode methods indirectly through firstKey and lastKey
        assertEquals("key1", map.firstKey());
        assertEquals("key2", map.lastKey());

        // Test red-black tree properties indirectly through put and remove operations
        map.remove("key1");
        assertNull(map.get("key1"));
        assertEquals("key2", map.firstKey());
        assertEquals("key2", map.lastKey());
    }
}