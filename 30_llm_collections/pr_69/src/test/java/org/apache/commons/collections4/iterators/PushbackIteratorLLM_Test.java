package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PushbackIteratorLLM_Test<E> extends AbstractIteratorTest<E> {
    private final String[] testArray = { "a", "b", "c" };
    private List<E> testList;

    public PushbackIteratorTest2(final String testName) {
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
        return PushbackIterator.pushbackIterator(Collections.<E> emptyList().iterator());
    }

    @Override
    public PushbackIterator<E> makeObject() {
        return PushbackIterator.pushbackIterator(testList.iterator());
    }

    @Override
    public boolean supportsRemove() {
        return false;
    }

    @Test
    public void testHasNextWithEmptyPushback() {
        final PushbackIterator<E> iter = makeObject();
        assertTrue(iter.hasNext());
        iter.next();
        iter.next();
        iter.next();
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithNonEmptyPushback() {
        final PushbackIterator<E> iter = makeObject();
        iter.pushback((E) "x");
        assertTrue(iter.hasNext());
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        assertFalse(iter.hasNext());
    }
}