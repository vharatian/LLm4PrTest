package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class SingletonIteratorLLM_Test<E> extends AbstractIteratorTest<E> {
    private static final Object testValue = "foo";

    public SingletonIteratorTest2(final String testName) {
        super(testName);
    }

    @Override
    public SingletonIterator<E> makeEmptyIterator() {
        final SingletonIterator<E> iter = makeObject();
        iter.next();
        iter.remove();
        iter.reset();
        return iter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SingletonIterator<E> makeObject() {
        return new SingletonIterator<>((E) testValue);
    }

    @Override
    public boolean supportsRemove() {
        return true;
    }

    @Override
    public boolean supportsEmptyIterator() {
        return true;
    }

    @Test
    public void testIterator() {
        final Iterator<E> iter = makeObject();
        assertTrue("Iterator has a first item", iter.hasNext());
        final E iterValue = iter.next();
        assertEquals("Iteration value is correct", testValue, iterValue);
        assertTrue("Iterator should now be empty", !iter.hasNext());
        try {
            iter.next();
        } catch (final Exception e) {
            assertTrue(
                "NoSuchElementException must be thrown",
                e.getClass().equals(new NoSuchElementException().getClass()));
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSingletonIteratorRemove() {
        final ResettableIterator<E> iter = new SingletonIterator<>((E) "xyzzy");
        assertTrue(iter.hasNext());
        assertEquals("xyzzy", iter.next());
        iter.remove();
        iter.reset();
        assertTrue(!iter.hasNext());
    }

    @Test
    public void testReset() {
        final ResettableIterator<E> it = makeObject();
        assertEquals(true, it.hasNext());
        assertEquals(testValue, it.next());
        assertEquals(false, it.hasNext());
        it.reset();
        assertEquals(true, it.hasNext());
        assertEquals(testValue, it.next());
        assertEquals(false, it.hasNext());
        it.reset();
        it.reset();
        assertEquals(true, it.hasNext());
    }

    // New test to verify the default value of 'removed' field
    @Test
    public void testDefaultRemovedValue() {
        final SingletonIterator<E> iter = makeObject();
        // Verify that 'removed' is false by default
        assertFalse("The 'removed' field should be false by default", iter.removed);
    }
}