package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HasherLLM_Test {

    @Test
    public void testIndices() {
        Shape shape = mock(Shape.class);
        IndexProducer producer = mock(IndexProducer.class);
        Hasher hasher = mock(Hasher.class);

        when(hasher.indices(shape)).thenReturn(producer);

        assertEquals(producer, hasher.indices(shape));
        verify(hasher).indices(shape);
    }
}