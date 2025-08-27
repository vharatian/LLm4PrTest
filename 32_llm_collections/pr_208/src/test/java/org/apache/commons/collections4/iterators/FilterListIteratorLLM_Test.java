package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.Predicate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilterListIteratorLLM_Test {

    private FilterListIterator<Integer> filterListIterator;
    private Predicate<Integer> truePred;
    private Predicate<Integer> falsePred;

    @Before
    public void setUp() {
        truePred = x -> true;
        falsePred = x -> false;
    }

    @Test
    public void testDefaultInitialization() {
        filterListIterator = new FilterListIterator<>();
        assertFalse(filterListIterator.hasNext());
        assertFalse(filterListIterator.hasPrevious());
        assertEquals(0, filterListIterator.nextIndex());
        assertEquals(-1, filterListIterator.previousIndex());
    }

    @Test
    public void testInitializationWithPredicate() {
        filterListIterator = new FilterListIterator<>(truePred);
        assertFalse(filterListIterator.hasNext());
        assertFalse(filterListIterator.hasPrevious());
        assertEquals(0, filterListIterator.nextIndex());
        assertEquals(-1, filterListIterator.previousIndex());
    }

    @Test
    public void testInitializationWithIteratorAndPredicate() {
        filterListIterator = new FilterListIterator<>(null, truePred);
        assertFalse(filterListIterator.hasNext());
        assertFalse(filterListIterator.hasPrevious());
        assertEquals(0, filterListIterator.nextIndex());
        assertEquals(-1, filterListIterator.previousIndex());
    }

    @Test
    public void testInitializationWithIteratorOnly() {
        filterListIterator = new FilterListIterator<>(null);
        assertFalse(filterListIterator.hasNext());
        assertFalse(filterListIterator.hasPrevious());
        assertEquals(0, filterListIterator.nextIndex());
        assertEquals(-1, filterListIterator.previousIndex());
    }
}