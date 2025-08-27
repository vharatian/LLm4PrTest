package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class UnmodifiableListLLM_Test<E> extends AbstractListTest<E> {

    public UnmodifiableListTest2(final String testName) {
        super(testName);
    }

    @Override
    public UnmodifiableList<E> makeObject() {
        return new UnmodifiableList<>(new ArrayList<E>());
    }

    @Override
    public UnmodifiableList<E> makeFullCollection() {
        final ArrayList<E> list = new ArrayList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return new UnmodifiableList<>(list);
    }

    @Override
    public boolean isSetSupported() {
        return false;
    }

    @Override
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    protected UnmodifiableList<E> list;
    protected ArrayList<E> array;

    @SuppressWarnings("unchecked")
    protected void setupList() {
        list = makeFullCollection();
        array = new ArrayList<>();
        array.add((E) Integer.valueOf(1));
    }

    public void testRemoveIf() {
        setupList();
        Predicate<E> predicate = e -> e.equals(Integer.valueOf(1));
        try {
            list.removeIf(predicate);
            fail("Expecting UnsupportedOperationException.");
        } catch (final UnsupportedOperationException e) {
            // Expected exception
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}