package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Transformer;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObjectGraphIteratorLLM_Test {

    @Test
    public void testHasNextInitialization() {
        // Test the initialization of hasNext without explicitly setting it to false
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        Iterator<String> iterator = list.iterator();
        ObjectGraphIterator<String> objectGraphIterator = new ObjectGraphIterator<>(iterator);

        assertTrue(objectGraphIterator.hasNext());
        assertEquals("One", objectGraphIterator.next());
        assertTrue(objectGraphIterator.hasNext());
        assertEquals("Two", objectGraphIterator.next());
        assertFalse(objectGraphIterator.hasNext());
    }

    @Test
    public void testHasNextWithEmptyIterator() {
        // Test the hasNext method when the iterator is empty
        List<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        ObjectGraphIterator<String> objectGraphIterator = new ObjectGraphIterator<>(iterator);

        assertFalse(objectGraphIterator.hasNext());
    }

    @Test
    public void testNextWithEmptyIterator() {
        // Test the next method when the iterator is empty
        List<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        ObjectGraphIterator<String> objectGraphIterator = new ObjectGraphIterator<>(iterator);

        try {
            objectGraphIterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
    }

    @Test
    public void testRemoveWithoutNext() {
        // Test the remove method without calling next
        List<String> list = new ArrayList<>();
        list.add("One");
        Iterator<String> iterator = list.iterator();
        ObjectGraphIterator<String> objectGraphIterator = new ObjectGraphIterator<>(iterator);

        try {
            objectGraphIterator.remove();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected exception
        }
    }
}