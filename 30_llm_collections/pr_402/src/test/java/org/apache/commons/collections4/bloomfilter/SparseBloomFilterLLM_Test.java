package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class SparseBloomFilterLLM_Test extends AbstractBloomFilterTest<SparseBloomFilter> {

    @Override
    protected SparseBloomFilter createEmptyFilter(final Shape shape) {
        return new SparseBloomFilter(shape);
    }

    @Test
    public void testIsEmpty() {
        // Create an empty filter and check if it is empty
        SparseBloomFilter filter = createEmptyFilter(getTestShape());
        assertTrue(filter.isEmpty(), "Filter should be empty");

        // Add an element and check if it is still empty
        filter.merge(IndexProducer.fromIndexArray(new int[]{1}));
        assertFalse(filter.isEmpty(), "Filter should not be empty after adding an element");

        // Clear the filter and check if it is empty again
        filter.clear();
        assertTrue(filter.isEmpty(), "Filter should be empty after clearing");
    }
}