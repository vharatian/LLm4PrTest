package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;

public class TreeListLLM_Test<E> extends AbstractListTest<E> {
    public TreeListTest2() {
        super(TreeListTest2.class.getSimpleName());
    }

    @Override
    public TreeList<E> makeObject() {
        return new TreeList<>();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddAll() {
        final List<E> l = makeObject();
        List<E> elementsToAdd = new ArrayList<>();
        elementsToAdd.add((E) "one");
        elementsToAdd.add((E) "two");
        elementsToAdd.add((E) "three");

        l.addAll(elementsToAdd);

        assertEquals(3, l.size());
        assertEquals("one", l.get(0));
        assertEquals("two", l.get(1));
        assertEquals("three", l.get(2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddAllEmptyCollection() {
        final List<E> l = makeObject();
        List<E> elementsToAdd = new ArrayList<>();

        boolean result = l.addAll(elementsToAdd);

        assertFalse(result);
        assertEquals(0, l.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddAllToNonEmptyList() {
        final List<E> l = makeObject();
        l.add((E) "initial");

        List<E> elementsToAdd = new ArrayList<>();
        elementsToAdd.add((E) "one");
        elementsToAdd.add((E) "two");
        elementsToAdd.add((E) "three");

        l.addAll(elementsToAdd);

        assertEquals(4, l.size());
        assertEquals("initial", l.get(0));
        assertEquals("one", l.get(1));
        assertEquals("two", l.get(2));
        assertEquals("three", l.get(3));
    }
}