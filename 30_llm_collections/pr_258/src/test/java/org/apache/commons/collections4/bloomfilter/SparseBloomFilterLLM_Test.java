package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

public class SparseBloomFilterLLM_Test {

    @Test
    public void testConstructorWithShape() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter = new SparseBloomFilter(shape);
        assertEquals(shape, filter.getShape());
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testConstructorWithBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter1 = new SparseBloomFilter(shape);
        filter1.add(1);
        filter1.add(2);
        SparseBloomFilter filter2 = new SparseBloomFilter(filter1);
        assertEquals(shape, filter2.getShape());
        assertEquals(2, filter2.cardinality());
        assertTrue(filter2.contains(IndexProducer.fromIndexArray(1, 2)));
    }

    @Test
    public void testConstructorWithHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher = new SimpleHasher(1, 2, 3);
        SparseBloomFilter filter = new SparseBloomFilter(shape, hasher);
        assertEquals(shape, filter.getShape());
        assertEquals(3, filter.cardinality());
        assertTrue(filter.contains(IndexProducer.fromIndexArray(1, 2, 3)));
    }

    @Test
    public void testConstructorWithIndexProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        IndexProducer producer = IndexProducer.fromIndexArray(1, 2, 3);
        SparseBloomFilter filter = new SparseBloomFilter(shape, producer);
        assertEquals(shape, filter.getShape());
        assertEquals(3, filter.cardinality());
        assertTrue(filter.contains(producer));
    }

    @Test
    public void testConstructorWithBitMapProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitMapProducer producer = BitMapProducer.fromBitMapArray(new long[]{1L, 2L, 3L});
        SparseBloomFilter filter = new SparseBloomFilter(shape, producer);
        assertEquals(shape, filter.getShape());
        assertEquals(3, filter.cardinality());
        assertTrue(filter.contains(IndexProducer.fromBitMapProducer(producer)));
    }

    @Test
    public void testAsBitMapArray() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter = new SparseBloomFilter(shape);
        filter.add(1);
        filter.add(2);
        long[] bitMapArray = filter.asBitMapArray();
        assertTrue(BitMap.contains(bitMapArray, 1));
        assertTrue(BitMap.contains(bitMapArray, 2));
    }

    @Test
    public void testCopy() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter1 = new SparseBloomFilter(shape);
        filter1.add(1);
        filter1.add(2);
        SparseBloomFilter filter2 = filter1.copy();
        assertEquals(shape, filter2.getShape());
        assertEquals(2, filter2.cardinality());
        assertTrue(filter2.contains(IndexProducer.fromIndexArray(1, 2)));
    }

    @Test
    public void testMergeInPlaceWithHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter = new SparseBloomFilter(shape);
        Hasher hasher = new SimpleHasher(1, 2, 3);
        filter.mergeInPlace(hasher);
        assertEquals(3, filter.cardinality());
        assertTrue(filter.contains(IndexProducer.fromIndexArray(1, 2, 3)));
    }

    @Test
    public void testMergeInPlaceWithBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter1 = new SparseBloomFilter(shape);
        filter1.add(1);
        filter1.add(2);
        SparseBloomFilter filter2 = new SparseBloomFilter(shape);
        filter2.add(3);
        filter2.mergeInPlace(filter1);
        assertEquals(3, filter2.cardinality());
        assertTrue(filter2.contains(IndexProducer.fromIndexArray(1, 2, 3)));
    }

    @Test
    public void testForEachIndex() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter = new SparseBloomFilter(shape);
        filter.add(1);
        filter.add(2);
        IntPredicate consumer = idx -> {
            assertTrue(idx == 1 || idx == 2);
            return true;
        };
        assertTrue(filter.forEachIndex(consumer));
    }

    @Test
    public void testForEachBitMap() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter = new SparseBloomFilter(shape);
        filter.add(1);
        filter.add(2);
        LongPredicate consumer = bitMap -> {
            assertTrue(bitMap == 2L || bitMap == 4L);
            return true;
        };
        assertTrue(filter.forEachBitMap(consumer));
    }

    @Test
    public void testContainsWithIndexProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter = new SparseBloomFilter(shape);
        filter.add(1);
        filter.add(2);
        IndexProducer producer = IndexProducer.fromIndexArray(1, 2);
        assertTrue(filter.contains(producer));
    }

    @Test
    public void testContainsWithBitMapProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        SparseBloomFilter filter = new SparseBloomFilter(shape);
        filter.add(1);
        filter.add(2);
        BitMapProducer producer = BitMapProducer.fromBitMapArray(new long[]{2L, 4L});
        assertTrue(filter.contains(producer));
    }
}