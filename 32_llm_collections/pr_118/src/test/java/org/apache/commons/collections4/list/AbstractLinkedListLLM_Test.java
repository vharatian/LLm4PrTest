package org.apache.commons.collections4.list;

import java.util.Arrays;
import java.util.Objects;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractLinkedListLLM_Test<E> extends AbstractLinkedListTest<E> {

    public AbstractLinkedListTest2(final String testName) {
        super(testName);
    }

    @Test
    public void testEqualsWithObjects() {
        AbstractLinkedList<String> list1 = new AbstractLinkedList<String>() {};
        AbstractLinkedList<String> list2 = new AbstractLinkedList<String>() {};
        list1.addAll(Arrays.asList("value1", "value2"));
        list2.addAll(Arrays.asList("value1", "value2"));
        assertTrue(list1.equals(list2));
        list2.add("value3");
        assertFalse(list1.equals(list2));
    }

    @Test
    public void testIsEqualValueWithObjects() {
        AbstractLinkedList<String> list = new AbstractLinkedList<String>() {};
        assertTrue(list.isEqualValue("value1", "value1"));
        assertFalse(list.isEqualValue("value1", "value2"));
        assertTrue(list.isEqualValue(null, null));
        assertFalse(list.isEqualValue("value1", null));
        assertFalse(list.isEqualValue(null, "value2"));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNodeWithNullNodeToInsert() {
        AbstractLinkedList<String> list = new AbstractLinkedList<String>() {};
        list.addNode(null, list.createHeaderNode());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNodeWithNullInsertBeforeNode() {
        AbstractLinkedList<String> list = new AbstractLinkedList<String>() {};
        list.addNode(list.createHeaderNode(), null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNodeWithNullNode() {
        AbstractLinkedList<String> list = new AbstractLinkedList<String>() {};
        list.removeNode(null);
    }

    @Test
    public void testConcurrentModificationException() {
        AbstractLinkedList<String> list = new AbstractLinkedList<String>() {};
        list.addAll(Arrays.asList("value1", "value2"));
        Iterator<String> iterator = list.iterator();
        list.add("value3");
        try {
            iterator.next();
            fail("Expected ConcurrentModificationException");
        } catch (ConcurrentModificationException e) {
            // expected
        }
    }

    @Test
    public void testNoSuchElementException() {
        AbstractLinkedList<String> list = new AbstractLinkedList<String>() {};
        ListIterator<String> iterator = list.listIterator();
        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // expected
        }
    }
}