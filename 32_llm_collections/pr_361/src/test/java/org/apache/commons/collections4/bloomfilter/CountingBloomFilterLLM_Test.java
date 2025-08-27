package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CountingBloomFilterLLM_Test {

    @Test
    public void testRemoveHasher() {
        CountingBloomFilter filter = mock(CountingBloomFilter.class);
        Hasher hasher = mock(Hasher.class);
        IndexProducer indexProducer = mock(IndexProducer.class);
        BitCountProducer bitCountProducer = mock(BitCountProducer.class);
        Shape shape = mock(Shape.class);

        when(filter.getShape()).thenReturn(shape);
        when(hasher.uniqueIndices(shape)).thenReturn(indexProducer);
        when(BitCountProducer.from(indexProducer)).thenReturn(bitCountProducer);
        when(filter.subtract(bitCountProducer)).thenReturn(true);

        boolean result = filter.remove(hasher);
        assertTrue(result);
        verify(filter).remove(hasher);
    }
}