package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class LayeredBloomFilterLLM_Test {

    @Test
    public void testFixedLayeredBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertNotNull(filter);
        assertEquals(1, filter.getDepth());
    }

    @Test
    public void testCopy() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        LayeredBloomFilter copy = filter.copy();
        assertNotSame(filter, copy);
        assertEquals(filter.getShape(), copy.getShape());
    }

    @Test
    public void testGetDepth() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertEquals(1, filter.getDepth());
    }

    @Test
    public void testGet() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertThrows(NoSuchElementException.class, () -> filter.get(1));
    }

    @Test
    public void testCardinality() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testIsEmpty() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertTrue(filter.isEmpty());
    }

    @Test
    public void testClear() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        filter.clear();
        assertTrue(filter.isEmpty());
    }

    @Test
    public void testFlatten() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        BloomFilter flattened = filter.flatten();
        assertNotNull(flattened);
        assertEquals(0, flattened.cardinality());
    }

    @Test
    public void testFindHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        Hasher hasher = new SimpleHasher(1, 2, 3);
        int[] layers = filter.find(hasher);
        assertEquals(0, layers.length);
    }

    @Test
    public void testFindIndexProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        IndexProducer indexProducer = new SimpleIndexProducer(1, 2, 3);
        int[] layers = filter.find(indexProducer);
        assertEquals(0, layers.length);
    }

    @Test
    public void testFindBitMapProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        BitMapProducer bitMapProducer = new SimpleBitMapProducer(1L, 2L, 3L);
        int[] layers = filter.find(bitMapProducer);
        assertEquals(0, layers.length);
    }

    @Test
    public void testContainsBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        BloomFilter bf = new SimpleBloomFilter(shape);
        assertFalse(filter.contains(bf));
    }

    @Test
    public void testContainsBloomFilterProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        BloomFilterProducer producer = new SimpleBloomFilterProducer(shape);
        assertFalse(filter.contains(producer));
    }

    @Test
    public void testMergeBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        BloomFilter bf = new SimpleBloomFilter(shape);
        assertTrue(filter.merge(bf));
    }

    @Test
    public void testMergeIndexProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        IndexProducer indexProducer = new SimpleIndexProducer(1, 2, 3);
        assertTrue(filter.merge(indexProducer));
    }

    @Test
    public void testMergeBitMapProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        BitMapProducer bitMapProducer = new SimpleBitMapProducer(1L, 2L, 3L);
        assertTrue(filter.merge(bitMapProducer));
    }

    @Test
    public void testForEachIndex() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertTrue(filter.forEachIndex(index -> true));
    }

    @Test
    public void testForEachBitMap() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertTrue(filter.forEachBitMap(bitMap -> true));
    }

    @Test
    public void testEstimateN() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        assertEquals(0, filter.estimateN());
    }

    @Test
    public void testEstimateUnion() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        BloomFilter other = new SimpleBloomFilter(shape);
        assertEquals(0, filter.estimateUnion(other));
    }

    @Test
    public void testNext() {
        Shape shape = new Shape(3, 1000, 0.01);
        LayeredBloomFilter filter = LayeredBloomFilter.fixed(shape, 5);
        filter.next();
        assertEquals(2, filter.getDepth());
    }
}