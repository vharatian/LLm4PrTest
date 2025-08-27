package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import java.util.function.IntPredicate;
import static org.junit.jupiter.api.Assertions.*;

public class IndexProducerLLM_Test {

    @Test
    public void testFromIndexArray() {
        IndexProducer producer = IndexProducer.fromIndexArray(1, 2, 3, 4, 5);
        assertTrue(producer.forEachIndex(value -> value >= 1 && value <= 5));
        assertFalse(producer.forEachIndex(value -> value > 5));
    }

    @Test
    public void testFromBitMapProducer() {
        BitMapProducer bitMapProducer = predicate -> {
            long[] bitmaps = {0b101, 0b10}; // Indices 0, 2, 65
            for (long bitmap : bitmaps) {
                if (!predicate.test(bitmap)) {
                    return false;
                }
            }
            return true;
        };

        IndexProducer producer = IndexProducer.fromBitMapProducer(bitMapProducer);
        assertTrue(producer.forEachIndex(value -> value == 0 || value == 2 || value == 65));
        assertFalse(producer.forEachIndex(value -> value == 1));
    }

    @Test
    public void testAsIndexArray() {
        IndexProducer producer = IndexProducer.fromIndexArray(3, 1, 4, 1, 5, 9);
        int[] expected = {1, 3, 4, 5, 9};
        int[] result = producer.asIndexArray();
        assertArrayEquals(expected, result);
    }
}