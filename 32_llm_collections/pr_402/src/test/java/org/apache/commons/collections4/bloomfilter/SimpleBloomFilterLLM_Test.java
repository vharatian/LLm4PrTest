package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SimpleBloomFilterLLM_Test extends AbstractBloomFilterTest<SimpleBloomFilter> {

    @Override
    protected SimpleBloomFilter createEmptyFilter(final Shape shape) {
        return new SimpleBloomFilter(shape);
    }

    @Test
    public void testIsEmptyWhenNew() {
        final SimpleBloomFilter filter = createEmptyFilter(getTestShape());
        assertTrue(filter.isEmpty(), "Newly created filter should be empty");
    }

    @Test
    public void testIsEmptyAfterClear() {
        final SimpleBloomFilter filter = createEmptyFilter(getTestShape());
        filter.merge(p -> p.test(2L));
        filter.clear();
        assertTrue(filter.isEmpty(), "Filter should be empty after clear");
    }

    @Test
    public void testIsNotEmptyAfterMerge() {
        final SimpleBloomFilter filter = createEmptyFilter(getTestShape());
        filter.merge(p -> p.test(2L));
        assertFalse(filter.isEmpty(), "Filter should not be empty after merge");
    }
}