package org.apache.commons.collections4.bloomfilter;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BloomFilterIndexerLLM_Test {

    @Test
    public void testCheckPositive() {
        // Test with zero
        BloomFilterIndexer.checkPositive(0);
        
        // Test with positive values
        for (final int index : getIndexes()) {
            BloomFilterIndexer.checkPositive(index);
        }
    }

    @Test
    public void testGetLongIndexWithPositiveValues() {
        for (final int index : getIndexes()) {
            Assert.assertEquals(index / Long.SIZE, BloomFilterIndexer.getLongIndex(index));
        }
    }

    @Test
    public void testGetLongBitWithPositiveValues() {
        for (final int index : getIndexes()) {
            Assert.assertEquals(1L << (index % Long.SIZE), BloomFilterIndexer.getLongBit(index));
        }
    }

    private static int[] getIndexes() {
        final Random rng = ThreadLocalRandom.current();
        ArrayList<Integer> indexes = new ArrayList<>(40);
        for (int i = 0; i < 10; i++) {
            indexes.add(rng.nextInt() >>> 1);
            indexes.add(rng.nextInt(23647826));
            indexes.add(rng.nextInt(245));
        }
        indexes.removeIf(i -> i == 0);
        indexes.add(1);
        indexes.add(2);
        indexes.add(63);
        indexes.add(64);
        indexes.add(Integer.MAX_VALUE);
        return indexes.stream().mapToInt(Integer::intValue).toArray();
    }
}