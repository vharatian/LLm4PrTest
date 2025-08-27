package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongPredicate;
import org.junit.jupiter.api.Test;

public class IndexProducerLLM_Test {

    @Test
    public void testAsIndexArray() {
        IndexProducer producer = IndexProducer.fromIndexArray(3, 1, 2, 1);
        int[] expected = {3, 1, 2, 1};
        assertArrayEquals(expected, producer.asIndexArray());
    }

    @Test
    public void testUniqueIndices() {
        IndexProducer producer = IndexProducer.fromIndexArray(3, 1, 2, 1);
        IndexProducer uniqueProducer = producer.uniqueIndices();
        List<Integer> lst = new ArrayList<>();
        uniqueProducer.forEachIndex(lst::add);
        int[] expected = {1, 2, 3};
        assertArrayEquals(expected, lst.stream().mapToInt(i -> i).toArray());
    }

    @Test
    public void testUniqueIndicesWithNegativeIndex() {
        IndexProducer producer = IndexProducer.fromIndexArray(3, -1, 2, 1);
        try {
            producer.uniqueIndices();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 out of bounds for length 0", e.getMessage());
        }
    }

    private static class TestingBitMapProducer implements BitMapProducer {
        long[] values;

        TestingBitMapProducer(final long[] values) {
            this.values = values;
        }

        @Override
        public boolean forEachBitMap(final LongPredicate consumer) {
            for (final long l : values) {
                if (!consumer.test(l)) {
                    return false;
                }
            }
            return true;
        }
    }
}