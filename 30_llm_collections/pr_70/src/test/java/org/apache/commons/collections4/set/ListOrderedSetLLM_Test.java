package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import junit.framework.TestCase;

public class ListOrderedSetLLM_Test<E> extends TestCase {

    private static final Integer ZERO = Integer.valueOf(0);
    private static final Integer ONE = Integer.valueOf(1);
    private static final Integer TWO = Integer.valueOf(2);
    private static final Integer THREE = Integer.valueOf(3);

    public ListOrderedSetTest2(final String testName) {
        super(testName);
    }

    public ListOrderedSet<E> makeObject() {
        return ListOrderedSet.listOrderedSet(new HashSet<E>());
    }

    @SuppressWarnings("unchecked")
    public void testRemoveIf() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);
        set.add((E) THREE);

        Predicate<E> isEven = e -> ((Integer) e) % 2 == 0;

        boolean result = set.removeIf(isEven);
        assertTrue(result);
        assertEquals(2, set.size());
        assertFalse(set.contains(ZERO));
        assertTrue(set.contains(ONE));
        assertFalse(set.contains(TWO));
        assertTrue(set.contains(THREE));

        result = set.removeIf(isEven);
        assertFalse(result);
        assertEquals(2, set.size());
    }

    @SuppressWarnings("unchecked")
    public void testRemoveIfWithNullPredicate() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) ONE);

        boolean result = set.removeIf(null);
        assertFalse(result);
        assertEquals(2, set.size());
        assertTrue(set.contains(ZERO));
        assertTrue(set.contains(ONE));
    }
}