package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.junit.Test;

public class ArrayCountingBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Override
    protected ArrayCountingBloomFilter createEmptyFilter(final Shape shape) {
        return new ArrayCountingBloomFilter(shape);
    }

    @Override
    protected ArrayCountingBloomFilter createFilter(final Hasher hasher, final Shape shape) {
        return new ArrayCountingBloomFilter(hasher, shape);
    }

    private ArrayCountingBloomFilter createFromCounts(int[] counts) {
        final CountingBloomFilter dummy = new ArrayCountingBloomFilter(shape) {
            @Override
            public void forEachCount(BitCountConsumer action) {
                for (int i = 0; i < counts.length; i++) {
                    action.accept(i, counts[i]);
                }
            }
        };
        final ArrayCountingBloomFilter bf = new ArrayCountingBloomFilter(shape);
        bf.add(dummy);
        return bf;
    }

    private static void assertCounts(CountingBloomFilter bf, int[] expected) {
        final Map<Integer, Integer> m = new HashMap<>();
        bf.forEachCount(m::put);
        int zeros = 0;
        for (int i = 0; i < expected.length; i++) {
            if (m.get(i) == null) {
                assertEquals("Wrong value for " + i, expected[i], 0);
                zeros++;
            } else {
                assertEquals("Wrong value for " + i, expected[i], m.get(i).intValue());
            }
        }
        assertEquals(expected.length - zeros, bf.cardinality());
    }

    @Test
    public void testInvalidStateAfterOverflow() {
        final Hasher hasher = new FixedIndexesTestHasher(shape, 1, 2, 3);
        final ArrayCountingBloomFilter bf = createFilter(hasher, shape);
        ArrayCountingBloomFilter bf2 = createFromCounts(new int[] {0, 0, Integer.MAX_VALUE});
        bf.merge(bf2);
        assertTrue(bf.isValid());
        assertCounts(bf, new int[] {0, 1, 2, 1});
        assertTrue(bf2.isValid());
        bf2.merge(bf);
        assertFalse("Merge should overflow and the filter is invalid", bf2.isValid());
        assertCounts(bf2, new int[] {0, 1, 1 + Integer.MAX_VALUE, 1});
    }

    @Test
    public void testInvalidStateAfterNegativeCounts() {
        final Hasher hasher = new FixedIndexesTestHasher(shape, 1, 2, 3);
        ArrayCountingBloomFilter bf = createFilter(hasher, shape);
        final Hasher hasher2 = new FixedIndexesTestHasher(shape, 2);
        ArrayCountingBloomFilter bf2 = createFilter(hasher2, shape);
        bf.remove(bf2);
        assertTrue(bf.isValid());
        assertCounts(bf, new int[] {0, 1, 0, 1});
        assertTrue(bf2.isValid());
        bf2.remove(bf);
        assertFalse("Remove should create negative counts and the filter is invalid", bf2.isValid());
        assertCounts(bf2, new int[] {0, -1, 1, -1});
    }

    @Test
    public void testMergeWithDuplicates() {
        final int[] expected = {0, 1, 1, 0, 0, 1};
        final Hasher hasher = new FixedIndexesTestHasher(shape, 1, 2, 2, 5);
        final ArrayCountingBloomFilter bf = createFilter(hasher, shape);
        final long[] lb = bf.getBits();
        assertEquals(1, lb.length);
        assertEquals(0b100110L, lb[0]);
        assertCounts(bf, expected);
    }

    @Test
    public void testContainsWithHasher() {
        final Hasher hasher = new FixedIndexesTestHasher(shape, 1, 2, 5);
        final ArrayCountingBloomFilter bf = createFilter(hasher, shape);
        assertFalse(bf.contains(new BitSetBloomFilter(new FixedIndexesTestHasher(shape, 3, 4), shape)));
        assertTrue(bf.contains(new BitSetBloomFilter(new FixedIndexesTestHasher(shape, 2, 5), shape)));
    }

    @Test
    public void testAddOperation() {
        assertCountingOperation(new int[] {5, 2, 1},
                new int[] {0, 6, 4, 1},
                CountingBloomFilter::add,
                true,
                new int[] {5, 8, 5, 1});
    }

    @Test
    public void testSubtractOperation() {
        assertCountingOperation(new int[] {5, 9, 1, 1},
                new int[] {0, 2, 1},
                CountingBloomFilter::subtract,
                true,
                new int[] {5, 7, 0, 1});
    }

    private void assertCountingOperation(int[] counts1, int[] counts2,
                                         BiPredicate<ArrayCountingBloomFilter, ArrayCountingBloomFilter> operation,
                                         boolean isValid, int[] expected) {
        final ArrayCountingBloomFilter bf1 = createFromCounts(counts1);
        final ArrayCountingBloomFilter bf2 = createFromCounts(counts2);
        final boolean result = operation.test(bf1, bf2);
        assertEquals(isValid, result);
        assertEquals(isValid, bf1.isValid());
        assertCounts(bf1, expected);
    }
}