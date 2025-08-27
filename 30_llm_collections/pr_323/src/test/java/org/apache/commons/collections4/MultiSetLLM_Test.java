package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultiSetLLM_Test {

    @Test
    public void testRemoveOccurrenceReducesToZero() {
        MultiSet<String> multiSet = new HashMultiSet<>();
        multiSet.add("apple", 3);
        assertEquals(3, multiSet.getCount("apple"));
        
        multiSet.remove("apple", 3);
        assertEquals(0, multiSet.getCount("apple"));
        assertFalse(multiSet.uniqueSet().contains("apple"));
    }

    @Test
    public void testEntrySetImplementsEntryInterface() {
        MultiSet<String> multiSet = new HashMultiSet<>();
        multiSet.add("banana", 2);
        Set<MultiSet.Entry<String>> entrySet = multiSet.entrySet();
        
        for (MultiSet.Entry<String> entry : entrySet) {
            assertTrue(entry instanceof MultiSet.Entry);
            assertEquals("banana", entry.getElement());
            assertEquals(2, entry.getCount());
        }
    }
}