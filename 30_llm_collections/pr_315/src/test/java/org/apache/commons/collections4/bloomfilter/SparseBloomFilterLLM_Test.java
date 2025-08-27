package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class SparseBloomFilterLLM_Test extends AbstractBloomFilterTest<SparseBloomFilter> {

    @Override
    protected SparseBloomFilter createEmptyFilter(final Shape shape) {
        return new SparseBloomFilter(shape);
    }

    @Override
    protected SparseBloomFilter createFilter(final Shape shape, final Hasher hasher) {
        return new SparseBloomFilter(shape, hasher);
    }

    @Override
    protected SparseBloomFilter createFilter(final Shape shape, final BitMapProducer producer) {
        return new SparseBloomFilter(shape, producer);
    }

    @Override
    protected SparseBloomFilter createFilter(final Shape shape, final IndexProducer producer) {
        return new SparseBloomFilter(shape, producer);
    }

    @Test
    public void testMergeWithHasher() {
        Shape shape = getTestShape();
        Hasher hasher = from1;
        SparseBloomFilter filter = createEmptyFilter(shape);
        filter.merge(hasher);
        assertTrue(filter.contains(IndexProducer.fromHasher(hasher, shape)));
    }

    @Test
    public void testMergeWithBloomFilter() {
        Shape shape = getTestShape();
        BloomFilter other = new SimpleBloomFilter(shape, from1);
        SparseBloomFilter filter = createEmptyFilter(shape);
        filter.merge(other);
        assertTrue(filter.contains(IndexProducer.fromHasher(from1, shape)));
    }

    @Test
    public void testMergeWithIndexProducer() {
        Shape shape = getTestShape();
        IndexProducer producer = IndexProducer.fromIndexArray(new int[]{1, 2, 3});
        SparseBloomFilter filter = createEmptyFilter(shape);
        filter.merge(producer);
        assertTrue(filter.contains(producer));
    }

    @Test
    public void testMergeWithBitMapProducer() {
        Shape shape = getTestShape();
        BitMapProducer bitMapProducer = BitMapProducer.fromBitMapArray(new long[]{1L, 2L, 3L});
        SparseBloomFilter filter = createEmptyFilter(shape);
        filter.merge(IndexProducer.fromBitMapProducer(bitMapProducer));
        assertTrue(filter.contains(IndexProducer.fromBitMapProducer(bitMapProducer)));
    }
}