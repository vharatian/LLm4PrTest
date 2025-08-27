package org.apache.commons.collections4;

import org.junit.Test;
import static org.junit.Assert.*;

public class MultiSetLLM_Test {

    @Test
    public void testAddSingleOccurrence() {
        MultiSet<String> multiSet = new HashMultiSet<>();
        multiSet.add("apple");
        assertEquals(1, multiSet.getCount("apple"));
    }

    @Test
    public void testAddMultipleOccurrences() {
        MultiSet<String> multiSet = new HashMultiSet<>();
        multiSet.add("apple", 3);
        assertEquals(3, multiSet.getCount("apple"));
    }

    @Test
    public void testAddSingleOccurrenceExistingElement() {
        MultiSet<String> multiSet = new HashMultiSet<>();
        multiSet.add("apple");
        multiSet.add("apple");
        assertEquals(2, multiSet.getCount("apple"));
    }

    @Test
    public void testAddMultipleOccurrencesExistingElement() {
        MultiSet<String> multiSet = new HashMultiSet<>();
        multiSet.add("apple", 2);
        multiSet.add("apple", 3);
        assertEquals(5, multiSet.getCount("apple"));
    }
}