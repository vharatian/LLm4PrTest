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
        final List<E> list = new ArrayList<>(Arrays.asList(getFullElements()));
        return GrowthList.growthList(list);
    }

    public void testGrowthListConstructorNoType() {
        final GrowthList<Integer> grower = new GrowthList<>();
        assertNotNull(grower);
        assertTrue(grower instanceof GrowthList);
    }

    public void testGrowthListConstructorWithCapacityNoType() {
        final GrowthList<Integer> grower = new GrowthList<>(10);
        assertNotNull(grower);
        assertTrue(grower instanceof GrowthList);
        assertEquals(0, grower.size());
    }

    public void testGrowthListConstructorWithListNoType() {
        final List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        final GrowthList<Integer> grower = GrowthList.growthList(list);
        assertNotNull(grower);
        assertTrue(grower instanceof GrowthList);
        assertEquals(3, grower.size());
        assertEquals(Integer.valueOf(1), grower.get(0));
        assertEquals(Integer.valueOf(2), grower.get(1));
        assertEquals(Integer.valueOf(3), grower.get(2));
    }

    @Override
    public void testListAddByIndexBoundsChecking() {
        final List<E> list;
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
        final List<E> list;
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
        } catch (final IndexOutOfBoundsException e) {
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}