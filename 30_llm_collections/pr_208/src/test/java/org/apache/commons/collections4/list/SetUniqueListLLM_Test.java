package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import junit.framework.TestCase;

public class SetUniqueListLLM_Test<E> extends TestCase {

    public SetUniqueListTest2(String testName) {
        super(testName);
    }

    public void testSetListIteratorLastInitialization() {
        List<E> list = new ArrayList<>();
        Set<E> set = new HashSet<>();
        SetUniqueList<E> uniqueList = new SetUniqueList<>(list, set);
        SetUniqueList.SetListIterator<E> iterator = new SetUniqueList.SetListIterator<>(list.iterator(), set);
        assertNull("Initial value of 'last' should be null", iterator.last);
    }

    public void testSetListListIteratorLastInitialization() {
        List<E> list = new ArrayList<>();
        Set<E> set = new HashSet<>();
        SetUniqueList<E> uniqueList = new SetUniqueList<>(list, set);
        SetUniqueList.SetListListIterator<E> listIterator = new SetUniqueList.SetListListIterator<>(list.listIterator(), set);
        assertNull("Initial value of 'last' should be null", listIterator.last);
    }
}