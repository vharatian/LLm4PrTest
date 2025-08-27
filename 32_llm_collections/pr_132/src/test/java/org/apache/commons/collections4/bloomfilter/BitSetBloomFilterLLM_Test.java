package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.Test;

public class BitSetBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Test
    public void orCardinalityTest_BitSetBloomFilter() {
        final Hasher hasher = new StaticHasher(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator(), shape);
        final BitSetBloomFilter bf = createFilter(hasher, shape);
        Hasher hasher2 = new StaticHasher(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator(), shape);
        BitSetBloomFilter bf2 = createFilter(hasher2, shape);
        assertEquals(10, bf.orCardinality(bf2));
        assertEquals(10, bf2.orCardinality(bf));
        hasher2 = new StaticHasher(Arrays.asList(11, 12, 13, 14, 15).iterator(), shape);
        bf2 = createFilter(hasher2, shape);
        assertEquals(15, bf.orCardinality(bf2));
        assertEquals(15, bf2.orCardinality(bf));
    }

    @Test
    public void mergeTest_DifferentShapes() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BitSetBloomFilter bf = createFilter(hasher, shape);
        final Shape differentShape = new Shape(100, 3, 0.01);
        final List<Integer> lst2 = Arrays.asList(11, 12, 13, 14, 15);
        final Hasher hasher2 = new StaticHasher(lst2.iterator(), differentShape);
        final BloomFilter bf2 = new BitSetBloomFilter(hasher2, differentShape);
        bf.merge(bf2);
        assertEquals(15, bf.cardinality());
    }

    @Override
    protected BitSetBloomFilter createEmptyFilter(final Shape shape) {
        return new BitSetBloomFilter(shape);
    }

    @Override
    protected BitSetBloomFilter createFilter(final Hasher hasher, final Shape shape) {
        return new BitSetBloomFilter(hasher, shape);
    }
}