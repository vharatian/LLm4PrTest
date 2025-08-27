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

    /**
     * Test to ensure the bitSet field is correctly initialized.
     */
    @Test
    public void testBitSetInitialization() {
        Shape shape = new Shape(3, 100);
        BitSetBloomFilter filter = new BitSetBloomFilter(shape);
        assertNotNull(filter.getBits(), "The bitSet should be initialized and not null.");
    }
}