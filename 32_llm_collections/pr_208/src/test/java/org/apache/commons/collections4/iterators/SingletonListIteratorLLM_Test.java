package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;
import junit.framework.TestCase;

public class SingletonListIteratorLLM_Test<E> extends TestCase {

    private static final Object testValue = "foo";

    public SingletonListIteratorTest2(final String testName) {
        super(testName);
    }

    @SuppressWarnings("unchecked")
    public SingletonListIterator<E> makeObject() {
        return new SingletonListIterator<>((E) testValue);
    }

    public void testInitialState() {
        final SingletonListIterator<E> iter = makeObject();
        assertTrue("Initial state: beforeFirst should be true", iter.hasNext());
        assertFalse("Initial state: nextCalled should be false", iter.hasPrevious());
        assertFalse("Initial state: removed should be false", iter.hasPrevious());
    }

    public void testNextCalledState() {
        final SingletonListIterator<E> iter = makeObject();
        iter.next();
        assertFalse("After next() call: beforeFirst should be false", iter.hasNext());
        assertTrue("After next() call: nextCalled should be true", iter.hasPrevious());
    }

    public void testRemovedState() {
        final SingletonListIterator<E> iter = makeObject();
        iter.next();
        iter.remove();
        assertFalse("After remove() call: beforeFirst should be false", iter.hasNext());
        assertFalse("After remove() call: nextCalled should be false", iter.hasPrevious());
        assertFalse("After remove() call: removed should be true", iter.hasPrevious());
    }

    public void testResetState() {
        final SingletonListIterator<E> iter = makeObject();
        iter.next();
        iter.reset();
        assertTrue("After reset() call: beforeFirst should be true", iter.hasNext());
        assertFalse("After reset() call: nextCalled should be false", iter.hasPrevious());
    }
}