package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HasherLLM_Test {

    @Test
    public void testUniqueIndicesWithNullConsumer() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);

        assertThrows(NullPointerException.class, () -> {
            hasher.uniqueIndices(shape).forEachIndex(null);
        });
    }

    @Test
    public void testUniqueIndicesDelegatesToIndices() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        IndexProducer indexProducer = mock(IndexProducer.class);
        when(hasher.indices(shape)).thenReturn(indexProducer);

        IndexProducer uniqueIndexProducer = hasher.uniqueIndices(shape);

        assertNotNull(uniqueIndexProducer);
        verify(hasher).indices(shape);
    }

    @Test
    public void testUniqueIndicesFiltersIndices() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        IndexProducer indexProducer = mock(IndexProducer.class);
        when(hasher.indices(shape)).thenReturn(indexProducer);

        IndexFilter indexFilter = mock(IndexFilter.class);
        when(IndexFilter.create(shape, any())).thenReturn(indexFilter);

        IndexProducer uniqueIndexProducer = hasher.uniqueIndices(shape);

        assertNotNull(uniqueIndexProducer);
        verify(indexProducer).forEachIndex(indexFilter);
    }
}