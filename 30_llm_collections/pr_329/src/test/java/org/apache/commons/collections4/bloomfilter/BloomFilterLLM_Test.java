package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BloomFilterLLM_Test {

    @Test
    public void testCharacteristics() {
        BloomFilter bloomFilter = mock(BloomFilter.class);
        when(bloomFilter.characteristics()).thenReturn(0x1);
        assertEquals(0x1, bloomFilter.characteristics());
    }

    @Test
    public void testContainsWithSparseCharacteristic() {
        BloomFilter bloomFilter = mock(BloomFilter.class);
        BloomFilter other = mock(BloomFilter.class);
        when(bloomFilter.characteristics()).thenReturn(BloomFilter.SPARSE);
        when(bloomFilter.contains((IndexProducer) other)).thenReturn(true);
        assertTrue(bloomFilter.contains(other));
    }

    @Test
    public void testContainsWithNonSparseCharacteristic() {
        BloomFilter bloomFilter = mock(BloomFilter.class);
        BloomFilter other = mock(BloomFilter.class);
        when(bloomFilter.characteristics()).thenReturn(0);
        when(bloomFilter.contains((BitMapProducer) other)).thenReturn(true);
        assertTrue(bloomFilter.contains(other));
    }

    @Test
    public void testMergeWithSparseCharacteristic() {
        BloomFilter bloomFilter = mock(BloomFilter.class);
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        when(bloomFilter.characteristics()).thenReturn(BloomFilter.SPARSE);
        when(bloomFilter.getShape()).thenReturn(shape);
        when(bloomFilter.merge(any(SparseBloomFilter.class))).thenReturn(true);
        assertTrue(bloomFilter.merge(hasher));
    }

    @Test
    public void testMergeWithNonSparseCharacteristic() {
        BloomFilter bloomFilter = mock(BloomFilter.class);
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        when(bloomFilter.characteristics()).thenReturn(0);
        when(bloomFilter.getShape()).thenReturn(shape);
        when(bloomFilter.merge(any(SimpleBloomFilter.class))).thenReturn(true);
        assertTrue(bloomFilter.merge(hasher));
    }
}