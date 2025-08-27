package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CountingBloomFilterLLM_Test {

    @Test
    public void testConstructorWithHasherAndShape() {
        Hasher hasher = new StaticHasher(new int[]{1, 2, 3}, new Shape(3, 3));
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(hasher, shape);

        assertEquals(3, filter.cardinality());
        assertTrue(filter.contains(hasher));
    }

    @Test
    public void testConstructorWithShape() {
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(shape);

        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testConstructorWithCountsAndShape() {
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(1, 2);
        counts.put(2, 3);
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(counts, shape);

        assertEquals(2, filter.cardinality());
        assertEquals(2, filter.getCounts().filter(entry -> entry.getKey() == 1).findFirst().get().getValue());
        assertEquals(3, filter.getCounts().filter(entry -> entry.getKey() == 2).findFirst().get().getValue());
    }

    @Test
    public void testGetCounts() {
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(1, 2);
        counts.put(2, 3);
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(counts, shape);

        Stream<Map.Entry<Integer, Integer>> countsStream = filter.getCounts();
        Map<Integer, Integer> resultMap = new TreeMap<>();
        countsStream.forEach(entry -> resultMap.put(entry.getKey(), entry.getValue()));

        assertEquals(counts, resultMap);
    }

    @Test
    public void testToString() {
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(1, 2);
        counts.put(2, 3);
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(counts, shape);

        String expected = "{ (1,2) (2,3) }";
        assertEquals(expected, filter.toString());
    }

    @Test
    public void testMergeWithOtherCountingBloomFilter() {
        Map<Integer, Integer> counts1 = new HashMap<>();
        counts1.put(1, 2);
        counts1.put(2, 3);
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter1 = new CountingBloomFilter(counts1, shape);

        Map<Integer, Integer> counts2 = new HashMap<>();
        counts2.put(2, 1);
        counts2.put(3, 4);
        CountingBloomFilter filter2 = new CountingBloomFilter(counts2, shape);

        filter1.merge(filter2);

        assertEquals(3, filter1.cardinality());
        assertEquals(2, filter1.getCounts().filter(entry -> entry.getKey() == 1).findFirst().get().getValue());
        assertEquals(4, filter1.getCounts().filter(entry -> entry.getKey() == 2).findFirst().get().getValue());
        assertEquals(4, filter1.getCounts().filter(entry -> entry.getKey() == 3).findFirst().get().getValue());
    }

    @Test
    public void testMergeWithHasher() {
        Hasher hasher = new StaticHasher(new int[]{1, 2, 3}, new Shape(3, 3));
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(shape);

        filter.merge(hasher);

        assertEquals(3, filter.cardinality());
        assertTrue(filter.contains(hasher));
    }

    @Test
    public void testRemoveWithOtherCountingBloomFilter() {
        Map<Integer, Integer> counts1 = new HashMap<>();
        counts1.put(1, 2);
        counts1.put(2, 3);
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter1 = new CountingBloomFilter(counts1, shape);

        Map<Integer, Integer> counts2 = new HashMap<>();
        counts2.put(2, 1);
        counts2.put(3, 4);
        CountingBloomFilter filter2 = new CountingBloomFilter(counts2, shape);

        filter1.remove(filter2);

        assertEquals(1, filter1.cardinality());
        assertEquals(2, filter1.getCounts().filter(entry -> entry.getKey() == 1).findFirst().get().getValue());
        assertNull(filter1.getCounts().filter(entry -> entry.getKey() == 2).findFirst().orElse(null));
    }

    @Test
    public void testRemoveWithHasher() {
        Hasher hasher = new StaticHasher(new int[]{1, 2, 3}, new Shape(3, 3));
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(hasher, shape);

        filter.remove(hasher);

        assertEquals(0, filter.cardinality());
        assertFalse(filter.contains(hasher));
    }

    @Test
    public void testGetBits() {
        Hasher hasher = new StaticHasher(new int[]{1, 2, 3}, new Shape(3, 3));
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(hasher, shape);

        long[] bits = filter.getBits();
        assertEquals(1, bits.length);
        assertEquals(14, bits[0]); // 1110 in binary
    }

    @Test
    public void testGetHasher() {
        Hasher hasher = new StaticHasher(new int[]{1, 2, 3}, new Shape(3, 3));
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(hasher, shape);

        StaticHasher staticHasher = filter.getHasher();
        assertTrue(filter.contains(staticHasher));
    }

    @Test
    public void testContains() {
        Hasher hasher = new StaticHasher(new int[]{1, 2, 3}, new Shape(3, 3));
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(hasher, shape);

        assertTrue(filter.contains(hasher));

        Hasher hasher2 = new StaticHasher(new int[]{4, 5, 6}, shape);
        assertFalse(filter.contains(hasher2));
    }

    @Test
    public void testCardinality() {
        Hasher hasher = new StaticHasher(new int[]{1, 2, 3}, new Shape(3, 3));
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter = new CountingBloomFilter(hasher, shape);

        assertEquals(3, filter.cardinality());
    }

    @Test
    public void testAndCardinality() {
        Map<Integer, Integer> counts1 = new HashMap<>();
        counts1.put(1, 2);
        counts1.put(2, 3);
        Shape shape = new Shape(3, 3);
        CountingBloomFilter filter1 = new CountingBloomFilter(counts1, shape);

        Map<Integer, Integer> counts2 = new HashMap<>();
        counts2.put(2, 1);
        counts2.put(3, 4);
        CountingBloomFilter filter2 = new CountingBloomFilter(counts2, shape);

        assertEquals(1, filter1.andCardinality(filter2));
    }
}