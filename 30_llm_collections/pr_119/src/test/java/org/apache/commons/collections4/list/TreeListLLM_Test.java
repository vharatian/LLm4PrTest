package org.apache.commons.collections4.list;

import java.util.List;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;
import java.util.Objects;

public class TreeListLLM_Test<E> extends AbstractListTest<E> {

    public TreeListTest2(final String name) {
        super(name);
    }

    public static Test suite() {
        return BulkTest.makeSuite(TreeListTest2.class);
    }

    @Override
    public TreeList<E> makeObject() {
        return new TreeList<>();
    }

    @SuppressWarnings("unchecked")
    public void testIndexOfWithNull() {
        final List<E> l = makeObject();
        l.add((E) "0");
        l.add((E) "1");
        l.add((E) "2");
        l.add((E) "3");
        l.add((E) "4");
        l.add((E) "5");
        l.add((E) "6");
        l.add(0, null);
        assertEquals(0, l.indexOf(null));
        assertEquals(1, l.indexOf("0"));
        assertEquals(2, l.indexOf("1"));
        assertEquals(3, l.indexOf("2"));
        assertEquals(4, l.indexOf("3"));
        assertEquals(5, l.indexOf("4"));
        assertEquals(6, l.indexOf("5"));
        assertEquals(7, l.indexOf("6"));
    }

    @SuppressWarnings("unchecked")
    public void testIndexOfWithObjectsEquals() {
        final List<E> l = makeObject();
        l.add((E) "0");
        l.add((E) "1");
        l.add((E) "2");
        l.add((E) "3");
        l.add((E) "4");
        l.add((E) "5");
        l.add((E) "6");
        assertEquals(0, l.indexOf("0"));
        assertEquals(1, l.indexOf("1"));
        assertEquals(2, l.indexOf("2"));
        assertEquals(3, l.indexOf("3"));
        assertEquals(4, l.indexOf("4"));
        assertEquals(5, l.indexOf("5"));
        assertEquals(6, l.indexOf("6"));
        l.set(1, (E) "0");
        assertEquals(0, l.indexOf("0"));
        l.set(3, (E) "3");
        assertEquals(3, l.indexOf("3"));
        l.set(2, (E) "3");
        assertEquals(2, l.indexOf("3"));
        l.set(1, (E) "3");
        assertEquals(1, l.indexOf("3"));
        l.set(0, (E) "3");
        assertEquals(0, l.indexOf("3"));
    }
}