package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.BulkTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeBidiMapLLM_Test<K extends Comparable<K>, V extends Comparable<V>> extends AbstractOrderedBidiMapTest<K, V> {

    public static junit.framework.Test suite() {
        return BulkTest.makeSuite(TreeBidiMapTest2.class);
    }

    public TreeBidiMapTest2() {
        super(TreeBidiMapTest2.class.getSimpleName());
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

    /**
     * Test for checking the corrected Javadoc comments.
     */
    @Test
    public void testJavadocCorrections() {
        TreeBidiMap<String, String> map = new TreeBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        // containsKey
        assertTrue(map.containsKey("key1"));
        assertFalse(map.containsKey("key3"));

        // containsValue
        assertTrue(map.containsValue("value1"));
        assertFalse(map.containsValue("value3"));

        // lookupKey
        assertNotNull(map.get("key1"));
        assertNull(map.get("key3"));

        // lookupValue
        assertNotNull(map.getKey("value1"));
        assertNull(map.getKey("value3"));
    }
}