package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import org.junit.jupiter.api.Test;

public class IndexFilterLLM_Test {

    @Test
    public void testTrackerTrueConsumerTest() {
        final Shape shape = Shape.fromKM(3, 12);
        final List<Integer> consumer = new ArrayList<>();
        final IntPredicate filter = IndexFilter.create(shape, consumer::add);

        // Test when tracker.test(number) is true
        for (int i = 0; i < 12; i++) {
            assertTrue(filter.test(i));
        }
        assertEquals(12, consumer.size());
    }

    @Test
    public void testTrackerFalse() {
        final Shape shape = Shape.fromKM(3, 12);
        final List<Integer> consumer = new ArrayList<>();
        final IntPredicate filter = IndexFilter.create(shape, consumer::add);

        // Test when tracker.test(number) is false
        for (int i = 0; i < 12; i++) {
            filter.test(i); // Populate tracker
        }
        for (int i = 0; i < 12; i++) {
            assertTrue(filter.test(i));
        }
        assertEquals(12, consumer.size());
    }

    @Test
    public void testIndexOutOfBounds() {
        final Shape shape = Shape.fromKM(3, 12);
        final List<Integer> consumer = new ArrayList<>();
        final IntPredicate filter = IndexFilter.create(shape, consumer::add);

        // Test IndexOutOfBoundsException for number >= size
        assertThrows(IndexOutOfBoundsException.class, () -> filter.test(12));
        assertThrows(IndexOutOfBoundsException.class, () -> filter.test(13));
    }
}