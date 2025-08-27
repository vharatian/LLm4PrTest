package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollatingIteratorLLM_Test extends AbstractIteratorTest<Integer> {

    public CollatingIteratorTest2(final String testName) {
        super(testName);
    }

    private Comparator<Integer> comparator = null;
    private ArrayList<Integer> evens = null;
    private ArrayList<Integer> odds = null;
    private ArrayList<Integer> fib = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
        fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);
        fib.add(2);
        fib.add(3);
        fib.add(5);
        fib.add(8);
        fib.add(13);
        fib.add(21);
    }

    @Override
    public CollatingIterator<Integer> makeEmptyIterator() {
        return new CollatingIterator<>(comparator);
    }

    @Override
    public CollatingIterator<Integer> makeObject() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
        iter.addIterator(fib.iterator());
        return iter;
    }

    @Test
    public void testComparatorInitialization() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>();
        assertNull(iter.getComparator());
    }

    @Test
    public void testIteratorsInitialization() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        assertTrue(iter.getIterators().isEmpty());
    }

    @Test
    public void testValuesInitialization() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
        iter.hasNext(); // Trigger initialization
        assertNotNull(iter.values);
        assertEquals(2, iter.values.size());
    }

    @Test
    public void testValueSetInitialization() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
        iter.hasNext(); // Trigger initialization
        assertNotNull(iter.valueSet);
        assertEquals(2, iter.valueSet.size());
    }
}