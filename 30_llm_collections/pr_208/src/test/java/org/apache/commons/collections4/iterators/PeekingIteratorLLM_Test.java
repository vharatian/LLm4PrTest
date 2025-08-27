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

    @Test
    public void testInitialState() {
        final PeekingIterator<E> it = makeObject();
        assertFalse(it.exhausted);
        assertFalse(it.slotFilled);
    }

    @Test
    public void testInitialStateEmptyIterator() {
        final PeekingIterator<E> it = (PeekingIterator<E>) makeEmptyIterator();
        assertFalse(it.exhausted);
        assertFalse(it.slotFilled);
    }
}