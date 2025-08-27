package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GrowthListLLM_Test<E> extends AbstractListTest<E> {

    public GrowthListTest2(final String testName) {
        super(testName);
    }

    @Override
    public List<E> makeObject() {
        return new GrowthList<>();
    }

    @Override
    public List<E> makeFullCollection() {
        final List<E> list = new ArrayList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return GrowthList.growthList(list);
    }

    /**
     * Test the constructor with initial capacity.
     */
    public void testGrowthListInitialCapacity() {
        final int initialCapacity = 10;
        final GrowthList<Integer> grower = new GrowthList<>(initialCapacity);
        assertEquals(0, grower.size());
        grower.add(0, 1);
        assertEquals(1, grower.size());
        assertEquals(Integer.valueOf(1), grower.get(0));
    }

    @Override
    public void testListAddByIndexBoundsChecking() {
        List<E> list;
        final E element = getOtherElements()[0];
        try {
            list = makeObject();
            list.add(-1, element);
            fail("List.add should throw IndexOutOfBoundsException [-1]");
        } catch (final IndexOutOfBoundsException e) {
        }
    }

    @Override
    public void testListAddByIndexBoundsChecking2() {
        List<E> list;
        final E element = getOtherElements()[0];
        try {
            list = makeFullCollection();
            list.add(-1, element);
            fail("List.add should throw IndexOutOfBoundsException [-1]");
        } catch (final IndexOutOfBoundsException e) {
        }
    }

    @Override
    public void testListSetByIndexBoundsChecking() {
        final List<E> list = makeObject();
        final E element = getOtherElements()[0];
        try {
            list.set(-1, element);
            fail("List.set should throw IndexOutOfBoundsException [-1]");
        } catch (final IndexOutOfBoundsException e) {
        }
    }

    @Override
    public void testListSetByIndexBoundsChecking2() {
        final List<E> list = makeFullCollection();
        final E element = getOtherElements()[0];
        try {
            list.set(-1, element);
            fail("List.set should throw IndexOutOfBoundsException [-1]");
        } catch(final IndexOutOfBoundsException e) {
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}