package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class UnmodifiableSortedSetLLM_Test<E> extends AbstractSortedSetTest<E> {
    protected UnmodifiableSortedSet<E> set = null;
    protected ArrayList<E> array = null;

    public UnmodifiableSortedSetTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(UnmodifiableSortedSetTest2.class);
    }

    @Override
    public SortedSet<E> makeObject() {
        return UnmodifiableSortedSet.unmodifiableSortedSet(new TreeSet<E>());
    }

    @Override
    public UnmodifiableSortedSet<E> makeFullCollection() {
        final TreeSet<E> set = new TreeSet<>();
        set.addAll(Arrays.asList(getFullElements()));
        return (UnmodifiableSortedSet<E>) UnmodifiableSortedSet.unmodifiableSortedSet(set);
    }

    @Override
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    @SuppressWarnings("unchecked")
    protected void setupSet() {
        set = makeFullCollection();
        array = new ArrayList<>();
        array.add((E) Integer.valueOf(1));
    }

    @SuppressWarnings("unchecked")
    public void testUnmodifiable() {
        setupSet();
        verifyUnmodifiable(set);
        verifyUnmodifiable(set.headSet((E) Integer.valueOf(1)));
        verifyUnmodifiable(set.tailSet((E) Integer.valueOf(1)));
        verifyUnmodifiable(set.subSet((E) Integer.valueOf(1), (E) Integer.valueOf(3)));
    }

    public void testDecorateFactory() {
        final SortedSet<E> set = makeFullCollection();
        assertSame(set, UnmodifiableSortedSet.unmodifiableSortedSet(set));
        try {
            UnmodifiableSortedSet.unmodifiableSortedSet(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void verifyUnmodifiable(final SortedSet<E> set) {
        try {
            set.add((E) "value");
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
        }
        try {
            set.addAll(new TreeSet<E>());
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
        }
        try {
            set.clear();
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
        }
        try {
            set.remove("x");
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
        }
        try {
            set.removeAll(array);
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
        }
        try {
            set.retainAll(array);
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
        }
        try {
            set.removeIf(new Predicate<E>() {
                @Override
                public boolean test(E e) {
                    return false;
                }
            });
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
        }
    }

    public void testComparator() {
        setupSet();
        final Comparator<? super E> c = set.comparator();
        assertTrue("natural order, so comparator should be null", c == null);
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}