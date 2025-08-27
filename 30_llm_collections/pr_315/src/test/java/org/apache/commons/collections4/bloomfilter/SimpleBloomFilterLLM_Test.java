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
    public void testMergeWithIndexProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        IndexProducer indexProducer = idx -> idx == 1 || idx == 2;
        filter.merge(indexProducer);
        assertTrue(filter.contains(indexProducer));
    }

    @Test
    public void testMergeWithBitMapProducer() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        BitMapProducer bitMapProducer = consumer -> {
            consumer.test(0b10L);
            return true;
        };
        filter.merge(bitMapProducer);
        assertTrue(filter.contains(IndexProducer.fromBitMapProducer(bitMapProducer)));
    }

    @Test
    public void testMergeWithHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        Hasher hasher = new SimpleHasher(1, 2);
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        filter.merge(hasher);
        assertTrue(filter.contains(hasher.indices(shape)));
    }

    @Test
    public void testMergeWithBloomFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        SimpleBloomFilter filter1 = new SimpleBloomFilter(shape);
        SimpleBloomFilter filter2 = new SimpleBloomFilter(shape, new SimpleHasher(1, 2));
        filter1.merge(filter2);
        assertTrue(filter1.contains(filter2));
    }
}