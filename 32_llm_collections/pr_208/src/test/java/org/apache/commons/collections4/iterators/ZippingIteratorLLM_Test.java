package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import org.apache.commons.collections4.IteratorUtils;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("boxing")
public class ZippingIteratorLLM_Test extends AbstractIteratorTest<Integer> {

    public ZippingIteratorTest2(final String testName) {
        super(testName);
    }

    private ArrayList<Integer> evens = null;
    private ArrayList<Integer> odds = null;
    private ArrayList<Integer> fib = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
        fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);
        fib.add(2);
        fib.add(3);
        fib.add(5);
        fib.add(8);
        fib.add(13);
        fib.add(21);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ZippingIterator<Integer> makeEmptyIterator() {
        return new ZippingIterator<>(IteratorUtils.<Integer>emptyIterator());
    }

    @Override
    public ZippingIterator<Integer> makeObject() {
        return new ZippingIterator<>(evens.iterator(), odds.iterator(), fib.iterator());
    }

    @Test
    public void testNextIteratorInitialization() {
        ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        assertNotNull(iter);
        assertNull(iter.nextIterator);
        assertNull(iter.lastReturned);
    }

    @Test
    public void testLastReturnedInitialization() {
        ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        assertNotNull(iter);
        assertNull(iter.nextIterator);
        assertNull(iter.lastReturned);
    }
}