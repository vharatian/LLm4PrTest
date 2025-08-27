package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BloomFilterLLM_Test {

    @Test
    public void testEstimateIntersectionWithNegativeEstimate() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        BloomFilter unionFilter = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(filter1.getShape()).thenReturn(shape);
        when(filter2.getShape()).thenReturn(shape);
        when(filter1.copy()).thenReturn(unionFilter);
        when(unionFilter.merge(filter2)).thenReturn(true);

        when(shape.estimateN(filter1.cardinality())).thenReturn(100.0);
        when(shape.estimateN(filter2.cardinality())).thenReturn(150.0);
        when(shape.estimateN(unionFilter.cardinality())).thenReturn(300.0);

        int intersectionEstimate = filter1.estimateIntersection(filter2);
        assertEquals(0, intersectionEstimate);
    }

    @Test
    public void testEstimateIntersectionWithPositiveEstimate() {
        BloomFilter filter1 = mock(BloomFilter.class);
        BloomFilter filter2 = mock(BloomFilter.class);
        BloomFilter unionFilter = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(filter1.getShape()).thenReturn(shape);
        when(filter2.getShape()).thenReturn(shape);
        when(filter1.copy()).thenReturn(unionFilter);
        when(unionFilter.merge(filter2)).thenReturn(true);

        when(shape.estimateN(filter1.cardinality())).thenReturn(100.0);
        when(shape.estimateN(filter2.cardinality())).thenReturn(150.0);
        when(shape.estimateN(unionFilter.cardinality())).thenReturn(200.0);

        int intersectionEstimate = filter1.estimateIntersection(filter2);
        assertTrue(intersectionEstimate > 0);
    }
}