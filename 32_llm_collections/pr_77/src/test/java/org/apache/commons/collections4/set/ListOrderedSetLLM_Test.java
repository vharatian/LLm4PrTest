package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.IteratorUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListOrderedSetLLM_Test<E> {

    private static final Integer ZERO = Integer.valueOf(0);
    private static final Integer ONE = Integer.valueOf(1);
    private static final Integer TWO = Integer.valueOf(2);
    private static final Integer THREE = Integer.valueOf(3);

    @Test
    public void testRetainAllWithEmptyDecoratedSet() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);

        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        // Clear the decorated set to simulate the condition where the set is empty
        orderedSet.clear();

        assertFalse(orderedSet.retainAll(retained));
        assertEquals(0, orderedSet.size());
    }

    @Test
    public void testRetainAllWithNonEmptyDecoratedSet() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);

        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        assertTrue(orderedSet.retainAll(retained));
        assertEquals(5, orderedSet.size());
        assertEquals(Integer.valueOf(8), orderedSet.get(0));
        assertEquals(Integer.valueOf(6), orderedSet.get(1));
        assertEquals(Integer.valueOf(4), orderedSet.get(2));
        assertEquals(Integer.valueOf(2), orderedSet.get(3));
        assertEquals(Integer.valueOf(0), orderedSet.get(4));
    }
}