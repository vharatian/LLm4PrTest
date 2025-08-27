package org.apache.commons.collections4.list;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FixedSizeListLLM_Test<E> extends AbstractListTest<E> {

    public FixedSizeListTest2(final String testName) {
        super(testName);
    }

    @Override
    public List<E> makeObject() {
        return FixedSizeList.fixedSizeList(new ArrayList<E>());
    }

    @Override
    public List<E> makeFullCollection() {
        final List<E> list = new ArrayList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return FixedSizeList.fixedSizeList(list);
    }

    @Override
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    /**
     * Test that removeIf throws UnsupportedOperationException.
     */
    @Test
    public void testRemoveIfThrowsException() {
        List<String> decoratedList = new ArrayList<>();
        decoratedList.add("item 1");
        decoratedList.add("item 2");
        FixedSizeList<String> fixedSizeList = FixedSizeList.fixedSizeList(decoratedList);

        Predicate<String> predicate = s -> s.startsWith("item");

        try {
            fixedSizeList.removeIf(predicate);
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            Assert.assertEquals("List is fixed size", e.getMessage());
        }
    }
}