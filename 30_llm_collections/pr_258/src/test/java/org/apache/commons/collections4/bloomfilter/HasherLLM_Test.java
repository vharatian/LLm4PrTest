package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HasherLLM_Test {

    @Test
    void testIndices() {
        Shape shape = new Shape(3, 100, 0.01);
        Hasher hasher = new SimpleHasher();
        IndexProducer producer = hasher.indices(shape);
        assertNotNull(producer);
    }

    @Test
    void testUniqueIndices() {
        Shape shape = new Shape(3, 100, 0.01);
        Hasher hasher = new SimpleHasher();
        IndexProducer producer = hasher.uniqueIndices(shape);
        assertNotNull(producer);
    }

    @Test
    void testIndexFilterCreate() {
        Shape shape = new Shape(3, 100, 0.01);
        IntPredicate consumer = value -> true;
        Hasher.IndexFilter filter = Hasher.IndexFilter.create(shape, consumer);
        assertNotNull(filter);
    }

    @Test
    void testIndexFilterTest() {
        Shape shape = new Shape(3, 100, 0.01);
        IntPredicate consumer = value -> true;
        Hasher.IndexFilter filter = Hasher.IndexFilter.create(shape, consumer);
        assertTrue(filter.test(10));
        assertThrows(IndexOutOfBoundsException.class, () -> filter.test(101));
    }

    @Test
    void testArrayTracker() {
        Shape shape = new Shape(3, 100, 0.01);
        Hasher.IndexFilter.ArrayTracker tracker = new Hasher.IndexFilter.ArrayTracker(shape);
        assertTrue(tracker.test(10));
        assertFalse(tracker.test(10));
        assertThrows(IndexOutOfBoundsException.class, () -> tracker.test(-1));
    }

    @Test
    void testBitMapTracker() {
        Shape shape = new Shape(3, 100, 0.01);
        Hasher.IndexFilter.BitMapTracker tracker = new Hasher.IndexFilter.BitMapTracker(shape);
        assertTrue(tracker.test(10));
        assertFalse(tracker.test(10));
    }
}