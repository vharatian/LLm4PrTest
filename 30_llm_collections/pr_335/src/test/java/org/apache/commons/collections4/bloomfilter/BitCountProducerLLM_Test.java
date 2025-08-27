package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import java.util.function.IntPredicate;
import static org.junit.jupiter.api.Assertions.*;

public class BitCountProducerLLM_Test {

    @Test
    public void testForEachCount() {
        IndexProducer indexProducer = () -> new int[]{1, 2, 3};
        BitCountProducer bitCountProducer = BitCountProducer.from(indexProducer);

        BitCountProducer.BitCountConsumer consumer = (index, count) -> {
            assertTrue(index >= 1 && index <= 3);
            assertEquals(1, count);
            return true;
        };

        assertTrue(bitCountProducer.forEachCount(consumer));
    }

    @Test
    public void testForEachIndex() {
        IndexProducer indexProducer = () -> new int[]{1, 2, 3};
        BitCountProducer bitCountProducer = BitCountProducer.from(indexProducer);

        IntPredicate predicate = index -> index >= 1 && index <= 3;

        assertTrue(bitCountProducer.forEachIndex(predicate));
    }

    @Test
    public void testAsIndexArray() {
        IndexProducer indexProducer = () -> new int[]{1, 2, 3};
        BitCountProducer bitCountProducer = BitCountProducer.from(indexProducer);

        int[] indices = bitCountProducer.asIndexArray();
        assertArrayEquals(new int[]{1, 2, 3}, indices);
    }

    @Test
    public void testNullConsumer() {
        IndexProducer indexProducer = () -> new int[]{1, 2, 3};
        BitCountProducer bitCountProducer = BitCountProducer.from(indexProducer);

        assertThrows(NullPointerException.class, () -> {
            bitCountProducer.forEachCount(null);
        });
    }

    @Test
    public void testDuplicateIndices() {
        IndexProducer indexProducer = () -> new int[]{1, 1, 2, 2, 3, 3};
        BitCountProducer bitCountProducer = BitCountProducer.from(indexProducer);

        BitCountProducer.BitCountConsumer consumer = (index, count) -> {
            assertTrue(index >= 1 && index <= 3);
            assertEquals(1, count);
            return true;
        };

        assertTrue(bitCountProducer.forEachCount(consumer));
    }
}