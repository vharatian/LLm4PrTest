package org.apache.commons.collections4.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class CursorableLinkedListLLM_Test<E> extends AbstractLinkedListTest<E> {

    public CursorableLinkedListTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(CursorableLinkedListTest2.class);
    }

    private CursorableLinkedList<E> list;

    @Override
    public void setUp() {
        list = new CursorableLinkedList<>();
    }

    @Override
    public CursorableLinkedList<E> makeObject() {
        return new CursorableLinkedList<>();
    }

    @SuppressWarnings("unchecked")
    public void testCursorCurrentRemovedByAnotherInitialization() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        
        final CursorableLinkedList.Cursor<E> cursor = list.cursor();
        
        // Check initial state of currentRemovedByAnother
        assertFalse(cursor.currentRemovedByAnother);
        
        // Move cursor and remove element using list
        assertEquals("1", cursor.next());
        assertEquals("2", cursor.next());
        list.remove(1); // Remove "2"
        
        // Check state of currentRemovedByAnother after removal
        assertTrue(cursor.currentRemovedByAnother);
        
        // Move cursor and check state again
        assertEquals("3", cursor.next());
        assertFalse(cursor.currentRemovedByAnother);
    }

    @SuppressWarnings("unchecked")
    public void testCursorCurrentRemovedByAnotherAfterRemoval() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        
        final CursorableLinkedList.Cursor<E> cursor = list.cursor();
        
        // Move cursor and remove element using cursor
        assertEquals("1", cursor.next());
        assertEquals("2", cursor.next());
        cursor.remove(); // Remove "2"
        
        // Check state of currentRemovedByAnother after removal
        assertFalse(cursor.currentRemovedByAnother);
        
        // Move cursor and check state again
        assertEquals("3", cursor.next());
        assertFalse(cursor.currentRemovedByAnother);
    }

    @SuppressWarnings("unchecked")
    public void testCursorCurrentRemovedByAnotherAfterAdd() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        
        final CursorableLinkedList.Cursor<E> cursor = list.cursor();
        
        // Move cursor and add element using cursor
        assertEquals("1", cursor.next());
        assertEquals("2", cursor.next());
        cursor.add((E) "2.5"); // Add "2.5"
        
        // Check state of currentRemovedByAnother after add
        assertFalse(cursor.currentRemovedByAnother);
        
        // Move cursor and check state again
        assertEquals("2.5", cursor.previous());
        assertFalse(cursor.currentRemovedByAnother);
    }
}