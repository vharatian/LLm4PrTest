package org.apache.commons.collections4.iterators;

import static org.apache.commons.collections4.functors.TruePredicate.truePredicate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterIteratorLLM_Test<E> extends AbstractIteratorTest<E> {
    public FilterIteratorTest2() {
        super(FilterIteratorTest2.class.getSimpleName());
    }

    private String[] array;
    private List<E> list;
    private FilterIterator<E> iterator;

    @BeforeEach
    public void setUp() {
        array = new String[] { "a", "b", "c" };
        initIterator();
    }

    @Override
    public void tearDown() throws Exception {
        iterator = null;
    }

    @Override
    public FilterIterator<E> makeEmptyIterator() {
        return makeBlockAllFilter(new ArrayIterator<E>(array));
    }

    @Override
    @SuppressWarnings("unchecked")
    public FilterIterator<E> makeObject() {
        list = new ArrayList<>(Arrays.asList((E[]) array));
        return makePassThroughFilter(list.iterator());
    }

    @Test
    public void testSetNextObject() {
        // Initialize iterator with a predicate that filters out "b"
        final Predicate<E> pred = x -> !"b".equals(x);
        iterator.setPredicate(pred);

        // Check that the iterator correctly skips "b"
        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("c", iterator.next());
        assertFalse(iterator.hasNext());
    }

    private void verifyNoMoreElements() {
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    private void verifyElementsInPredicate(final String[] elements) {
        final Predicate<E> pred = x -> {
            for (final String element : elements) {
                if (element.equals(x)) {
                    return true;
                }
            }
            return false;
        };
        initIterator();
        iterator.setPredicate(pred);
        for (int i = 0; i < elements.length; i++) {
            final String s = (String) iterator.next();
            assertEquals(elements[i], s);
            assertTrue(i == elements.length - 1 ? !iterator.hasNext() : iterator.hasNext());
        }
        verifyNoMoreElements();
        initIterator();
        iterator.setPredicate(pred);
        if (iterator.hasNext()) {
            final Object last = iterator.next();
            iterator.remove();
            assertFalse("Base of FilterIterator still contains removed element.", list.contains(last));
        }
    }

    private void initIterator() {
        iterator = makeObject();
    }

    protected FilterIterator<E> makePassThroughFilter(final Iterator<E> i) {
        final Predicate<E> pred = x -> true;
        return new FilterIterator<>(i, pred);
    }

    protected FilterIterator<E> makeBlockAllFilter(final Iterator<E> i) {
        final Predicate<E> pred = x -> false;
        return new FilterIterator<>(i, pred);
    }
}