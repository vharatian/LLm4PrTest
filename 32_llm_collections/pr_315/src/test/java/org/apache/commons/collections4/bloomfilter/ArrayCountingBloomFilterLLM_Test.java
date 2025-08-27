package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArrayCountingBloomFilterLLM_Test extends AbstractCountingBloomFilterTest<ArrayCountingBloomFilter> {

    @Override
    protected ArrayCountingBloomFilter createEmptyFilter(Shape shape) {
        return new ArrayCountingBloomFilter(shape);
    }

    @Override
    protected ArrayCountingBloomFilter createFilter(Shape shape, Hasher hasher) {
        return createFilter(shape, hasher.uniqueIndices(shape));
    }

    @Override
    protected ArrayCountingBloomFilter createFilter(Shape shape, BitMapProducer producer) {
        return createFilter(shape, IndexProducer.fromBitMapProducer(producer));
    }

    @Override
    protected ArrayCountingBloomFilter createFilter(Shape shape, IndexProducer producer) {
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);
        try {
            filter.add(BitCountProducer.from(producer));
            return filter;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test
    public void testMergeWithBloomFilter() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter1 = createEmptyFilter(shape);
        BloomFilter other = mock(BloomFilter.class);
        when(other.forEachIndex(any())).thenReturn(true);

        assertTrue(filter1.merge(other));
    }

    @Test
    public void testMergeWithHasher() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter1 = createEmptyFilter(shape);
        Hasher hasher = mock(Hasher.class);
        when(hasher.uniqueIndices(shape)).thenReturn(IndexProducer.fromIndexArray(1, 2, 3));

        assertTrue(filter1.merge(hasher));
    }

    @Test
    public void testMergeWithBloomFilterThrowsException() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter1 = createEmptyFilter(shape);
        BloomFilter other = mock(BloomFilter.class);
        when(other.forEachIndex(any())).thenThrow(new IndexOutOfBoundsException());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> filter1.merge(other));
        assertEquals("java.lang.IndexOutOfBoundsException", thrown.getMessage());
    }

    @Test
    public void testMergeWithHasherThrowsException() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter1 = createEmptyFilter(shape);
        Hasher hasher = mock(Hasher.class);
        when(hasher.uniqueIndices(shape)).thenThrow(new IndexOutOfBoundsException());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> filter1.merge(hasher));
        assertEquals("Filter only accepts values in the [0,100) range", thrown.getMessage());
    }
}