package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SynchronizedSortedBagLLM_Test {

    private SortedBag<String> sortedBag;
    private SynchronizedSortedBag<String> synchronizedSortedBag;

    @BeforeEach
    public void setUp() {
        sortedBag = mock(SortedBag.class);
        synchronizedSortedBag = SynchronizedSortedBag.synchronizedSortedBag(sortedBag);
    }

    @Test
    public void testFirst() {
        String firstElement = "first";
        when(sortedBag.first()).thenReturn(firstElement);

        synchronized (synchronizedSortedBag) {
            assertEquals(firstElement, synchronizedSortedBag.first());
        }

        verify(sortedBag).first();
    }

    @Test
    public void testLast() {
        String lastElement = "last";
        when(sortedBag.last()).thenReturn(lastElement);

        synchronized (synchronizedSortedBag) {
            assertEquals(lastElement, synchronizedSortedBag.last());
        }

        verify(sortedBag).last();
    }

    @Test
    public void testComparator() {
        Comparator<String> comparator = mock(Comparator.class);
        when(sortedBag.comparator()).thenReturn(comparator);

        synchronized (synchronizedSortedBag) {
            assertEquals(comparator, synchronizedSortedBag.comparator());
        }

        verify(sortedBag).comparator();
    }
}