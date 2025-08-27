package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;

public class LazyIteratorChainLLM_Test extends AbstractIteratorTest<String> {

    protected String[] testArray = {
        "One", "Two", "Three", "Four", "Five", "Six"
    };

    protected List<String> list1 = null;
    protected List<String> list2 = null;
    protected List<String> list3 = null;

    public LazyIteratorChainTest2(final String testName) {
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
    public LazyIteratorChain<String> makeEmptyIterator() {
        return new LazyIteratorChain<String>() {
            @Override
            protected Iterator<String> nextIterator(final int count) {
                return null;
            }
        };
    }

    @Override
    public LazyIteratorChain<String> makeObject() {
        final LazyIteratorChain<String> chain = new LazyIteratorChain<String>() {
            @Override
            protected Iterator<String> nextIterator(final int count) {
                switch (count) {
                    case 1:
                        return list1.iterator();
                    case 2:
                        return list2.iterator();
                    case 3:
                        return list3.iterator();
                }
                return null;
            }
        };
        return chain;
    }

    public void testInitialValues() {
        final LazyIteratorChain<String> chain = makeObject();
        // Test initial values of the fields
        assertEquals("Initial callCounter should be 0", 0, getFieldValue(chain, "callCounter"));
        assertEquals("Initial chainExhausted should be false", false, getFieldValue(chain, "chainExhausted"));
        assertNull("Initial currentIterator should be null", getFieldValue(chain, "currentIterator"));
        assertNull("Initial lastUsedIterator should be null", getFieldValue(chain, "lastUsedIterator"));
    }

    @SuppressWarnings("unchecked")
    private <T> T getFieldValue(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}