package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.LongPredicate;

public class BitMapProducerLLM_Test {

    @Test
    public void testForEachBitMap() {
        BitMapProducer producer = BitMapProducer.fromBitMapArray(0b1010, 0b0101);
        LongPredicate predicate = value -> (value & 0b1) == 0; // Test if the least significant bit is 0
        assertFalse(producer.forEachBitMap(predicate));
    }

    @Test
    public void testForEachBitMapPair() {
        BitMapProducer producer1 = BitMapProducer.fromBitMapArray(0b1010, 0b0101);
        BitMapProducer producer2 = BitMapProducer.fromBitMapArray(0b1100, 0b0011);
        LongBiPredicate func = (x, y) -> (x & y) == 0; // Test if there are no common bits set
        assertFalse(producer1.forEachBitMapPair(producer2, func));
    }

    @Test
    public void testAsBitMapArray() {
        BitMapProducer producer = BitMapProducer.fromBitMapArray(0b1010, 0b0101);
        long[] expected = {0b1010, 0b0101};
        assertArrayEquals(expected, producer.asBitMapArray());
    }

    @Test
    public void testFromBitMapArray() {
        long[] bitMaps = {0b1010, 0b0101};
        BitMapProducer producer = BitMapProducer.fromBitMapArray(bitMaps);
        assertArrayEquals(bitMaps, producer.asBitMapArray());
    }

    @Test
    public void testFromIndexProducer() {
        IndexProducer indexProducer = (consumer) -> {
            consumer.test(1);
            consumer.test(3);
            consumer.test(65);
            return true;
        };
        BitMapProducer producer = BitMapProducer.fromIndexProducer(indexProducer, 128);
        long[] expected = {0b1010, 0b10};
        assertArrayEquals(expected, producer.asBitMapArray());
    }

    @Test
    public void testCountingLongPredicate() {
        long[] ary = {0b1010, 0b0101};
        LongBiPredicate func = (x, y) -> (x & y) == 0; // Test if there are no common bits set
        BitMapProducer.CountingLongPredicate predicate = new BitMapProducer.CountingLongPredicate(ary, func);
        assertTrue(predicate.test(0b0100));
        assertFalse(predicate.test(0b0010));
    }

    @Test
    public void testCountingLongPredicateForEachRemaining() {
        long[] ary = {0b1010, 0b0101};
        LongBiPredicate func = (x, y) -> (x & y) == 0; // Test if there are no common bits set
        BitMapProducer.CountingLongPredicate predicate = new BitMapProducer.CountingLongPredicate(ary, func);
        predicate.test(0b0100);
        assertFalse(predicate.forEachRemaining());
    }
}