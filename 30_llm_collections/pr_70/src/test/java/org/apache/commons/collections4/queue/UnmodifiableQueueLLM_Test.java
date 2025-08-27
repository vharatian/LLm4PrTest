package org.apache.commons.collections4.queue;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.AbstractCollectionTest;

public class UnmodifiableQueueLLM_Test<E> extends AbstractQueueTest<E> {

    public UnmodifiableQueueTest2(final String testName) {
        super(testName);
    }

    @Override
    public Queue<E> makeObject() {
        return UnmodifiableQueue.unmodifiableQueue(new LinkedList<E>());
    }

    @Override
    public Queue<E> makeFullCollection() {
        final Queue<E> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(getFullElements()));
        return UnmodifiableQueue.unmodifiableQueue(queue);
    }

    @Override
    public Collection<E> makeConfirmedCollection() {
        return new LinkedList<>();
    }

    @Override
    public Collection<E> makeConfirmedFullCollection() {
        final LinkedList<E> list = new LinkedList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return list;
    }

    @Override
    public Queue<E> getCollection() {
        return super.getCollection();
    }

    @Override
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    @Override
    public boolean isNullSupported() {
        return false;
    }

    @Override
    public void testQueueRemove() {
        resetEmpty();
        try {
            getCollection().remove();
            fail();
        } catch (final UnsupportedOperationException ex) {}
    }

    public void testUnmodifiable() {
        assertTrue(makeObject() instanceof Unmodifiable);
        assertTrue(makeFullCollection() instanceof Unmodifiable);
    }

    public void testDecorateFactory() {
        final Queue<E> queue = makeFullCollection();
        assertSame(queue, UnmodifiableQueue.unmodifiableQueue(queue));
        try {
            UnmodifiableQueue.unmodifiableQueue(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    /**
     * Test to ensure removeIf throws UnsupportedOperationException
     */
    public void testRemoveIf() {
        final Queue<E> queue = makeFullCollection();
        try {
            queue.removeIf(new Predicate<E>() {
                @Override
                public boolean test(E e) {
                    return true;
                }
            });
            fail();
        } catch (final UnsupportedOperationException ex) {}
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}