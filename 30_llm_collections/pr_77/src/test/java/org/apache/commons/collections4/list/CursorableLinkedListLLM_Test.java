package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakReference;
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

    /**
     * Test the modified registerCursor method to ensure it correctly removes
     * garbage-collected cursors using the new removeIf method.
     */
    @SuppressWarnings("unchecked")
    public void testRegisterCursor() {
        // Create a cursor and register it
        CursorableLinkedList.Cursor<E> cursor1 = list.cursor();
        assertEquals(1, list.cursors.size());

        // Create another cursor and register it
        CursorableLinkedList.Cursor<E> cursor2 = list.cursor();
        assertEquals(2, list.cursors.size());

        // Manually clear the first cursor to simulate garbage collection
        cursor1.close();
        assertFalse(cursor1.valid);

        // Register a new cursor and check if the closed cursor is removed
        CursorableLinkedList.Cursor<E> cursor3 = list.cursor();
        assertEquals(2, list.cursors.size()); // cursor1 should be removed, cursor2 and cursor3 should remain

        // Verify that the remaining cursors are the correct ones
        List<CursorableLinkedList.Cursor<E>> activeCursors = new ArrayList<>();
        for (WeakReference<CursorableLinkedList.Cursor<E>> ref : list.cursors) {
            CursorableLinkedList.Cursor<E> cursor = ref.get();
            if (cursor != null) {
                activeCursors.add(cursor);
            }
        }
        assertTrue(activeCursors.contains(cursor2));
        assertTrue(activeCursors.contains(cursor3));
    }
}