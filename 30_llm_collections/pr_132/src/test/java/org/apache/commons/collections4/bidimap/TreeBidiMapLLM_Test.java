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

    // Test for the formatting change in doToString method
    public void testDoToStringFormatting() {
        TreeBidiMap<String, String> map = new TreeBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        String expected = "{key1=value1, key2=value2}";
        assertEquals(expected, map.toString());
    }

    // Test for the spacing change in readObject method
    public void testReadObjectSpacing() throws Exception {
        TreeBidiMap<String, String> map = new TreeBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(map);
        oos.close();

        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        TreeBidiMap<String, String> deserializedMap = (TreeBidiMap<String, String>) ois.readObject();
        ois.close();

        assertEquals(map, deserializedMap);
    }

    // Test for the constructor visibility change in KeyView
    public void testKeyViewConstructorVisibility() {
        TreeBidiMap<String, String> map = new TreeBidiMap<>();
        TreeBidiMap<String, String>.KeyView keyView = map.new KeyView(TreeBidiMap.DataElement.KEY);
        assertNotNull(keyView);
    }

    // Test for the constructor visibility change in ValueView
    public void testValueViewConstructorVisibility() {
        TreeBidiMap<String, String> map = new TreeBidiMap<>();
        TreeBidiMap<String, String>.ValueView valueView = map.new ValueView(TreeBidiMap.DataElement.VALUE);
        assertNotNull(valueView);
    }

    // Test for the constructor visibility change in InverseViewMapIterator
    public void testInverseViewMapIteratorConstructorVisibility() {
        TreeBidiMap<String, String> map = new TreeBidiMap<>();
        TreeBidiMap<String, String>.InverseViewMapIterator iterator = map.new InverseViewMapIterator(TreeBidiMap.DataElement.VALUE);
        assertNotNull(iterator);
    }
}