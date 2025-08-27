package org.apache.commons.collections4.list;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractLinkedListLLM_Test<E> extends AbstractLinkedListTest<E> {

    public AbstractLinkedListTest2(final String testName) {
        super(testName);
    }

    @Test
    public void testIndexOfWithCollectionUtils() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, list.indexOf("value1"));
        list.addAll(Arrays.asList((E[]) new String[]{"value1", "value2"}));
        assertEquals(0, list.indexOf("value1"));
        assertEquals(1, list.indexOf("value2"));
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, list.indexOf("value3"));
    }

    @Test
    public void testLastIndexOfWithCollectionUtils() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, list.lastIndexOf("value1"));
        list.addAll(Arrays.asList((E[]) new String[]{"value1", "value2", "value1"}));
        assertEquals(2, list.lastIndexOf("value1"));
        assertEquals(1, list.lastIndexOf("value2"));
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, list.lastIndexOf("value3"));
    }
}