package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.IntPredicate;

public class BitCountProducerLLM_Test {

    @Test
    public void testForEachCount() {
        BitCountProducer producer = (consumer) -> {
            consumer.test(1, 2);
            consumer.test(3, 4);
            return true;
        };

        assertTrue(producer.forEachCount((index, count) -> {
            if (index == 1 && count == 2) return true;
            if (index == 3 && count == 4) return true;
            return false;
        }));
    }

    @Test
    public void testForEachIndex() {
        BitCountProducer producer = (consumer) -> {
            consumer.test(1, 2);
            consumer.test(3, 4);
            return true;
        };

        IntPredicate predicate = index -> index == 1 || index == 3;

        assertTrue(producer.forEachIndex(predicate));
    }

    @Test
    public void testFromIndexProducer() {
        IndexProducer indexProducer = predicate -> {
            predicate.test(1);
            predicate.test(2);
            return true;
        };

        BitCountProducer bitCountProducer = BitCountProducer.from(indexProducer);

        assertTrue(bitCountProducer.forEachCount((index, count) -> {
            if (index == 1 && count == 1) return true;
            if (index == 2 && count == 1) return true;
            return false;
        }));
    }

    @Test
    public void testBitCountConsumer() {
        BitCountProducer.BitCountConsumer consumer = (index, count) -> index == 1 && count == 2;

        assertTrue(consumer.test(1, 2));
        assertFalse(consumer.test(2, 1));
    }
}