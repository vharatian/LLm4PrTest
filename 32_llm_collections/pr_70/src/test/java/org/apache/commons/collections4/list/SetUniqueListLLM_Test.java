package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import junit.framework.TestCase;

public class SetUniqueListLLM_Test<E> extends TestCase {

    public void testRemoveIf() {
        // Create a SetUniqueList with some initial elements
        SetUniqueList<Integer> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        uniqueList.add(1);
        uniqueList.add(2);
        uniqueList.add(3);
        uniqueList.add(4);
        uniqueList.add(5);

        // Define a predicate to remove even numbers
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Apply removeIf with the predicate
        boolean result = uniqueList.removeIf(isEven);

        // Check if the removeIf operation was successful
        assertTrue(result);

        // Verify the remaining elements in the list
        assertEquals(3, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(3));
        assertTrue(uniqueList.contains(5));
        assertFalse(uniqueList.contains(2));
        assertFalse(uniqueList.contains(4));
    }

    public void testRemoveIfNoMatch() {
        // Create a SetUniqueList with some initial elements
        SetUniqueList<Integer> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        uniqueList.add(1);
        uniqueList.add(2);
        uniqueList.add(3);

        // Define a predicate that does not match any elements
        Predicate<Integer> greaterThanThree = num -> num > 3;

        // Apply removeIf with the predicate
        boolean result = uniqueList.removeIf(greaterThanThree);

        // Check if the removeIf operation was unsuccessful
        assertFalse(result);

        // Verify the elements in the list remain unchanged
        assertEquals(3, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(2));
        assertTrue(uniqueList.contains(3));
    }

    public void testRemoveIfAllMatch() {
        // Create a SetUniqueList with some initial elements
        SetUniqueList<Integer> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        uniqueList.add(1);
        uniqueList.add(2);
        uniqueList.add(3);

        // Define a predicate that matches all elements
        Predicate<Integer> lessThanFour = num -> num < 4;

        // Apply removeIf with the predicate
        boolean result = uniqueList.removeIf(lessThanFour);

        // Check if the removeIf operation was successful
        assertTrue(result);

        // Verify the list is empty
        assertEquals(0, uniqueList.size());
    }
}