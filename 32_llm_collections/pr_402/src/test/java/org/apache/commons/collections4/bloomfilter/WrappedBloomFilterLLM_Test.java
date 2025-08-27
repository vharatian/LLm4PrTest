package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WrappedBloomFilterLLM_Test {

    private BloomFilter mockBloomFilter;
    private WrappedBloomFilter wrappedBloomFilter;

    @BeforeEach
    public void setUp() {
        mockBloomFilter = mock(BloomFilter.class);
        wrappedBloomFilter = new WrappedBloomFilter(mockBloomFilter) {};
    }

    @Test
    public void testForEachIndex() {
        IntPredicate predicate = mock(IntPredicate.class);
        when(mockBloomFilter.forEachIndex(predicate)).thenReturn(true);
        assertTrue(wrappedBloomFilter.forEachIndex(predicate));
        verify(mockBloomFilter).forEachIndex(predicate);
    }

    @Test
    public void testCopy() {
        BloomFilter copy = mock(BloomFilter.class);
        when(mockBloomFilter.copy()).thenReturn(copy);
        assertEquals(copy, wrappedBloomFilter.copy());
        verify(mockBloomFilter).copy();
    }

    @Test
    public void testForEachBitMap() {
        LongPredicate predicate = mock(LongPredicate.class);
        when(mockBloomFilter.forEachBitMap(predicate)).thenReturn(true);
        assertTrue(wrappedBloomFilter.forEachBitMap(predicate));
        verify(mockBloomFilter).forEachBitMap(predicate);
    }

    @Test
    public void testCharacteristics() {
        when(mockBloomFilter.characteristics()).thenReturn(123);
        assertEquals(123, wrappedBloomFilter.characteristics());
        verify(mockBloomFilter).characteristics();
    }

    @Test
    public void testGetShape() {
        Shape shape = mock(Shape.class);
        when(mockBloomFilter.getShape()).thenReturn(shape);
        assertEquals(shape, wrappedBloomFilter.getShape());
        verify(mockBloomFilter).getShape();
    }

    @Test
    public void testClear() {
        wrappedBloomFilter.clear();
        verify(mockBloomFilter).clear();
    }

    @Test
    public void testContainsBloomFilter() {
        BloomFilter other = mock(BloomFilter.class);
        when(mockBloomFilter.contains(other)).thenReturn(true);
        assertTrue(wrappedBloomFilter.contains(other));
        verify(mockBloomFilter).contains(other);
    }

    @Test
    public void testForEachBitMapPair() {
        BitMapProducer other = mock(BitMapProducer.class);
        LongBiPredicate func = mock(LongBiPredicate.class);
        when(mockBloomFilter.forEachBitMapPair(other, func)).thenReturn(true);
        assertTrue(wrappedBloomFilter.forEachBitMapPair(other, func));
        verify(mockBloomFilter).forEachBitMapPair(other, func);
    }

    @Test
    public void testContainsHasher() {
        Hasher hasher = mock(Hasher.class);
        when(mockBloomFilter.contains(hasher)).thenReturn(true);
        assertTrue(wrappedBloomFilter.contains(hasher));
        verify(mockBloomFilter).contains(hasher);
    }

    @Test
    public void testAsBitMapArray() {
        long[] bitMapArray = new long[]{1L, 2L, 3L};
        when(mockBloomFilter.asBitMapArray()).thenReturn(bitMapArray);
        assertArrayEquals(bitMapArray, wrappedBloomFilter.asBitMapArray());
        verify(mockBloomFilter).asBitMapArray();
    }

    @Test
    public void testAsIndexArray() {
        int[] indexArray = new int[]{1, 2, 3};
        when(mockBloomFilter.asIndexArray()).thenReturn(indexArray);
        assertArrayEquals(indexArray, wrappedBloomFilter.asIndexArray());
        verify(mockBloomFilter).asIndexArray();
    }

    @Test
    public void testContainsIndexProducer() {
        IndexProducer indexProducer = mock(IndexProducer.class);
        when(mockBloomFilter.contains(indexProducer)).thenReturn(true);
        assertTrue(wrappedBloomFilter.contains(indexProducer));
        verify(mockBloomFilter).contains(indexProducer);
    }

    @Test
    public void testContainsBitMapProducer() {
        BitMapProducer bitMapProducer = mock(BitMapProducer.class);
        when(mockBloomFilter.contains(bitMapProducer)).thenReturn(true);
        assertTrue(wrappedBloomFilter.contains(bitMapProducer));
        verify(mockBloomFilter).contains(bitMapProducer);
    }

    @Test
    public void testMergeBloomFilter() {
        BloomFilter other = mock(BloomFilter.class);
        when(mockBloomFilter.merge(other)).thenReturn(true);
        assertTrue(wrappedBloomFilter.merge(other));
        verify(mockBloomFilter).merge(other);
    }

    @Test
    public void testMergeHasher() {
        Hasher hasher = mock(Hasher.class);
        when(mockBloomFilter.merge(hasher)).thenReturn(true);
        assertTrue(wrappedBloomFilter.merge(hasher));
        verify(mockBloomFilter).merge(hasher);
    }

    @Test
    public void testMergeIndexProducer() {
        IndexProducer indexProducer = mock(IndexProducer.class);
        when(mockBloomFilter.merge(indexProducer)).thenReturn(true);
        assertTrue(wrappedBloomFilter.merge(indexProducer));
        verify(mockBloomFilter).merge(indexProducer);
    }

    @Test
    public void testMergeBitMapProducer() {
        BitMapProducer bitMapProducer = mock(BitMapProducer.class);
        when(mockBloomFilter.merge(bitMapProducer)).thenReturn(true);
        assertTrue(wrappedBloomFilter.merge(bitMapProducer));
        verify(mockBloomFilter).merge(bitMapProducer);
    }

    @Test
    public void testIsFull() {
        when(mockBloomFilter.isFull()).thenReturn(true);
        assertTrue(wrappedBloomFilter.isFull());
        verify(mockBloomFilter).isFull();
    }

    @Test
    public void testCardinality() {
        when(mockBloomFilter.cardinality()).thenReturn(42);
        assertEquals(42, wrappedBloomFilter.cardinality());
        verify(mockBloomFilter).cardinality();
    }

    @Test
    public void testEstimateN() {
        when(mockBloomFilter.estimateN()).thenReturn(42);
        assertEquals(42, wrappedBloomFilter.estimateN());
        verify(mockBloomFilter).estimateN();
    }

    @Test
    public void testEstimateUnion() {
        BloomFilter other = mock(BloomFilter.class);
        when(mockBloomFilter.estimateUnion(other)).thenReturn(42);
        assertEquals(42, wrappedBloomFilter.estimateUnion(other));
        verify(mockBloomFilter).estimateUnion(other);
    }

    @Test
    public void testEstimateIntersection() {
        BloomFilter other = mock(BloomFilter.class);
        when(mockBloomFilter.estimateIntersection(other)).thenReturn(42);
        assertEquals(42, wrappedBloomFilter.estimateIntersection(other));
        verify(mockBloomFilter).estimateIntersection(other);
    }
}