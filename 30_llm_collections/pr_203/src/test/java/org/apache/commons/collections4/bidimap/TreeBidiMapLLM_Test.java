package org.apache.commons.collections4.bidimap;

import java.util.TreeMap;
import junit.framework.Test;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.BulkTest;
import org.junit.Test;
import static org.junit.Assert.*;

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

    /**
     * Test the nextGreater method with a null node.
     */
    @Test
    public void testNextGreaterWithNullNode() {
        TreeBidiMap<Integer, String> map = new TreeBidiMap<>();
        assertNull(map.nextGreater(null, TreeBidiMap.DataElement.KEY));
    }

    /**
     * Test the nextSmaller method with a null node.
     */
    @Test
    public void testNextSmallerWithNullNode() {
        TreeBidiMap<Integer, String> map = new TreeBidiMap<>();
        assertNull(map.nextSmaller(null, TreeBidiMap.DataElement.KEY));
    }

    /**
     * Test the nextGreater method with a non-null node.
     */
    @Test
    public void testNextGreaterWithNonNullNode() {
        TreeBidiMap<Integer, String> map = new TreeBidiMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        TreeBidiMap.Node<Integer, String> node = map.lookupKey(1);
        assertEquals("two", map.nextGreater(node, TreeBidiMap.DataElement.KEY).getValue());
    }

    /**
     * Test the nextSmaller method with a non-null node.
     */
    @Test
    public void testNextSmallerWithNonNullNode() {
        TreeBidiMap<Integer, String> map = new TreeBidiMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        TreeBidiMap.Node<Integer, String> node = map.lookupKey(3);
        assertEquals("two", map.nextSmaller(node, TreeBidiMap.DataElement.KEY).getValue());
    }
}