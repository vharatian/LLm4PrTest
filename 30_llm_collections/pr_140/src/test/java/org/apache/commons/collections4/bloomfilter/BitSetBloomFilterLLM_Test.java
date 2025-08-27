package org.apache.commons.collections4.bloomfilter;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitSetBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Override
    protected BitSetBloomFilter createEmptyFilter(final Shape shape) {
        return new BitSetBloomFilter(shape);
    }

    @Override
    protected BitSetBloomFilter createFilter(final Hasher hasher, final Shape shape) {
        return new BitSetBloomFilter(hasher, shape);
    }

    @Test
    public void testToStringRemoved() {
        BitSetBloomFilter filter = createEmptyFilter(new Shape(3, 100));
        assertThrows(NoSuchMethodException.class, () -> {
            filter.getClass().getMethod("toString");
        });
    }
}