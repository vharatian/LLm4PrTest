package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetOperationsLLM_Test {

    @Test
    void testHammingDistance() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(first.getShape()).thenReturn(shape);
        when(second.getShape()).thenReturn(shape);
        when(first.xorCardinality(second)).thenReturn(5);

        int result = SetOperations.hammingDistance(first, second);
        assertEquals(5, result);
    }

    @Test
    void testHammingDistanceDifferentShapes() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);

        when(first.getShape()).thenReturn(mock(Shape.class));
        when(second.getShape()).thenReturn(mock(Shape.class));

        assertThrows(IllegalArgumentException.class, () -> SetOperations.hammingDistance(first, second));
    }

    @Test
    void testJaccardSimilarity() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(first.getShape()).thenReturn(shape);
        when(second.getShape()).thenReturn(shape);
        when(first.orCardinality(second)).thenReturn(10);
        when(first.xorCardinality(second)).thenReturn(5);

        double result = SetOperations.jaccardSimilarity(first, second);
        assertEquals(0.5, result);
    }

    @Test
    void testJaccardDistance() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(first.getShape()).thenReturn(shape);
        when(second.getShape()).thenReturn(shape);
        when(first.orCardinality(second)).thenReturn(10);
        when(first.xorCardinality(second)).thenReturn(5);

        double result = SetOperations.jaccardDistance(first, second);
        assertEquals(0.5, result);
    }

    @Test
    void testCosineSimilarity() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(first.getShape()).thenReturn(shape);
        when(second.getShape()).thenReturn(shape);
        when(first.andCardinality(second)).thenReturn(3);
        when(first.cardinality()).thenReturn(4);
        when(second.cardinality()).thenReturn(9);

        double result = SetOperations.cosineSimilarity(first, second);
        assertEquals(0.5, result);
    }

    @Test
    void testCosineDistance() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(first.getShape()).thenReturn(shape);
        when(second.getShape()).thenReturn(shape);
        when(first.andCardinality(second)).thenReturn(3);
        when(first.cardinality()).thenReturn(4);
        when(second.cardinality()).thenReturn(9);

        double result = SetOperations.cosineDistance(first, second);
        assertEquals(0.5, result);
    }

    @Test
    void testEstimateSize() {
        BloomFilter filter = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(filter.getShape()).thenReturn(shape);
        when(shape.getNumberOfBits()).thenReturn(100);
        when(shape.getNumberOfHashFunctions()).thenReturn(4);
        when(filter.cardinality()).thenReturn(10);

        long result = SetOperations.estimateSize(filter);
        assertEquals(3, result);
    }

    @Test
    void testEstimateUnionSize() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(first.getShape()).thenReturn(shape);
        when(second.getShape()).thenReturn(shape);
        when(shape.getNumberOfBits()).thenReturn(100);
        when(shape.getNumberOfHashFunctions()).thenReturn(4);
        when(first.orCardinality(second)).thenReturn(20);

        long result = SetOperations.estimateUnionSize(first, second);
        assertEquals(6, result);
    }

    @Test
    void testEstimateIntersectionSize() {
        BloomFilter first = mock(BloomFilter.class);
        BloomFilter second = mock(BloomFilter.class);
        Shape shape = mock(Shape.class);

        when(first.getShape()).thenReturn(shape);
        when(second.getShape()).thenReturn(shape);
        when(shape.getNumberOfBits()).thenReturn(100);
        when(shape.getNumberOfHashFunctions()).thenReturn(4);
        when(first.orCardinality(second)).thenReturn(20);
        when(first.cardinality()).thenReturn(10);
        when(second.cardinality()).thenReturn(15);

        long result = SetOperations.estimateIntersectionSize(first, second);
        assertEquals(2, result);
    }
}