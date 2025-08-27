package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testCharacteristicsMethod() {
        SparseBloomFilter filter = createEmptyFilter(getTestShape());
        assertEquals(SparseBloomFilter.SPARSE, filter.characteristics());
    }

    @Test
    public void testMergeWithSparseCharacteristic() {
        Shape shape = getTestShape();
        BloomFilter sparseFilter = new SparseBloomFilter(shape, IndexProducer.fromIndexArray(new int[]{1, 2, 3}));
        SparseBloomFilter filter = createEmptyFilter(shape);
        filter.merge(sparseFilter);
        assertTrue(filter.contains(IndexProducer.fromIndexArray(new int[]{1, 2, 3})));
    }

    @Test
    public void testMergeWithNonSparseCharacteristic() {
        Shape shape = getTestShape();
        BloomFilter nonSparseFilter = new SimpleBloomFilter(shape, IndexProducer.fromIndexArray(new int[]{1, 2, 3}));
        SparseBloomFilter filter = createEmptyFilter(shape);
        filter.merge(nonSparseFilter);
        assertTrue(filter.contains(IndexProducer.fromIndexArray(new int[]{1, 2, 3})));
    }

    @Test
    public void testConstructorWithSparseCharacteristic() {
        Shape shape = getTestShape();
        BloomFilter sparseFilter = new SparseBloomFilter(shape, IndexProducer.fromIndexArray(new int[]{1, 2, 3}));
        SparseBloomFilter filter = new SparseBloomFilter(sparseFilter);
        assertTrue(filter.contains(IndexProducer.fromIndexArray(new int[]{1, 2, 3})));
    }

    @Test
    public void testConstructorWithNonSparseCharacteristic() {
        Shape shape = getTestShape();
        BloomFilter nonSparseFilter = new SimpleBloomFilter(shape, IndexProducer.fromIndexArray(new int[]{1, 2, 3}));
        SparseBloomFilter filter = new SparseBloomFilter(nonSparseFilter);
        assertTrue(filter.contains(IndexProducer.fromIndexArray(new int[]{1, 2, 3})));
    }
}