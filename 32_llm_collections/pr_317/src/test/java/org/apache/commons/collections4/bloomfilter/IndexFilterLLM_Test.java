package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IndexFilterLLM_Test {

    @Test
    public void testIndexOutOfBoundsException() {
        Shape shape = Shape.fromKM(3, 12);
        List<Integer> consumer = new ArrayList<>();
        IntPredicate filter = IndexFilter.create(shape, consumer::add);

        // Test for number greater than size
        assertThrows(IndexOutOfBoundsException.class, () -> filter.test(13));

        // Test for negative number
        assertThrows(IndexOutOfBoundsException.class, () -> filter.test(-1));
    }

    @Test
    public void testArrayTrackerNegativeNumber() {
        Shape shape = Shape.fromKM(3, 12);
        IndexFilter.ArrayTracker tracker = new IndexFilter.ArrayTracker(shape);

        // Test for negative number
        assertThrows(IndexOutOfBoundsException.class, () -> tracker.test(-1));
    }

    @Test
    public void testBitMapTrackerFunctionality() {
        Shape shape = Shape.fromKM(3, 64);
        IndexFilter.BitMapTracker tracker = new IndexFilter.BitMapTracker(shape);

        // Test BitMapTracker functionality
        assertTrue(tracker.test(1));
        assertTrue(tracker.test(2));
        assertTrue(tracker.test(3));
        assertTrue(!tracker.test(1)); // Should return false as 1 is already seen
    }
}