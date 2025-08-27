package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.LongPredicate;

public class BitMapProducerLLM_Test {

    @Test
    public void testFromBitMapArray() {
        long[] bitMaps = {0b101, 0b110};
        BitMapProducer producer = BitMapProducer.fromBitMapArray(bitMaps);
        long[] result = producer.asBitMapArray();
        assertArrayEquals(bitMaps, result);
    }

    @Test
    public void testForEachBitMap() {
        long[] bitMaps = {0b101, 0b110};
        BitMapProducer producer = BitMapProducer.fromBitMapArray(bitMaps);
        boolean allMatch = producer.forEachBitMap(value -> (value & 0b100) != 0);
        assertTrue(allMatch);
    }

    @Test
    public void testForEachBitMapPair() {
        long[] bitMaps1 = {0b101, 0b110};
        long[] bitMaps2 = {0b011, 0b100};
        BitMapProducer producer1 = BitMapProducer.fromBitMapArray(bitMaps1);
        BitMapProducer producer2 = BitMapProducer.fromBitMapArray(bitMaps2);
        boolean allMatch = producer1.forEachBitMapPair(producer2, (a, b) -> (a & b) == 0);
        assertTrue(allMatch);
    }

    @Test
    public void testFromIndexProducer() {
        IndexProducer indexProducer = new IndexProducer() {
            @Override
            public boolean forEachIndex(IntPredicate predicate) {
                predicate.test(0);
                predicate.test(1);
                predicate.test(63);
                return true;
            }
        };
        BitMapProducer producer = BitMapProducer.fromIndexProducer(indexProducer, 64);
        long[] expected = {0b11, 0b1};
        assertArrayEquals(expected, producer.asBitMapArray());
    }
}