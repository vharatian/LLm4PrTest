package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CursorableLinkedListLLM_Test<E> extends AbstractLinkedListTest<E> {

    public CursorableLinkedListTest2() {
        super(CursorableLinkedListTest2.class.getSimpleName());
    }

    private CursorableLinkedList<E> list;

    @BeforeEach
    public void setUp() {
        list = new CursorableLinkedList<>();
    }

    @Override
    public CursorableLinkedList<E> makeObject() {
        return new CursorableLinkedList<>();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUnregisterCursorCleanup() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        CursorableLinkedList.Cursor<E> cursor1 = list.cursor();
        CursorableLinkedList.Cursor<E> cursor2 = list.cursor();
        CursorableLinkedList.Cursor<E> cursor3 = list.cursor();

        cursor1.close();
        cursor2.close();

        // Force garbage collection
        System.gc();

        // Ensure cursor3 is still valid
        assertEquals("A", cursor3.next());

        // Register a new cursor and ensure the list is cleaned up
        CursorableLinkedList.Cursor<E> cursor4 = list.cursor();
        assertEquals("A", cursor4.next());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testConcurrentModificationExceptionOnClosedCursor() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        CursorableLinkedList.Cursor<E> cursor = list.cursor();
        cursor.close();

        assertThrows(ConcurrentModificationException.class, cursor::next);
    }
}