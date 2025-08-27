package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class ComparatorChainLLM_Test {

    @Test
    public void testEqualsWithNullOrderingBits() {
        List<Comparator<Integer>> list1 = new ArrayList<>();
        list1.add(new ComparableComparator<>());
        BitSet bits1 = null;

        List<Comparator<Integer>> list2 = new ArrayList<>();
        list2.add(new ComparableComparator<>());
        BitSet bits2 = null;

        ComparatorChain<Integer> chain1 = new ComparatorChain<>(list1, bits1);
        ComparatorChain<Integer> chain2 = new ComparatorChain<>(list2, bits2);

        assertEquals(chain1, chain2, "ComparatorChains with null orderingBits should be equal");
    }

    @Test
    public void testEqualsWithDifferentOrderingBits() {
        List<Comparator<Integer>> list1 = new ArrayList<>();
        list1.add(new ComparableComparator<>());
        BitSet bits1 = new BitSet();
        bits1.set(0);

        List<Comparator<Integer>> list2 = new ArrayList<>();
        list2.add(new ComparableComparator<>());
        BitSet bits2 = new BitSet();

        ComparatorChain<Integer> chain1 = new ComparatorChain<>(list1, bits1);
        ComparatorChain<Integer> chain2 = new ComparatorChain<>(list2, bits2);

        assertNotEquals(chain1, chain2, "ComparatorChains with different orderingBits should not be equal");
    }

    @Test
    public void testEqualsWithNullComparatorChain() {
        List<Comparator<Integer>> list1 = null;
        BitSet bits1 = new BitSet();

        List<Comparator<Integer>> list2 = null;
        BitSet bits2 = new BitSet();

        ComparatorChain<Integer> chain1 = new ComparatorChain<>(list1, bits1);
        ComparatorChain<Integer> chain2 = new ComparatorChain<>(list2, bits2);

        assertEquals(chain1, chain2, "ComparatorChains with null comparatorChain should be equal");
    }

    @Test
    public void testEqualsWithDifferentComparatorChain() {
        List<Comparator<Integer>> list1 = new ArrayList<>();
        list1.add(new ComparableComparator<>());
        BitSet bits1 = new BitSet();

        List<Comparator<Integer>> list2 = new ArrayList<>();
        list2.add(new ComparableComparator<>());
        list2.add(new ComparableComparator<>());
        BitSet bits2 = new BitSet();

        ComparatorChain<Integer> chain1 = new ComparatorChain<>(list1, bits1);
        ComparatorChain<Integer> chain2 = new ComparatorChain<>(list2, bits2);

        assertNotEquals(chain1, chain2, "ComparatorChains with different comparatorChain should not be equal");
    }

    @Test
    public void testEqualsWithObjectsEquals() {
        List<Comparator<Integer>> list1 = new ArrayList<>();
        list1.add(new ComparableComparator<>());
        BitSet bits1 = new BitSet();

        List<Comparator<Integer>> list2 = new ArrayList<>();
        list2.add(new ComparableComparator<>());
        BitSet bits2 = new BitSet();

        ComparatorChain<Integer> chain1 = new ComparatorChain<>(list1, bits1);
        ComparatorChain<Integer> chain2 = new ComparatorChain<>(list2, bits2);

        assertEquals(Objects.equals(chain1, chain2), chain1.equals(chain2), "Objects.equals should be consistent with ComparatorChain.equals");
    }
}