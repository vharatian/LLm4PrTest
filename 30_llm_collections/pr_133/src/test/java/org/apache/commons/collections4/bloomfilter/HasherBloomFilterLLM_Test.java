package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.apache.commons.collections4.bloomfilter.hasher.DynamicHasher;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.function.MD5Cyclic;
import org.junit.Test;

public class HasherBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Test
    public void testGetBitsWithIndexer() {
        final Shape shape = new Shape(new MD5Cyclic(), 3, 72, 17);
        final DynamicHasher hasher = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello").build();
        final HasherBloomFilter filter = createFilter(hasher, shape);
        final long[] lb = filter.getBits();
        assertEquals(2, lb.length);
        assertEquals(0x6203101001888c44L, lb[0]);
        assertEquals(0x60L, lb[1]);
    }

    @Test
    public void testGetBitsWithNegativeIndex() {
        final Shape shape = new Shape(new MD5Cyclic(), 3, 72, 17);
        final DynamicHasher hasher = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello").build();
        final HasherBloomFilter filter = createFilter(hasher, shape);

        // Simulate a negative index scenario
        assertThrows(IllegalArgumentException.class, () -> {
            filter.getBits();
        });
    }

    @Override
    protected AbstractBloomFilter createEmptyFilter(final Shape shape) {
        return new HasherBloomFilter(shape);
    }

    @Override
    protected HasherBloomFilter createFilter(final Hasher hasher, final Shape shape) {
        return new HasherBloomFilter(hasher, shape);
    }
}