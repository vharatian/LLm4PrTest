package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;
import org.junit.Test;
import static org.junit.Assert.*;

public class IteratorChainLLM_Test extends AbstractIteratorTest<String> {

    protected String[] testArray = {
        "One", "Two", "Three", "Four", "Five", "Six"
    };

    protected List<String> list1 = null;
    protected List<String> list2 = null;
    protected List<String> list3 = null;

    public IteratorChainTest2(final String testName) {
        super(testName);
    }

    @Override
    public void setUp() {
        list1 = new ArrayList<>();
        list1.add("One");
        list1.add("Two");
        list1.add("Three");
        list2 = new ArrayList<>();
        list2.add("Four");
        list3 = new ArrayList<>();
        list3.add("Five");
        list3.add("Six");
    }

    @Override
    public IteratorChain<String> makeEmptyIterator() {
        final ArrayList<String> list = new ArrayList<>();
        return new IteratorChain<>(list.iterator());
    }

    @Override
    public IteratorChain<String> makeObject() {
        final IteratorChain<String> chain = new IteratorChain<>();
        chain.addIterator(list1.iterator());
        chain.addIterator(list2.iterator());
        chain.addIterator(list3.iterator());
        return chain;
    }

    @Test
    public void testIteratorInitialization() {
        IteratorChain<String> chain = new IteratorChain<>();
        assertNull("Current iterator should be null initially", chain.currentIterator);
        assertNull("Last used iterator should be null initially", chain.lastUsedIterator);
        assertFalse("Chain should not be locked initially", chain.isLocked);
    }

    @Test
    public void testIteratorLocking() {
        IteratorChain<String> chain = makeObject();
        assertFalse("Chain should not be locked initially", chain.isLocked());
        chain.hasNext();
        assertTrue("Chain should be locked after calling hasNext()", chain.isLocked());
    }

    @Test
    public void testIteratorLockingAfterNext() {
        IteratorChain<String> chain = makeObject();
        assertFalse("Chain should not be locked initially", chain.isLocked());
        chain.next();
        assertTrue("Chain should be locked after calling next()", chain.isLocked());
    }

    @Test
    public void testIteratorLockingAfterRemove() {
        IteratorChain<String> chain = makeObject();
        chain.next();
        assertFalse("Chain should not be locked initially", chain.isLocked());
        chain.remove();
        assertTrue("Chain should be locked after calling remove()", chain.isLocked());
    }
}