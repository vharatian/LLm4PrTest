package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import java.util.function.IntPredicate;
import static org.junit.jupiter.api.Assertions.*;

public class CellProducerLLM_Test {

    @Test
    public void testUniqueIndices() {
        IndexProducer producer = new IndexProducer() {
            @Override
            public boolean forEachIndex(IntPredicate predicate) {
                predicate.test(1);
                predicate.test(2);
                predicate.test(3);
                return true;
            }
        };

        CellProducer cellProducer = CellProducer.from(producer);
        IndexProducer uniqueIndices = cellProducer.uniqueIndices();

        assertTrue(uniqueIndices.forEachIndex(index -> index >= 1 && index <= 3));
    }
}