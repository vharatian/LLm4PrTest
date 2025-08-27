package org.apache.commons.collections4.bloomfilter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
    public void testCharacteristics() {
        Shape shape = new Shape(3, 100, 0.01);
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);
        assertEquals(ArrayCountingBloomFilter.SPARSE, filter.characteristics());
    }
}