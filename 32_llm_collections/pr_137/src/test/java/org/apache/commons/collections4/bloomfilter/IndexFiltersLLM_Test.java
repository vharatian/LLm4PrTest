package org.apache.commons.collections4.bloomfilter;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.IntConsumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class IndexFiltersLLM_Test {

    @Test
    public void testDistinctIndexes_NullHasher() {
        Shape shape = mock(Shape.class);
        IntConsumer consumer = mock(IntConsumer.class);
        assertThrows(NullPointerException.class, () -> IndexFilters.distinctIndexes(null, shape, consumer));
    }

    @Test
    public void testDistinctIndexes_NullShape() {
        Hasher hasher = mock(Hasher.class);
        IntConsumer consumer = mock(IntConsumer.class);
        assertThrows(NullPointerException.class, () -> IndexFilters.distinctIndexes(hasher, null, consumer));
    }

    @Test
    public void testDistinctIndexes_NullConsumer() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        assertThrows(NullPointerException.class, () -> IndexFilters.distinctIndexes(hasher, shape, null));
    }

    @Test
    public void testDistinctIndexes_DistinctValues() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        IntConsumer consumer = mock(IntConsumer.class);

        Iterator<Integer> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(1, 2, 3);

        when(hasher.getBits(shape)).thenReturn(iterator);

        IndexFilters.distinctIndexes(hasher, shape, consumer);

        verify(consumer, times(1)).accept(1);
        verify(consumer, times(1)).accept(2);
        verify(consumer, times(1)).accept(3);
    }

    @Test
    public void testDistinctIndexes_DuplicateValues() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        IntConsumer consumer = mock(IntConsumer.class);

        Iterator<Integer> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(1, 2, 1);

        when(hasher.getBits(shape)).thenReturn(iterator);

        IndexFilters.distinctIndexes(hasher, shape, consumer);

        verify(consumer, times(1)).accept(1);
        verify(consumer, times(1)).accept(2);
    }

    @Test
    public void testDistinctIndexes_EmptyHasher() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        IntConsumer consumer = mock(IntConsumer.class);

        Iterator<Integer> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(false);

        when(hasher.getBits(shape)).thenReturn(iterator);

        IndexFilters.distinctIndexes(hasher, shape, consumer);

        verify(consumer, never()).accept(anyInt());
    }

    @Test
    public void testDistinctIndexes_IteratorThrowsException() {
        Hasher hasher = mock(Hasher.class);
        Shape shape = mock(Shape.class);
        IntConsumer consumer = mock(IntConsumer.class);

        Iterator<Integer> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenThrow(new NoSuchElementException());

        when(hasher.getBits(shape)).thenReturn(iterator);

        assertThrows(NoSuchElementException.class, () -> IndexFilters.distinctIndexes(hasher, shape, consumer));
    }
}