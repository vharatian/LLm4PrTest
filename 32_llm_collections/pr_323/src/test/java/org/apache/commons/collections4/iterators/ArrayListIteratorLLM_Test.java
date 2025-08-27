package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ArrayListIteratorLLM_Test<E> extends ArrayIteratorTest<E> {
    public ArrayListIteratorTest2() {
        super();
    }

    @Override
    public ArrayListIterator<E> makeEmptyIterator() {
        return new ArrayListIterator<>(new Object[0]);
    }

    @Override
    public ArrayListIterator<E> makeObject() {
        return new ArrayListIterator<>(testArray);
    }

    public ArrayListIterator<E> makeArrayListIterator(final Object array) {
        return new ArrayListIterator<>(array);
    }

    @Override
    public boolean supportsRemove() {
        return false;
    }

    @Test
    public void testListIterator() {
        final ListIterator<E> iter = makeObject();
        while (iter.hasNext()) {
            iter.next();
        }
        for (int x = testArray.length - 1; x >= 0; x--) {
            final Object testValue = testArray[x];
            final Object iterValue = iter.previous();
            assertEquals("Iteration value is correct", testValue, iterValue);
        }
        assertFalse("Iterator should now be empty", iter.hasPrevious());
        try {
            iter.previous();
        } catch (final Exception e) {
            assertEquals("NoSuchElementException must be thrown", e.getClass(), new NoSuchElementException().getClass());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testListIteratorSet() {
        final String[] testData = { "a", "b", "c" };
        final String[] result = { "0", "1", "2" };
        ListIterator<E> iter = makeArrayListIterator(testData);
        int x = 0;
        while (iter.hasNext()) {
            iter.next();
            iter.set((E) Integer.toString(x));
            x++;
        }
        assertArrayEquals(testData, result, "The two arrays should have the same value, i.e. {0,1,2}");
        iter = makeArrayListIterator(testArray);
        final ListIterator<E> finalIter = iter;
        assertThrows(IllegalStateException.class, () -> finalIter.set((E) "should fail"), "ListIterator#set should fail if next() or previous() have not yet been called.");
    }
}