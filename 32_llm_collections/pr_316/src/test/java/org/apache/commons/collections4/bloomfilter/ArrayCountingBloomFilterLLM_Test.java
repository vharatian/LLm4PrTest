package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import java.util.function.LongPredicate;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayCountingBloomFilterLLM_Test extends ArrayCountingBloomFilterTest {

    @Test
    public void testForEachBitMap() {
        Shape shape = new Shape(3, 100, 0.01);
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);
        filter.add(BitCountProducer.from(IndexProducer.fromIndexArray(new int[]{0, 1, 2, 63, 64, 127})));

        LongPredicate consumer = value -> {
            if (value == 0) {
                return true;
            }
            // Check if the value has the expected bits set
            if (value == BitMap.getLongBit(0) || value == BitMap.getLongBit(1) || value == BitMap.getLongBit(2)) {
                return true;
            }
            if (value == BitMap.getLongBit(63 - 64) || value == BitMap.getLongBit(127 - 64)) {
                return true;
            }
            return false;
        };

        assertTrue(filter.forEachBitMap(consumer));
    }

    @Test
    public void testForEachBitMapWithEmptyFilter() {
        Shape shape = new Shape(3, 100, 0.01);
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);

        LongPredicate consumer = value -> value == 0;

        assertTrue(filter.forEachBitMap(consumer));
    }
}