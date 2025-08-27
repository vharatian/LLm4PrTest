package org.apache.commons.collections4.map;

import java.util.Iterator;
import java.util.Map;
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

    public void testAddMappingExceptionMessages() {
        final LRUMap<K, V> map = new LRUMap<>(2);
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();

        // Fill the map to its max size
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);

        try {
            // Trigger the IllegalStateException with a null reuse entry
            map.put(keys[2], values[2]);
            fail("Expected IllegalStateException due to null reuse entry");
        } catch (IllegalStateException ex) {
            assertTrue(ex.getMessage().contains("reuse=null, header.after="));
            assertTrue(ex.getMessage().contains("header.before="));
        }
    }

    public void testAddMappingScanUntilRemovableExceptionMessages() {
        final LRUMap<K, V> map = new LRUMap<>(2, true);
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();

        // Fill the map to its max size
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);

        try {
            // Trigger the IllegalStateException with a null reuse entry
            map.put(keys[2], values[2]);
            fail("Expected IllegalStateException due to null reuse entry");
        } catch (IllegalStateException ex) {
            assertTrue(ex.getMessage().contains("Entry.after=null, header.after="));
            assertTrue(ex.getMessage().contains("header.before="));
        }
    }
}