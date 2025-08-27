package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

public class SimpleBloomFilterLLM_Test {

    @Test
    public void testConstructorWithShape() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        assertEquals(shape, filter.getShape());
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testConstructorWithBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter1 = new SimpleBloomFilter(shape);
        SimpleBloomFilter filter2 = new SimpleBloomFilter(filter1);
        assertEquals(shape, filter2.getShape());
        assertEquals(0, filter2.cardinality());
    }

    @Test
    public void testConstructorWithShapeAndHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher = new SimpleHasher();
        SimpleBloomFilter filter = new SimpleBloomFilter(shape, hasher);
        assertEquals(shape, filter.getShape());
        assertTrue(filter.cardinality() > 0);
    }

    @Test
    public void testConstructorWithShapeAndIndexProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        IndexProducer indices = new SimpleIndexProducer();
        SimpleBloomFilter filter = new SimpleBloomFilter(shape, indices);
        assertEquals(shape, filter.getShape());
        assertTrue(filter.cardinality() > 0);
    }

    @Test
    public void testConstructorWithShapeAndBitMapProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitMapProducer bitMaps = new SimpleBitMapProducer();
        SimpleBloomFilter filter = new SimpleBloomFilter(shape, bitMaps);
        assertEquals(shape, filter.getShape());
        assertTrue(filter.cardinality() > 0);
    }

    @Test
    public void testCopy() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter1 = new SimpleBloomFilter(shape);
        SimpleBloomFilter filter2 = filter1.copy();
        assertEquals(filter1.getShape(), filter2.getShape());
        assertEquals(filter1.cardinality(), filter2.cardinality());
    }

    @Test
    public void testAsBitMapArray() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        long[] bitMapArray = filter.asBitMapArray();
        assertNotNull(bitMapArray);
        assertEquals(BitMap.numberOfBitMaps(shape.getNumberOfBits()), bitMapArray.length);
    }

    @Test
    public void testForEachBitMapPair() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter1 = new SimpleBloomFilter(shape);
        SimpleBloomFilter filter2 = new SimpleBloomFilter(shape);
        assertTrue(filter1.forEachBitMapPair(filter2, (a, b) -> a == b));
    }

    @Test
    public void testMergeInPlaceWithHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        Hasher hasher = new SimpleHasher();
        assertTrue(filter.mergeInPlace(hasher));
        assertTrue(filter.cardinality() > 0);
    }

    @Test
    public void testMergeInPlaceWithBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter1 = new SimpleBloomFilter(shape);
        SimpleBloomFilter filter2 = new SimpleBloomFilter(shape);
        assertTrue(filter1.mergeInPlace(filter2));
    }

    @Test
    public void testIsSparse() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        assertFalse(filter.isSparse());
    }

    @Test
    public void testCardinality() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testForEachIndex() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        IntPredicate consumer = idx -> true;
        assertTrue(filter.forEachIndex(consumer));
    }

    @Test
    public void testForEachBitMap() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        LongPredicate consumer = value -> true;
        assertTrue(filter.forEachBitMap(consumer));
    }

    @Test
    public void testContains() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        IndexProducer indexProducer = new SimpleIndexProducer();
        assertFalse(filter.contains(indexProducer));
    }
}