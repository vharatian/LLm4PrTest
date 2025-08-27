package org.apache.commons.collections4.map;

import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class LRUMapLLM_Test<K, V> extends AbstractOrderedMapTest<K, V> {

    public LRUMapTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(LRUMapTest2.class);
    }

    @Override
    public LRUMap<K, V> makeObject() {
        return new LRUMap<>();
    }

    @Override
    public LRUMap<K, V> makeFullMap() {
        return (LRUMap<K, V>) super.makeFullMap();
    }

    @Override
    public boolean isGetStructuralModify() {
        return true;
    }

    @Override
    public LRUMap<K, V> getMap() {
        return (LRUMap<K, V>) super.getMap();
    }

    public void testFinalScanUntilRemovable() {
        LRUMap<K, V> map = new LRUMap<>(10, true);
        assertTrue(map.isScanUntilRemovable());

        map = new LRUMap<>(10, false);
        assertFalse(map.isScanUntilRemovable());
    }

    public void testSerialization() throws Exception {
        LRUMap<K, V> map = new LRUMap<>(10, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(map);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        LRUMap<K, V> deserializedMap = (LRUMap<K, V>) ois.readObject();
        ois.close();

        assertEquals(map.maxSize(), deserializedMap.maxSize());
        assertEquals(map.isScanUntilRemovable(), deserializedMap.isScanUntilRemovable());
    }
}