package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeekingIteratorLLM_Test<E> extends AbstractIteratorTest<E> {
    private final String[] testArray = { "a", "b", "c" };
    private List<E> testList;

    public PeekingIteratorTest2(final String testName) {
        super(testName);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testList = new ArrayList<>(Arrays.asList((E[]) testArray));
    }

    @Override
    public Iterator<E> makeEmptyIterator() {
        return PeekingIterator.peekingIterator(Collections.<E>emptyList().iterator());
    }

    @Override
    public PeekingIterator<E> makeObject() {
        return PeekingIterator.peekingIterator(testList.iterator());
    }

    @Override
    public boolean supportsRemove() {
        return true;
    }

    /**
     * Test to ensure hasNext() works correctly when slotFilled is false and iterator has next element.
     */
    @Test
    public void testHasNextWithSlotNotFilled() {
        final PeekingIterator<E> it = makeObject();
        it.next(); // Move to "b"
        assertTrue(it.hasNext()); // Should be true as "c" is next
    }

    /**
     * Test to ensure hasNext() works correctly when slotFilled is true.
     */
    @Test
    public void testHasNextWithSlotFilled() {
        final PeekingIterator<E> it = makeObject();
        it.peek(); // Peek "a", slotFilled should be true
        assertTrue(it.hasNext()); // Should be true as slot is filled
    }

    /**
     * Test to ensure hasNext() works correctly when iterator is exhausted.
     */
    @Test
    public void testHasNextWhenExhausted() {
        final PeekingIterator<E> it = makeObject();
        it.next(); // "a"
        it.next(); // "b"
        it.next(); // "c"
        assertFalse(it.hasNext()); // Should be false as iterator is exhausted
    }
}