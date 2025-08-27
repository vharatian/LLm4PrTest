package org.apache.commons.collections4.bidimap;

import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class DualLinkedHashBidiMapLLM_Test<K, V> extends AbstractBidiMapTest<K, V> {

    public static Test suite() {
        return BulkTest.makeSuite(DualLinkedHashBidiMapTest2.class);
    }

    public DualLinkedHashBidiMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public DualLinkedHashBidiMap<K, V> makeObject() {
        return new DualLinkedHashBidiMap<>();
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    public String[] ignoredTests() {
        return new String[] { "DualLinkedHashBidiMapTest2.bulkTestInverseMap.bulkTestInverseMap" };
    }

    // Test for the constructor with Map parameter
    public void testConstructorWithMap() {
        Map<String, String> initialMap = new LinkedHashMap<>();
        initialMap.put("key1", "value1");
        initialMap.put("key2", "value2");

        DualLinkedHashBidiMap<String, String> bidiMap = new DualLinkedHashBidiMap<>(initialMap);

        assertEquals("value1", bidiMap.get("key1"));
        assertEquals("value2", bidiMap.get("key2"));
        assertEquals("key1", bidiMap.getKey("value1"));
        assertEquals("key2", bidiMap.getKey("value2"));
    }

    // Test for the default constructor
    public void testDefaultConstructor() {
        DualLinkedHashBidiMap<String, String> bidiMap = new DualLinkedHashBidiMap<>();

        assertTrue(bidiMap.isEmpty());
        assertTrue(bidiMap.inverseBidiMap().isEmpty());
    }
}