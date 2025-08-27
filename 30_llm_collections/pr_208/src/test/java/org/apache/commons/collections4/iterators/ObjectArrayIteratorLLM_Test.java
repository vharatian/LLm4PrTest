package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObjectArrayIteratorLLM_Test<E> extends AbstractIteratorTest<E> {

    protected String[] testArray = { "One", "Two", "Three" };

    public ObjectArrayIteratorTest2(final String testName) {
        super(testName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObjectArrayIterator<E> makeEmptyIterator() {
        return new ObjectArrayIterator<>((E[]) new Object[0]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObjectArrayIterator<E> makeObject() {
        return new ObjectArrayIterator<>((E[]) testArray);
    }

    @SuppressWarnings("unchecked")
    public ObjectArrayIterator<E> makeArrayIterator() {
        return new ObjectArrayIterator<>();
    }

    public ObjectArrayIterator<E> makeArrayIterator(final E[] array) {
        return new ObjectArrayIterator<>(array);
    }

    public ObjectArrayIterator<E> makeArrayIterator(final E[] array, final int index) {
        return new ObjectArrayIterator<>(array, index);
    }

    public ObjectArrayIterator<E> makeArrayIterator(final E[] array, final int start, final int end) {
        return new ObjectArrayIterator<>(array, start, end);
    }

    @Override
    public boolean supportsRemove() {
        return false;
    }

    @Test
    public void testIterator() {
        final Iterator<E> iter = makeObject();
        for (final String testValue : testArray) {
            final E iterValue = iter.next();
            assertEquals("Iteration value is correct", testValue, iterValue);
        }
        assertTrue("Iterator should now be empty", !iter.hasNext());
        try {
            iter.next();
        } catch (final Exception e) {
            assertTrue(
                "NoSuchElementException must be thrown",
                e.getClass().equals(new NoSuchElementException().getClass()));
        }
    }

    @Test
    public void testNullArray() {
        try {
            makeArrayIterator(null);
            fail("Constructor should throw a NullPointerException when constructed with a null array");
        } catch (final NullPointerException e) {
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testReset() {
        final ObjectArrayIterator<E> it = makeArrayIterator((E[]) testArray);
        it.next();
        it.reset();
        assertEquals("One", it.next());
    }

    // New test to ensure the index is correctly initialized
    @Test
    public void testIndexInitialization() {
        final ObjectArrayIterator<E> it = makeArrayIterator((E[]) testArray);
        assertEquals("Index should be initialized to startIndex", 0, it.index);
    }
}