package org.apache.commons.collections4.set;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.Test;
import static org.junit.Assert.*;

public class CompositeSetLLM_Test<E> {

    @Test
    public void testRemoveIf() {
        // Create a CompositeSet with two underlying sets
        Set<E> set1 = new HashSet<>();
        set1.add((E) "1");
        set1.add((E) "2");

        Set<E> set2 = new HashSet<>();
        set2.add((E) "3");
        set2.add((E) "4");

        CompositeSet<E> compositeSet = new CompositeSet<>(set1, set2);

        // Define a predicate to remove elements that are equal to "2" or "3"
        Predicate<E> filter = e -> e.equals("2") || e.equals("3");

        // Apply the removeIf method
        boolean changed = compositeSet.removeIf(filter);

        // Verify that the set has been modified
        assertTrue(changed);

        // Verify that the elements "2" and "3" have been removed
        assertFalse(compositeSet.contains("2"));
        assertFalse(compositeSet.contains("3"));

        // Verify that the elements "1" and "4" are still present
        assertTrue(compositeSet.contains("1"));
        assertTrue(compositeSet.contains("4"));
    }

    @Test
    public void testRemoveIfWithNullPredicate() {
        // Create a CompositeSet with two underlying sets
        Set<E> set1 = new HashSet<>();
        set1.add((E) "1");
        set1.add((E) "2");

        Set<E> set2 = new HashSet<>();
        set2.add((E) "3");
        set2.add((E) "4");

        CompositeSet<E> compositeSet = new CompositeSet<>(set1, set2);

        // Apply the removeIf method with a null predicate
        boolean changed = compositeSet.removeIf(null);

        // Verify that the set has not been modified
        assertFalse(changed);

        // Verify that all elements are still present
        assertTrue(compositeSet.contains("1"));
        assertTrue(compositeSet.contains("2"));
        assertTrue(compositeSet.contains("3"));
        assertTrue(compositeSet.contains("4"));
    }
}