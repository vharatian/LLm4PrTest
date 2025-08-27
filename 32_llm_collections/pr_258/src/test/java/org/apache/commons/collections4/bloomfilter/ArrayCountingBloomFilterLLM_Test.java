package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.ToIntBiFunction;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.junit.jupiter.api.Test;

public class ArrayCountingBloomFilterLLM_Test extends AbstractBloomFilterTest {

    private final Function<int[], BloomFilter> converter = counts -> {
        final BloomFilter testingFilter = new BitSetBloomFilter(shape);
        testingFilter.merge(new FixedIndexesTestHasher(shape, counts));
        return testingFilter;
    };

    @Override
    protected ArrayCountingBloomFilter createEmptyFilter(final Shape shape) {
        return new ArrayCountingBloomFilter(shape);
    }

    @Override
    protected ArrayCountingBloomFilter createFilter(final Hasher hasher, final Shape shape) {
        final ArrayCountingBloomFilter result = new ArrayCountingBloomFilter(shape);
        result.merge(hasher);
        return result;
    }

    private ArrayCountingBloomFilter createFromCounts(final int[] counts) {
        final CountingBloomFilter dummy = new ArrayCountingBloomFilter(shape) {
            @Override
            public void forEachCount(final BitCountConsumer action) {
                for (int i = 0; i < counts.length; i++) {
                    action.accept(i, counts[i]);
                }
            }
        };
        final ArrayCountingBloomFilter bf = new ArrayCountingBloomFilter(shape);
        bf.add(dummy);
        return bf;
    }

    private static void assertCounts(final CountingBloomFilter bf, final int[] expected) {
        final Map<Integer, Integer> m = new HashMap<>();
        bf.forEachCount(m::put);
        int zeros = 0;
        for (int i = 0; i < expected.length; i++) {
            if (m.get(i) == null) {
                assertEquals(expected[i], 0, "Wrong value for " + i);
                zeros++;
            } else {
                assertEquals(expected[i], m.get(i).intValue(), "Wrong value for " + i);
            }
        }
        assertEquals(expected.length - zeros, bf.cardinality());
    }

    @Test
    public void testCopy() {
        final int[] counts = {1, 2, 3};
        final ArrayCountingBloomFilter bf = createFromCounts(counts);
        final ArrayCountingBloomFilter copy = bf.copy();
        assertCounts(copy, counts);
        assertEquals(bf.isValid(), copy.isValid());
    }

    @Test
    public void testIsSparse() {
        final ArrayCountingBloomFilter bf = createEmptyFilter(shape);
        assertTrue(bf.isSparse());
    }

    @Test
    public void testForEachIndex() {
        final int[] counts = {1, 0, 3};
        final ArrayCountingBloomFilter bf = createFromCounts(counts);
        final boolean result = bf.forEachIndex(index -> counts[index] > 0);
        assertTrue(result);
    }

    @Test
    public void testForEachBitMap() {
        final int[] counts = {1, 0, 3};
        final ArrayCountingBloomFilter bf = createFromCounts(counts);
        final boolean result = bf.forEachBitMap(bitMap -> true);
        assertTrue(result);
    }

    @Test
    public void testContainsIndexProducer() {
        final int[] counts = {1, 0, 3};
        final ArrayCountingBloomFilter bf = createFromCounts(counts);
        final IndexProducer indexProducer = IndexProducer.fromIndexArray(new int[]{0, 2});
        assertTrue(bf.contains(indexProducer));
    }

    @Test
    public void testContainsBitMapProducer() {
        final int[] counts = {1, 0, 3};
        final ArrayCountingBloomFilter bf = createFromCounts(counts);
        final BitMapProducer bitMapProducer = BitMapProducer.fromIndexProducer(IndexProducer.fromIndexArray(new int[]{0, 2}));
        assertTrue(bf.contains(bitMapProducer));
    }

    @Test
    public void testAsIndexArray() {
        final int[] counts = {1, 0, 3};
        final ArrayCountingBloomFilter bf = createFromCounts(counts);
        final int[] indexArray = bf.asIndexArray();
        assertTrue(Arrays.equals(indexArray, new int[]{0, 2}));
    }
}