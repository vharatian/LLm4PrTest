package org.apache.commons.collections4.comparators;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComparableComparatorLLM_Test extends AbstractComparatorTest<Integer> {
    public ComparableComparatorTest2() {
        super(ComparableComparatorTest2.class.getSimpleName());
    }

    @Override
    public Comparator<Integer> makeObject() {
        return new ComparableComparator<>();
    }

    @Override
    public List<Integer> getComparableObjectsOrdered() {
        final List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        return list;
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Test
    public void testEqualsWithComparator() {
        ComparableComparator<Integer> comparator1 = new ComparableComparator<>();
        ComparableComparator<Integer> comparator2 = new ComparableComparator<>();
        assertTrue(comparator1.equals(comparator2));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        ComparableComparator<Integer> comparator = new ComparableComparator<>();
        Object differentObject = new Object();
        assertFalse(comparator.equals(differentObject));
    }

    @Test
    public void testEqualsWithNull() {
        ComparableComparator<Integer> comparator = new ComparableComparator<>();
        assertFalse(comparator.equals(null));
    }
}