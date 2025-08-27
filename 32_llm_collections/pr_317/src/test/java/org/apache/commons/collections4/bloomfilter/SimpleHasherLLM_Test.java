package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import org.junit.jupiter.api.Test;

public class SimpleHasherLLM_Test extends AbstractHasherTest {

    @Override
    protected Hasher createHasher() {
        return new SimpleHasher(1, 1);
    }

    @Override
    protected Hasher createEmptyHasher() {
        return NullHasher.INSTANCE;
    }

    @Override
    protected int getHasherSize(Hasher hasher) {
        return 1;
    }

    @Test
    public void testUniqueIndicesWithIntPredicate() {
        Shape shape = Shape.fromKM(5, 10);
        SimpleHasher hasher = new SimpleHasher(new byte[] { 1, 1 });
        List<Integer> lst = new ArrayList<>();
        IntPredicate consumer = lst::add;
        IndexProducer producer = hasher.uniqueIndices(shape);
        producer.forEachIndex(consumer);
        assertEquals(5, lst.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 1, lst.get(i));
        }
    }
}