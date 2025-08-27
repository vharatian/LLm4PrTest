package org.apache.commons.collections4.iterators;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class EntrySetMapIteratorLLM_Test {

    private Map<String, String> map;
    private EntrySetMapIterator<String, String> iterator;

    @Before
    public void setUp() {
        map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        iterator = new EntrySetMapIterator<>(map);
    }

    @Test
    public void testConstructorInitializesCanRemoveToFalse() {
        // The diff file indicates that the canRemove field should be initialized to false
        assertFalse("canRemove should be initialized to false", getCanRemove(iterator));
    }

    @Test
    public void testNextSetsCanRemoveToTrue() {
        iterator.next();
        assertTrue("canRemove should be true after calling next()", getCanRemove(iterator));
    }

    @Test
    public void testRemoveSetsCanRemoveToFalse() {
        iterator.next();
        iterator.remove();
        assertFalse("canRemove should be false after calling remove()", getCanRemove(iterator));
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNextThrowsException() {
        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testGetKeyWithoutNextThrowsException() {
        iterator.getKey();
    }

    @Test(expected = IllegalStateException.class)
    public void testGetValueWithoutNextThrowsException() {
        iterator.getValue();
    }

    @Test(expected = IllegalStateException.class)
    public void testSetValueWithoutNextThrowsException() {
        iterator.setValue("newValue");
    }

    @Test
    public void testResetResetsIterator() {
        iterator.next();
        iterator.reset();
        assertFalse("canRemove should be false after reset", getCanRemove(iterator));
        assertTrue("Iterator should have next after reset", iterator.hasNext());
    }

    // Helper method to access the private canRemove field using reflection
    private boolean getCanRemove(EntrySetMapIterator<String, String> iterator) {
        try {
            java.lang.reflect.Field field = EntrySetMapIterator.class.getDeclaredField("canRemove");
            field.setAccessible(true);
            return field.getBoolean(iterator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}