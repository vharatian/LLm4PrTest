package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class UnmodifiableNavigableSetLLM_Test<E> extends AbstractNavigableSetTest<E> {

    protected UnmodifiableNavigableSet<E> set = null;
    protected ArrayList<E> array = null;

    public UnmodifiableNavigableSetTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(UnmodifiableNavigableSetTest2.class);
    }

    @Override
    public NavigableSet<E> makeObject() {
        return UnmodifiableNavigableSet.unmodifiableNavigableSet(new TreeSet<E>());
    }

    @Override
    public UnmodifiableNavigableSet<E> makeFullCollection() {
        final TreeSet<E> set = new TreeSet<>();
        set.addAll(Arrays.asList(getFullElements()));
        return (UnmodifiableNavigableSet<E>) UnmodifiableNavigableSet.unmodifiableNavigableSet(set);
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

    /**
     * Test that removeIf throws UnsupportedOperationException.
     */
    public void testRemoveIf() {
        setupSet();
        try {
            set.removeIf(new Predicate<E>() {
                @Override
                public boolean test(E e) {
                    return true;
                }
            });
            fail("Expecting UnsupportedOperationException.");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }
}