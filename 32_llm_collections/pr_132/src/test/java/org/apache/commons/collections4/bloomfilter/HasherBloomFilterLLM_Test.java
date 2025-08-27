package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import org.apache.commons.collections4.bloomfilter.hasher.DynamicHasher;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.function.MD5Cyclic;
import org.junit.Test;

public class HasherBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Test
    public void testCardinality() {
        final Shape shape = new Shape(new MD5Cyclic(), 3, 72, 17);
        final DynamicHasher hasher = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello").build();
        final HasherBloomFilter filter = createFilter(hasher, shape);
        assertEquals(3, filter.cardinality());
    }

    @Test
    public void testContains() {
        final Shape shape = new Shape(new MD5Cyclic(), 3, 72, 17);
        final DynamicHasher hasher1 = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello").build();
        final DynamicHasher hasher2 = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello").build();
        final HasherBloomFilter filter = createFilter(hasher1, shape);
        assertEquals(true, filter.contains(hasher2));
    }

    @Test
    public void testMerge() {
        final Shape shape = new Shape(new MD5Cyclic(), 3, 72, 17);
        final DynamicHasher hasher1 = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello").build();
        final DynamicHasher hasher2 = new DynamicHasher.Builder(new MD5Cyclic()).with("World").build();
        final HasherBloomFilter filter = createFilter(hasher1, shape);
        filter.merge(hasher2);
        final long[] lb = filter.getBits();
        assertEquals(2, lb.length);
        assertEquals(0x6203101001888c44L | 0x1dce8b78f0a2b2c6L, lb[0]);
        assertEquals(0x60L | 0x1L, lb[1]);
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