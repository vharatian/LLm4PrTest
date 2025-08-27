package org.apache.commons.collections4.bloomfilter;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BloomFilterLLM_Test {

    @Test
    public void testCopy() {
        BloomFilter original = mock(BloomFilter.class);
        BloomFilter copy = mock(BloomFilter.class);
        when(original.copy()).thenReturn(copy);

        BloomFilter result = original.copy();
        assertSame(copy, result);
        verify(original).copy();
    }

    @Test
    public void testIsSparse() {
        BloomFilter filter = mock(BloomFilter.class);
        when(filter.isSparse()).thenReturn(true);

        assertTrue(filter.isSparse());
        verify(filter).isSparse();
    }

    @Test
    public void testContainsBloomFilter() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        when(filter1.contains(filter2)).thenCallRealMethod();
        when(filter1.isSparse()).thenReturn(true);
        when(filter1.contains((IndexProducer) filter2)).thenReturn(true);

        assertTrue(filter1.contains(filter2));
        verify(filter1).contains((IndexProducer) filter2);
    }

    @Test
    public void testContainsHasher() {
        BloomFilter filter = mock(BloomFilter.class);
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        IndexProducer indexProducer = mock(IndexProducer.class);
        when(filter.getShape()).thenReturn(shape);
        when(hasher.indices(shape)).thenReturn(indexProducer);
        when(filter.contains(indexProducer)).thenReturn(true);
        when(filter.contains(hasher)).thenCallRealMethod();

        assertTrue(filter.contains(hasher));
        verify(filter).contains(indexProducer);
    }

    @Test
    public void testMergeBloomFilter() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        BloomFilter result = mock(BloomFilter.class);
        when(filter1.copy()).thenReturn(result);
        doNothing().when(result).mergeInPlace(filter2);
        when(filter1.merge(filter2)).thenCallRealMethod();

        BloomFilter merged = filter1.merge(filter2);
        assertSame(result, merged);
        verify(result).mergeInPlace(filter2);
    }

    @Test
    public void testMergeHasher() {
        BloomFilter filter = mock(BloomFilter.class);
        Hasher hasher = mock(Hasher.class);
        BloomFilter result = mock(BloomFilter.class);
        when(filter.copy()).thenReturn(result);
        doNothing().when(result).mergeInPlace(hasher);
        when(filter.merge(hasher)).thenCallRealMethod();

        BloomFilter merged = filter.merge(hasher);
        assertSame(result, merged);
        verify(result).mergeInPlace(hasher);
    }

    @Test
    public void testIsFull() {
        BloomFilter filter = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);
        when(filter.getShape()).thenReturn(shape);
        when(shape.getNumberOfBits()).thenReturn(100);
        when(filter.cardinality()).thenReturn(100);
        when(filter.isFull()).thenCallRealMethod();

        assertTrue(filter.isFull());
        verify(filter).cardinality();
        verify(shape).getNumberOfBits();
    }

    @Test
    public void testEstimateN() {
        BloomFilter filter = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);
        when(filter.getShape()).thenReturn(shape);
        when(filter.cardinality()).thenReturn(50);
        when(shape.estimateN(50)).thenReturn(25.0);
        when(filter.estimateN()).thenCallRealMethod();

        assertEquals(25, filter.estimateN());
        verify(shape).estimateN(50);
    }

    @Test
    public void testEstimateUnion() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        BloomFilter merged = mock(BloomFilter.class);
        when(filter1.merge(filter2)).thenReturn(merged);
        when(merged.estimateN()).thenReturn(30);
        when(filter1.estimateUnion(filter2)).thenCallRealMethod();

        assertEquals(30, filter1.estimateUnion(filter2));
        verify(filter1).merge(filter2);
        verify(merged).estimateN();
    }

    @Test
    public void testEstimateIntersection() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        when(filter1.estimateN()).thenReturn(20);
        when(filter2.estimateN()).thenReturn(30);
        when(filter1.estimateUnion(filter2)).thenReturn(40);
        when(filter1.estimateIntersection(filter2)).thenCallRealMethod();

        assertEquals(10, filter1.estimateIntersection(filter2));
        verify(filter1).estimateN();
        verify(filter2).estimateN();
        verify(filter1).estimateUnion(filter2);
    }
}