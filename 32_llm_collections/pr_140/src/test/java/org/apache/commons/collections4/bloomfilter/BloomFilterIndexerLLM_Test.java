package org.apache.commons.collections4.bloomfilter;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BloomFilterIndexerLLM_Test {

    @Test
    public void testGetLongIndexWithNegativeValues() {
        // Testing the behavior of getLongIndex with negative values
        for (final int index : getNegativeIndexes()) {
            Assert.assertTrue(BloomFilterIndexer.getLongIndex(index) < 0);
        }
    }

    @Test
    public void testGetLongBitWithNegativeValues() {
        // Testing the behavior of getLongBit with negative values
        for (final int index : getNegativeIndexes()) {
            Assert.assertEquals(1L << (64 - (index & 0x3f)), BloomFilterIndexer.getLongBit(index));
        }
    }

    private static int[] getNegativeIndexes() {
        final Random rng = ThreadLocalRandom.current();
        ArrayList<Integer> indexes = new ArrayList<>(40);
        for (int i = 0; i < 10; i++) {
            indexes.add(-(rng.nextInt() >>> 1));
            indexes.add(-rng.nextInt(23647826));
            indexes.add(-rng.nextInt(245));
        }
        indexes.removeIf(i -> i == 0);
        indexes.add(-1);
        indexes.add(-2);
        indexes.add(-63);
        indexes.add(-64);
        indexes.add(Integer.MIN_VALUE);
        return indexes.stream().mapToInt(Integer::intValue).toArray();
    }
}