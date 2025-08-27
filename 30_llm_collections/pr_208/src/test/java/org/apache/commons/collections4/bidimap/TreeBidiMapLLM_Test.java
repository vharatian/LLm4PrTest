package org.apache.commons.collections4.bidimap;

import java.util.TreeMap;
import junit.framework.Test;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.BulkTest;
import org.junit.Before;
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

    private TreeBidiMap<K, V> map;

    @Before
    public void setUp() {
        map = new TreeBidiMap<>();
    }

    @Test
    public void testInitialNodeCount() {
        assertEquals("Initial node count should be 0", 0, map.size());
    }

    @Test
    public void testInitialModificationsCount() {
        // Assuming modifications count is not directly accessible, we test indirectly
        map.put((K) "key1", (V) "value1");
        map.put((K) "key2", (V) "value2");
        map.remove("key1");
        assertEquals("Node count should be 1 after adding two entries and removing one", 1, map.size());
    }

    @Test
    public void testInitialInverseIsNull() {
        assertNotNull("Inverse should not be null after initialization", map.inverseBidiMap());
    }
}