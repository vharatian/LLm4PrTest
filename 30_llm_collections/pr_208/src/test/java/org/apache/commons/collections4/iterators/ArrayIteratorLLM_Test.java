package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIteratorLLM_Test<E> extends AbstractIteratorTest<E> {

    protected String[] testArray = { "One", "Two", "Three" };

    public ArrayIteratorTest2(final String testName) {
        super(testName);
    }

    @Override
    public ArrayIterator<E> makeEmptyIterator() {
        return new ArrayIterator<>(new Object[0]);
    }

    @Override
    public ArrayIterator<E> makeObject() {
        return new ArrayIterator<>(testArray);
    }

    @Override
    public boolean supportsRemove() {
        return false;
    }

    /**
     * Test to ensure that the index is correctly initialized to startIndex
     * when the ArrayIterator is created.
     */
    public void testIndexInitialization() {
        final int startIndex = 1;
        final ArrayIterator<E> iter = new ArrayIterator<>(testArray, startIndex);
        assertEquals("Index should be initialized to startIndex", startIndex, iter.index);
    }
}