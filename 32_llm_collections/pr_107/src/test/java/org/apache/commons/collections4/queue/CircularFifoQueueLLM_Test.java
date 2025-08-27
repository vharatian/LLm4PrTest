package org.apache.commons.collections4.queue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CircularFifoQueueLLM_Test<E> extends AbstractQueueTest<E> {

    public CircularFifoQueueTest2(final String testName) {
        super(testName);
    }

    @Override
    public void verify() {
        super.verify();
        final Iterator<E> iterator1 = getCollection().iterator();
        for (final E e : getConfirmed()) {
            assertTrue(iterator1.hasNext());
            final Object o1 = iterator1.next();
            final Object o2 = e;
            assertEquals(o1, o2);
        }
    }

    @Override
    public boolean isNullSupported() {
        return false;
    }

    @Override
    public boolean isFailFastSupported() {
        return false;
    }

    @Override
    public Collection<E> makeConfirmedCollection() {
        return new ArrayList<>();
    }

    @Override
    public Collection<E> makeConfirmedFullCollection() {
        final Collection<E> c = makeConfirmedCollection();
        c.addAll(java.util.Arrays.asList(getFullElements()));
        return c;
    }

    @Override
    public Queue<E> makeObject() {
        return new CircularFifoQueue<>(100);
    }

    public void testGetIndexErrorMessage() {
        final CircularFifoQueue<E> queue = new CircularFifoQueue<>(5);
        queue.add((E) "1");
        queue.add((E) "2");
        queue.add((E) "3");
        queue.add((E) "4");
        queue.add((E) "5");

        try {
            queue.get(5);
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException ex) {
            assertEquals("The specified index 5 is outside the available range [0, 5)", ex.getMessage());
        }

        try {
            queue.get(-1);
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException ex) {
            assertEquals("The specified index -1 is outside the available range [0, 5)", ex.getMessage());
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    public CircularFifoQueue<E> getCollection() {
        return (CircularFifoQueue<E>) super.getCollection();
    }
}