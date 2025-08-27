package org.apache.commons.collections4.map;

import org.junit.Before;
import org.junit.Test;
import java.lang.ref.ReferenceQueue;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class AbstractReferenceMapLLM_Test {

    private AbstractReferenceMap<String, String> map;

    @Before
    public void setUp() {
        map = new AbstractReferenceMap<String, String>(
                AbstractReferenceMap.ReferenceStrength.WEAK,
                AbstractReferenceMap.ReferenceStrength.WEAK,
                16, 0.75f, true) {
            @Override
            protected void init() {
                super.init();
            }
        };
        map.put("key1", "value1");
        map.put("key2", "value2");
    }

    @Test
    public void testHasNext() {
        AbstractReferenceMap.ReferenceBaseIterator<String, String> iterator = map.new ReferenceMapIterator<>(map);
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNextEntry() {
        AbstractReferenceMap.ReferenceBaseIterator<String, String> iterator = map.new ReferenceMapIterator<>(map);
        assertNotNull(iterator.nextEntry());
        assertNotNull(iterator.nextEntry());
        try {
            iterator.nextEntry();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
    }

    @Test
    public void testCurrentEntry() {
        AbstractReferenceMap.ReferenceBaseIterator<String, String> iterator = map.new ReferenceMapIterator<>(map);
        iterator.next();
        assertNotNull(iterator.currentEntry());
        iterator.next();
        assertNotNull(iterator.currentEntry());
    }

    @Test
    public void testRemove() {
        AbstractReferenceMap.ReferenceBaseIterator<String, String> iterator = map.new ReferenceMapIterator<>(map);
        iterator.next();
        iterator.remove();
        assertFalse(map.containsKey("key1"));
        try {
            iterator.remove();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModification() {
        AbstractReferenceMap.ReferenceBaseIterator<String, String> iterator = map.new ReferenceMapIterator<>(map);
        map.put("key3", "value3");
        iterator.next();
    }
}