package org.apache.commons.collections4.bloomfilter;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;

public class BitSetBloomFilterLLM_Test {

    private Shape shape;
    private Hasher hasher;
    private BitSetBloomFilter filter;

    @BeforeEach
    public void setUp() {
        shape = new Shape(3, 1000, 0.01);
        hasher = new StaticHasher(new int[]{1, 2, 3}, shape);
        filter = new BitSetBloomFilter(hasher, shape);
    }

    @Test
    public void testConstructorWithHasherAndShape() {
        BitSetBloomFilter filter = new BitSetBloomFilter(hasher, shape);
        assertNotNull(filter);
        assertEquals(3, filter.cardinality());
    }

    @Test
    public void testConstructorWithShape() {
        BitSetBloomFilter filter = new BitSetBloomFilter(shape);
        assertNotNull(filter);
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testGetBits() {
        long[] bits = filter.getBits();
        assertNotNull(bits);
        assertTrue(bits.length > 0);
    }

    @Test
    public void testGetHasher() {
        StaticHasher staticHasher = filter.getHasher();
        assertNotNull(staticHasher);
    }

    @Test
    public void testMergeWithBloomFilter() {
        BitSetBloomFilter otherFilter = new BitSetBloomFilter(new StaticHasher(new int[]{4, 5, 6}, shape), shape);
        filter.merge(otherFilter);
        assertEquals(6, filter.cardinality());
    }

    @Test
    public void testMergeWithHasher() {
        Hasher otherHasher = new StaticHasher(new int[]{4, 5, 6}, shape);
        filter.merge(otherHasher);
        assertEquals(6, filter.cardinality());
    }

    @Test
    public void testContains() {
        Hasher otherHasher = new StaticHasher(new int[]{1, 2}, shape);
        assertTrue(filter.contains(otherHasher));
    }

    @Test
    public void testCardinality() {
        assertEquals(3, filter.cardinality());
    }

    @Test
    public void testToString() {
        String bitSetString = filter.toString();
        assertNotNull(bitSetString);
        assertFalse(bitSetString.isEmpty());
    }

    @Test
    public void testAndCardinality() {
        BitSetBloomFilter otherFilter = new BitSetBloomFilter(new StaticHasher(new int[]{1, 2, 4}, shape), shape);
        int andCardinality = filter.andCardinality(otherFilter);
        assertEquals(2, andCardinality);
    }

    @Test
    public void testXorCardinality() {
        BitSetBloomFilter otherFilter = new BitSetBloomFilter(new StaticHasher(new int[]{1, 2, 4}, shape), shape);
        int xorCardinality = filter.xorCardinality(otherFilter);
        assertEquals(2, xorCardinality);
    }
}