package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BloomFilterLLM_Test {

    @Test
    public void testMergeBloomFilter() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        BloomFilter result = mock(BloomFilter.class);

        when(filter1.copy()).thenReturn(result);
        when(result.merge(filter2)).thenReturn(true);

        assertTrue(filter1.merge(filter2));
        verify(result).merge(filter2);
    }

    @Test
    public void testMergeHasher() {
        BloomFilter filter = mock(BloomFilter.class);
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        BloomFilter result = mock(BloomFilter.class);

        when(filter.getShape()).thenReturn(shape);
        when(filter.isSparse()).thenReturn(false);
        when(filter.copy()).thenReturn(result);
        when(result.merge(hasher)).thenReturn(true);

        assertTrue(filter.merge(hasher));
        verify(result).merge(hasher);
    }

    @Test
    public void testEstimateUnion() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        BloomFilter copy = mock(BloomFilter.class);

        when(filter1.copy()).thenReturn(copy);
        when(copy.merge(filter2)).thenReturn(true);
        when(copy.estimateN()).thenReturn(42);

        assertEquals(42, filter1.estimateUnion(filter2));
        verify(copy).merge(filter2);
    }
}