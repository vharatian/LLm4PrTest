package org.apache.commons.collections4.bloomfilter;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.PrimitiveIterator.OfInt;
import java.util.function.IntConsumer;

public class HasherBloomFilterLLM_Test {

    @Test
    public void testConstructorWithHasherAndShape() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher = mock(Hasher.class);
        when(hasher.getBits(shape)).thenReturn(EmptyIterator.emptyIterator());

        HasherBloomFilter filter = new HasherBloomFilter(hasher, shape);
        assertNotNull(filter.getHasher());
        assertEquals(shape, filter.getShape());
    }

    @Test
    public void testConstructorWithShape() {
        Shape shape = new Shape(3, 1000, 0.01);
        HasherBloomFilter filter = new HasherBloomFilter(shape);
        assertNotNull(filter.getHasher());
        assertEquals(shape, filter.getShape());
    }

    @Test
    public void testGetBits() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher = mock(Hasher.class);
        OfInt iterator = mock(OfInt.class);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.nextInt()).thenReturn(5);
        when(hasher.getBits(shape)).thenReturn(iterator);

        HasherBloomFilter filter = new HasherBloomFilter(hasher, shape);
        long[] bits = filter.getBits();
        assertEquals(1, bits.length);
        assertEquals(1L << 5, bits[0]);
    }

    @Test
    public void testGetHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher = mock(Hasher.class);
        when(hasher.getBits(shape)).thenReturn(EmptyIterator.emptyIterator());

        HasherBloomFilter filter = new HasherBloomFilter(hasher, shape);
        assertNotNull(filter.getHasher());
    }

    @Test
    public void testMergeWithBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher1 = mock(Hasher.class);
        Hasher hasher2 = mock(Hasher.class);
        when(hasher1.getBits(shape)).thenReturn(EmptyIterator.emptyIterator());
        when(hasher2.getBits(shape)).thenReturn(EmptyIterator.emptyIterator());

        HasherBloomFilter filter1 = new HasherBloomFilter(hasher1, shape);
        HasherBloomFilter filter2 = new HasherBloomFilter(hasher2, shape);

        filter1.merge(filter2);
        assertNotNull(filter1.getHasher());
    }

    @Test
    public void testMergeWithHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher1 = mock(Hasher.class);
        Hasher hasher2 = mock(Hasher.class);
        when(hasher1.getBits(shape)).thenReturn(EmptyIterator.emptyIterator());
        when(hasher2.getBits(shape)).thenReturn(EmptyIterator.emptyIterator());

        HasherBloomFilter filter = new HasherBloomFilter(hasher1, shape);
        filter.merge(hasher2);
        assertNotNull(filter.getHasher());
    }

    @Test
    public void testCardinality() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher = mock(Hasher.class);
        when(hasher.size()).thenReturn(5);

        HasherBloomFilter filter = new HasherBloomFilter(hasher, shape);
        assertEquals(5, filter.cardinality());
    }

    @Test
    public void testContains() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher1 = mock(Hasher.class);
        Hasher hasher2 = mock(Hasher.class);
        OfInt iterator1 = mock(OfInt.class);
        OfInt iterator2 = mock(OfInt.class);
        when(iterator1.hasNext()).thenReturn(true, false);
        when(iterator1.nextInt()).thenReturn(5);
        when(iterator2.hasNext()).thenReturn(true, false);
        when(iterator2.nextInt()).thenReturn(5);
        when(hasher1.getBits(shape)).thenReturn(iterator1);
        when(hasher2.getBits(shape)).thenReturn(iterator2);

        HasherBloomFilter filter = new HasherBloomFilter(hasher1, shape);
        assertTrue(filter.contains(hasher2));
    }
}