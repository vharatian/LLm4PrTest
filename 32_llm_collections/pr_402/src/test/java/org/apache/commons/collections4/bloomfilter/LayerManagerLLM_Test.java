package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LayerManagerLLM_Test {

    private Supplier<BloomFilter> mockSupplier;
    private Predicate<LayerManager> mockExtendCheck;
    private Consumer<LinkedList<BloomFilter>> mockCleanup;
    private BloomFilter mockBloomFilter;
    private LayerManager layerManager;

    @BeforeEach
    public void setUp() {
        mockSupplier = mock(Supplier.class);
        mockExtendCheck = mock(Predicate.class);
        mockCleanup = mock(Consumer.class);
        mockBloomFilter = mock(BloomFilter.class);

        when(mockSupplier.get()).thenReturn(mockBloomFilter);
        layerManager = new LayerManager(mockSupplier, mockExtendCheck, mockCleanup, true);
    }

    @Test
    public void testAddFilter() {
        assertEquals(1, layerManager.getDepth());
        layerManager.clear();
        assertEquals(1, layerManager.getDepth());
    }

    @Test
    public void testGetDepth() {
        assertEquals(1, layerManager.getDepth());
    }

    @Test
    public void testGet() {
        assertEquals(mockBloomFilter, layerManager.get(0));
        assertThrows(NoSuchElementException.class, () -> layerManager.get(1));
    }

    @Test
    public void testGetTarget() {
        when(mockExtendCheck.test(layerManager)).thenReturn(false);
        assertEquals(mockBloomFilter, layerManager.getTarget());

        when(mockExtendCheck.test(layerManager)).thenReturn(true);
        BloomFilter newMockBloomFilter = mock(BloomFilter.class);
        when(mockSupplier.get()).thenReturn(newMockBloomFilter);
        assertEquals(newMockBloomFilter, layerManager.getTarget());
    }

    @Test
    public void testClear() {
        layerManager.clear();
        assertEquals(1, layerManager.getDepth());
    }

    @Test
    public void testForEachBloomFilter() {
        Predicate<BloomFilter> mockPredicate = mock(Predicate.class);
        when(mockPredicate.test(mockBloomFilter)).thenReturn(true);
        assertTrue(layerManager.forEachBloomFilter(mockPredicate));

        when(mockPredicate.test(mockBloomFilter)).thenReturn(false);
        assertFalse(layerManager.forEachBloomFilter(mockPredicate));
    }

    @Test
    public void testCopy() {
        LayerManager copy = layerManager.copy();
        assertNotSame(layerManager, copy);
        assertEquals(layerManager.getDepth(), copy.getDepth());
        assertNotSame(layerManager.get(0), copy.get(0));
    }

    @Test
    public void testNext() {
        layerManager.next();
        verify(mockCleanup).accept(any());
        assertEquals(2, layerManager.getDepth());
    }

    @Test
    public void testBuilder() {
        LayerManager.Builder builder = LayerManager.builder();
        assertNotNull(builder);

        builder.setSupplier(mockSupplier);
        builder.setExtendCheck(mockExtendCheck);
        builder.setCleanup(mockCleanup);

        LayerManager builtManager = builder.build();
        assertNotNull(builtManager);
    }

    @Test
    public void testExtendCheckAdvanceOnPopulated() {
        Predicate<LayerManager> predicate = LayerManager.ExtendCheck.advanceOnPopulated();
        when(mockBloomFilter.isEmpty()).thenReturn(false);
        assertTrue(predicate.test(layerManager));
    }

    @Test
    public void testExtendCheckNeverAdvance() {
        Predicate<LayerManager> predicate = LayerManager.ExtendCheck.neverAdvance();
        assertFalse(predicate.test(layerManager));
    }

    @Test
    public void testExtendCheckAdvanceOnCount() {
        Predicate<LayerManager> predicate = LayerManager.ExtendCheck.advanceOnCount(2);
        assertFalse(predicate.test(layerManager));
        assertTrue(predicate.test(layerManager));
    }

    @Test
    public void testExtendCheckAdvanceOnSaturation() {
        when(mockBloomFilter.getShape()).thenReturn(mock(BloomFilter.Shape.class));
        when(mockBloomFilter.getShape().estimateN(anyInt())).thenReturn(10.0);
        Predicate<LayerManager> predicate = LayerManager.ExtendCheck.advanceOnSaturation(5.0);
        assertTrue(predicate.test(layerManager));
    }

    @Test
    public void testCleanupNoCleanup() {
        Consumer<LinkedList<BloomFilter>> consumer = LayerManager.Cleanup.noCleanup();
        LinkedList<BloomFilter> list = new LinkedList<>();
        list.add(mockBloomFilter);
        consumer.accept(list);
        assertEquals(1, list.size());
    }

    @Test
    public void testCleanupOnMaxSize() {
        Consumer<LinkedList<BloomFilter>> consumer = LayerManager.Cleanup.onMaxSize(1);
        LinkedList<BloomFilter> list = new LinkedList<>();
        list.add(mockBloomFilter);
        list.add(mock(BloomFilter.class));
        consumer.accept(list);
        assertEquals(1, list.size());
    }

    @Test
    public void testCleanupRemoveEmptyTarget() {
        when(mockBloomFilter.cardinality()).thenReturn(0);
        Consumer<LinkedList<BloomFilter>> consumer = LayerManager.Cleanup.removeEmptyTarget();
        LinkedList<BloomFilter> list = new LinkedList<>();
        list.add(mockBloomFilter);
        consumer.accept(list);
        assertTrue(list.isEmpty());
    }
}