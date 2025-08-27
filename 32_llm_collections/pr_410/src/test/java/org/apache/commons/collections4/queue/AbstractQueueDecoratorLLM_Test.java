package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.Queue;

public class AbstractQueueDecoratorLLM_Test {

    private static class TestQueueDecorator<E> extends AbstractQueueDecorator<E> {
        private static final long serialVersionUID = 1L;

        protected TestQueueDecorator(Queue<E> queue) {
            super(queue);
        }
    }

    @Test
    public void testOffer() {
        Queue<Integer> queue = new LinkedList<>();
        AbstractQueueDecorator<Integer> decorator = new TestQueueDecorator<>(queue);
        assertTrue(decorator.offer(1));
        assertEquals(1, queue.size());
    }

    @Test
    public void testPoll() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        AbstractQueueDecorator<Integer> decorator = new TestQueueDecorator<>(queue);
        assertEquals(1, decorator.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPeek() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        AbstractQueueDecorator<Integer> decorator = new TestQueueDecorator<>(queue);
        assertEquals(1, decorator.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testElement() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        AbstractQueueDecorator<Integer> decorator = new TestQueueDecorator<>(queue);
        assertEquals(1, decorator.element());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testRemove() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        AbstractQueueDecorator<Integer> decorator = new TestQueueDecorator<>(queue);
        assertEquals(1, decorator.remove());
        assertTrue(queue.isEmpty());
    }
}