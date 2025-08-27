package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.list.PredicatedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListUtilsLLM_Test {

    private static final Predicate<Number> EQUALS_TWO = input -> input.intValue() == 2;
    private String[] fullArray;
    private List<String> fullList;

    @BeforeEach
    public void setUp() {
        fullArray = new String[]{"a", "b", "c", "d", "e"};
        fullList = new ArrayList<>(Arrays.asList(fullArray));
    }

    @Test
    @SuppressWarnings("boxing")
    public void testSelect() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        final List<Integer> output1 = ListUtils.select(list, EQUALS_TWO);
        final List<Number> output2 = ListUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
        assertEquals(4, list.size());
        assertEquals(1, output1.size());
        assertEquals(2, output2.iterator().next());
    }

    @Test
    @SuppressWarnings("boxing")
    public void testSelectRejected() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output2));
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
        assertEquals(4, list.size());
        assertEquals(3, output1.size());
        assertTrue(output1.contains(1L));
        assertTrue(output1.contains(3L));
        assertTrue(output1.contains(4L));
    }
}