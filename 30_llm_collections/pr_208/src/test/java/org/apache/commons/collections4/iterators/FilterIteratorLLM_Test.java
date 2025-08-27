package org.apache.commons.collections4.iterators;

import static org.apache.commons.collections4.functors.TruePredicate.truePredicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilterIteratorLLM_Test<E> extends AbstractIteratorTest<E> {

    public FilterIteratorTest2(final String name) {
        super(name);
    }

    private String[] array;
    private List<E> list;
    private FilterIterator<E> iterator;

    @Before
    public void setUp() {
        array = new String[] { "a", "b", "c" };
        initIterator();
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
    public void testNextObjectSetInitialization() {
        FilterIterator<E> filterIterator = new FilterIterator<>();
        assertFalse("nextObjectSet should be false upon initialization", filterIterator.hasNext());
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