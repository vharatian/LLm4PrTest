package org.apache.commons.collections4.bidimap;

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
     * Test to ensure that the DataElement enum constructor is accessible.
     */
    public void testDataElementConstructorAccessibility() {
        // Accessing the DataElement enum constructor
        TreeBidiMap.DataElement keyElement = TreeBidiMap.DataElement.KEY;
        TreeBidiMap.DataElement valueElement = TreeBidiMap.DataElement.VALUE;

        // Asserting that the elements are not null
        assertNotNull(keyElement);
        assertNotNull(valueElement);

        // Asserting that the descriptions are correct
        assertEquals("key", keyElement.toString());
        assertEquals("value", valueElement.toString());
    }
}