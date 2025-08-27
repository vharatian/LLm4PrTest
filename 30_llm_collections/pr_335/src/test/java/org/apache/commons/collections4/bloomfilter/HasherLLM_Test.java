package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HasherLLM_Test {

    @Test
    public void testUniqueIndices() {
        Shape shape = mock(Shape.class);
        IndexProducer indexProducer = mock(IndexProducer.class);
        Hasher hasher = mock(Hasher.class);

        when(hasher.indices(shape)).thenReturn(indexProducer);

        IndexProducer uniqueIndexProducer = hasher.uniqueIndices(shape);
        assertNotNull(uniqueIndexProducer);

        // Verify that the uniqueIndices method calls the indices method internally
        verify(hasher).indices(shape);
    }

    @Test
    public void testUniqueIndicesNoDuplicates() {
        Shape shape = mock(Shape.class);
        IndexProducer indexProducer = mock(IndexProducer.class);
        Hasher hasher = mock(Hasher.class);

        when(hasher.indices(shape)).thenReturn(indexProducer);

        IndexProducer uniqueIndexProducer = hasher.uniqueIndices(shape);
        assertNotNull(uniqueIndexProducer);

        // Mock the consumer and IndexFilter to ensure no duplicates
        IndexProducer.Consumer consumer = mock(IndexProducer.Consumer.class);
        IndexFilter indexFilter = mock(IndexFilter.class);
        when(IndexFilter.create(shape, consumer)).thenReturn(indexFilter);

        // Verify that the uniqueIndices method ensures no duplicates
        uniqueIndexProducer.forEachIndex(consumer);
        verify(indexFilter).forEachIndex(consumer);
    }
}