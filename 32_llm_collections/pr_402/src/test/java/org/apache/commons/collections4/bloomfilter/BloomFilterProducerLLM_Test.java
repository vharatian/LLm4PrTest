package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Predicate;
import java.util.function.BiPredicate;

public class BloomFilterProducerLLM_Test {

    @Test
    public void testForEachBloomFilter() {
        BloomFilterProducer producer = BloomFilterProducer.fromBloomFilterArray(
            new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01)),
            new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01))
        );

        Predicate<BloomFilter> predicate = filter -> filter.getShape().getNumberOfHashFunctions() == 3;
        assertTrue(producer.forEachBloomFilter(predicate));
    }

    @Test
    public void testAsBloomFilterArray() {
        BloomFilter filter1 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilter filter2 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilterProducer producer = BloomFilterProducer.fromBloomFilterArray(filter1, filter2);

        BloomFilter[] filters = producer.asBloomFilterArray();
        assertEquals(2, filters.length);
        assertSame(filter1, filters[0]);
        assertSame(filter2, filters[1]);
    }

    @Test
    public void testForEachBloomFilterPair() {
        BloomFilter filter1 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilter filter2 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilterProducer producer1 = BloomFilterProducer.fromBloomFilterArray(filter1);
        BloomFilterProducer producer2 = BloomFilterProducer.fromBloomFilterArray(filter2);

        BiPredicate<BloomFilter, BloomFilter> biPredicate = (f1, f2) -> f1.getShape().getNumberOfHashFunctions() == f2.getShape().getNumberOfHashFunctions();
        assertTrue(producer1.forEachBloomFilterPair(producer2, biPredicate));
    }

    @Test
    public void testFlatten() {
        BloomFilter filter1 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilter filter2 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilterProducer producer = BloomFilterProducer.fromBloomFilterArray(filter1, filter2);

        BloomFilter flattened = producer.flatten();
        assertNotNull(flattened);
        assertEquals(3, flattened.getShape().getNumberOfHashFunctions());
    }

    @Test
    public void testFromBloomFilterArray() {
        BloomFilter filter1 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilter filter2 = new SimpleBloomFilter(new BloomFilter.Shape(3, 100, 0.01));
        BloomFilterProducer producer = BloomFilterProducer.fromBloomFilterArray(filter1, filter2);

        BloomFilter[] filters = producer.asBloomFilterArray();
        assertEquals(2, filters.length);
        assertSame(filter1, filters[0]);
        assertSame(filter2, filters[1]);
    }
}