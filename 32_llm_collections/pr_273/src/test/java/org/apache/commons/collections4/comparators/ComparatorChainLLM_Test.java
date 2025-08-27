package org.apache.commons.collections4.comparators;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComparatorChainLLM_Test {

    @Test
    public void testComparatorChainConstructor() {
        // Test the default constructor to ensure it initializes correctly
        ComparatorChain<Integer> chain = new ComparatorChain<>();
        assertNotNull("ComparatorChain should not be null", chain);
        assertEquals("ComparatorChain should be empty", 0, chain.size());
    }

    @Test
    public void testComparatorChainConstructorWithComparator() {
        // Test the constructor with a single comparator
        Comparator<Integer> comparator = Integer::compareTo;
        ComparatorChain<Integer> chain = new ComparatorChain<>(comparator);
        assertNotNull("ComparatorChain should not be null", chain);
        assertEquals("ComparatorChain should have one comparator", 1, chain.size());
    }

    @Test
    public void testComparatorChainConstructorWithComparatorAndReverse() {
        // Test the constructor with a single comparator and reverse order
        Comparator<Integer> comparator = Integer::compareTo;
        ComparatorChain<Integer> chain = new ComparatorChain<>(comparator, true);
        assertNotNull("ComparatorChain should not be null", chain);
        assertEquals("ComparatorChain should have one comparator", 1, chain.size());
    }

    @Test
    public void testComparatorChainConstructorWithList() {
        // Test the constructor with a list of comparators
        ArrayList<Comparator<Integer>> list = new ArrayList<>();
        list.add(Integer::compareTo);
        ComparatorChain<Integer> chain = new ComparatorChain<>(list);
        assertNotNull("ComparatorChain should not be null", chain);
        assertEquals("ComparatorChain should have one comparator", 1, chain.size());
    }

    @Test
    public void testComparatorChainConstructorWithListAndBitSet() {
        // Test the constructor with a list of comparators and a BitSet
        ArrayList<Comparator<Integer>> list = new ArrayList<>();
        list.add(Integer::compareTo);
        BitSet bits = new BitSet(1);
        ComparatorChain<Integer> chain = new ComparatorChain<>(list, bits);
        assertNotNull("ComparatorChain should not be null", chain);
        assertEquals("ComparatorChain should have one comparator", 1, chain.size());
    }
}