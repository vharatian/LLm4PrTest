package org.apache.commons.collections4.bidimap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AbstractDualBidiMapLLM_Test {

    private AbstractDualBidiMap<String, String> bidiMap;
    private Map<String, String> normalMap;
    private Map<String, String> reverseMap;

    @BeforeEach
    public void setUp() {
        normalMap = mock(Map.class);
        reverseMap = mock(Map.class);
        bidiMap = new AbstractDualBidiMap<String, String>(normalMap, reverseMap) {
            @Override
            protected BidiMap<String, String> createBidiMap(Map<String, String> normalMap, Map<String, String> reverseMap, BidiMap<String, String> inverseMap) {
                return null;
            }
        };
    }

    @Test
    public void testKeySetIteratorRemoveWithoutNext() {
        Iterator<String> iterator = mock(Iterator.class);
        AbstractDualBidiMap.KeySetIterator<String> keySetIterator = new AbstractDualBidiMap.KeySetIterator<>(iterator, bidiMap);

        assertThrows(IllegalStateException.class, keySetIterator::remove);
    }

    @Test
    public void testValuesIteratorRemoveWithoutNext() {
        Iterator<String> iterator = mock(Iterator.class);
        AbstractDualBidiMap.ValuesIterator<String> valuesIterator = new AbstractDualBidiMap.ValuesIterator<>(iterator, bidiMap);

        assertThrows(IllegalStateException.class, valuesIterator::remove);
    }

    @Test
    public void testEntrySetIteratorRemoveWithoutNext() {
        Iterator<Map.Entry<String, String>> iterator = mock(Iterator.class);
        AbstractDualBidiMap.EntrySetIterator<String, String> entrySetIterator = new AbstractDualBidiMap.EntrySetIterator<>(iterator, bidiMap);

        assertThrows(IllegalStateException.class, entrySetIterator::remove);
    }

    @Test
    public void testBidiMapIteratorRemoveWithoutNext() {
        AbstractDualBidiMap.BidiMapIterator<String, String> bidiMapIterator = new AbstractDualBidiMap.BidiMapIterator<>(bidiMap);

        assertThrows(IllegalStateException.class, bidiMapIterator::remove);
    }
}