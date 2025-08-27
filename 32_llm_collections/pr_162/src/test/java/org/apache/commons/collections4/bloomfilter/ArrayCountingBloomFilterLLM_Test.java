package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
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
    public void constructorTest_Shape() {
        final Shape shape = new Shape(3, 6);
        final ArrayCountingBloomFilter bf = new ArrayCountingBloomFilter(shape);
        final long[] lb = bf.getBits();
        assertEquals(0, lb.length);
        assertCounts(bf, new int[shape.getNumberOfBits()]);
    }

    @Test
    public void mergeTest_Empty() {
        final Shape shape = new Shape(3, 6);
        final ArrayCountingBloomFilter bf1 = new ArrayCountingBloomFilter(shape);
        final ArrayCountingBloomFilter bf2 = new ArrayCountingBloomFilter(shape);
        assertTrue(bf1.merge(bf2));
        assertTrue(bf1.isValid());
        assertCounts(bf1, new int[shape.getNumberOfBits()]);
    }

    @Test
    public void removeTest_Empty() {
        final Shape shape = new Shape(3, 6);
        final ArrayCountingBloomFilter bf1 = new ArrayCountingBloomFilter(shape);
        final ArrayCountingBloomFilter bf2 = new ArrayCountingBloomFilter(shape);
        assertTrue(bf1.remove(bf2));
        assertTrue(bf1.isValid());
        assertCounts(bf1, new int[shape.getNumberOfBits()]);
    }

    @Test
    public void addTest_Empty() {
        final Shape shape = new Shape(3, 6);
        final ArrayCountingBloomFilter bf1 = new ArrayCountingBloomFilter(shape);
        final ArrayCountingBloomFilter bf2 = new ArrayCountingBloomFilter(shape);
        assertTrue(bf1.add(bf2));
        assertTrue(bf1.isValid());
        assertCounts(bf1, new int[shape.getNumberOfBits()]);
    }

    @Test
    public void subtractTest_Empty() {
        final Shape shape = new Shape(3, 6);
        final ArrayCountingBloomFilter bf1 = new ArrayCountingBloomFilter(shape);
        final ArrayCountingBloomFilter bf2 = new ArrayCountingBloomFilter(shape);
        assertTrue(bf1.subtract(bf2));
        assertTrue(bf1.isValid());
        assertCounts(bf1, new int[shape.getNumberOfBits()]);
    }
}