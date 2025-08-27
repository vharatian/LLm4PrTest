package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListIteratorWrapperLLM_Test<E> extends AbstractIteratorTest<E> {

    protected String[] testArray = {
        "One", "Two", "Three", "Four", "Five", "Six"
    };

    protected List<E> list1 = null;

    public ListIteratorWrapperTest2(final String testName) {
        super(testName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setUp() {
        list1 = new ArrayList<>();
        list1.add((E) "One");
        list1.add((E) "Two");
        list1.add((E) "Three");
        list1.add((E) "Four");
        list1.add((E) "Five");
        list1.add((E) "Six");
    }

    @Override
    public ResettableListIterator<E> makeEmptyIterator() {
        final ArrayList<E> list = new ArrayList<>();
        return new ListIteratorWrapper<>(list.iterator());
    }

    @Override
    public ResettableListIterator<E> makeObject() {
        return new ListIteratorWrapper<>(list1.iterator());
    }

    @Test
    public void testInitialCurrentIndex() {
        final ListIterator<E> iter = makeObject();
        assertEquals("Initial currentIndex should be 0", 0, ((ListIteratorWrapper<E>) iter).currentIndex);
    }

    @Test
    public void testInitialWrappedIteratorIndex() {
        final ListIterator<E> iter = makeObject();
        assertEquals("Initial wrappedIteratorIndex should be 0", 0, ((ListIteratorWrapper<E>) iter).wrappedIteratorIndex);
    }
}