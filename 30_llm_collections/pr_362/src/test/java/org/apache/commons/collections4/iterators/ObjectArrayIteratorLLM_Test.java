package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

public class ObjectArrayIteratorLLM_Test<E> extends AbstractIteratorTest<E> {
    protected String[] testArray = { "One", "Two", "Three" };

    public ObjectArrayIteratorTest2() {
        super(ObjectArrayIteratorTest2.class.getSimpleName());
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
        assertFalse("Iterator should now be empty", iter.hasNext());
        try {
            iter.next();
        } catch (final Exception e) {
            assertEquals("NoSuchElementException must be thrown", e.getClass(), new NoSuchElementException().getClass());
        }
    }

    @Test
    public void testNullArray() {
        assertThrows(NullPointerException.class, () -> makeArrayIterator(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testReset() {
        final ObjectArrayIterator<E> it = makeArrayIterator((E[]) testArray);
        it.next();
        it.reset();
        assertEquals("One", it.next());
    }

    @Test
    public void testConstructorWithStartIndex() {
        final ObjectArrayIterator<E> it = makeArrayIterator((E[]) testArray, 1);
        assertEquals("Two", it.next());
    }

    @Test
    public void testConstructorWithStartAndEndIndex() {
        final ObjectArrayIterator<E> it = makeArrayIterator((E[]) testArray, 1, 2);
        assertEquals("Two", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testConstructorWithInvalidStartIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> makeArrayIterator((E[]) testArray, -1));
    }

    @Test
    public void testConstructorWithInvalidEndIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> makeArrayIterator((E[]) testArray, 0, 4));
    }

    @Test
    public void testConstructorWithEndIndexBeforeStartIndex() {
        assertThrows(IllegalArgumentException.class, () -> makeArrayIterator((E[]) testArray, 2, 1));
    }
}