package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleBloomFilterLLM_Test extends AbstractBloomFilterTest<SimpleBloomFilter> {

    @Override
    protected SimpleBloomFilter createEmptyFilter(final Shape shape) {
        return new SimpleBloomFilter(shape);
    }

    @Override
    protected SimpleBloomFilter createFilter(final Shape shape, final Hasher hasher) {
        return new SimpleBloomFilter(shape, hasher);
    }

    @Override
    protected SimpleBloomFilter createFilter(final Shape shape, final BitMapProducer producer) {
        return new SimpleBloomFilter(shape, producer);
    }

    @Override
    protected SimpleBloomFilter createFilter(final Shape shape, final IndexProducer producer) {
        return new SimpleBloomFilter(shape, producer);
    }

    @Test
    public void testCharacteristicsMethod() {
        SimpleBloomFilter filter = new SimpleBloomFilter(new Shape(3, 100));
        assertEquals(0, filter.characteristics(), "Expected characteristics to be 0 for SimpleBloomFilter");
    }

    @Test
    public void testMergeWithSparseCharacteristic() {
        Shape shape = new Shape(3, 100);
        BloomFilter sparseFilter = new SparseBloomFilter(shape);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);

        assertTrue(filter.merge(sparseFilter), "Merge with sparse filter should return true");
    }

    @Test
    public void testMergeWithNonSparseCharacteristic() {
        Shape shape = new Shape(3, 100);
        BloomFilter nonSparseFilter = new SimpleBloomFilter(shape);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);

        assertTrue(filter.merge(nonSparseFilter), "Merge with non-sparse filter should return true");
    }

    @Test
    public void testConstructorWithSparseCharacteristic() {
        Shape shape = new Shape(3, 100);
        BloomFilter sparseFilter = new SparseBloomFilter(shape);
        SimpleBloomFilter filter = new SimpleBloomFilter(sparseFilter);

        assertNotNull(filter, "SimpleBloomFilter constructed with sparse filter should not be null");
    }

    @Test
    public void testConstructorWithNonSparseCharacteristic() {
        Shape shape = new Shape(3, 100);
        BloomFilter nonSparseFilter = new SimpleBloomFilter(shape);
        SimpleBloomFilter filter = new SimpleBloomFilter(nonSparseFilter);

        assertNotNull(filter, "SimpleBloomFilter constructed with non-sparse filter should not be null");
    }
}