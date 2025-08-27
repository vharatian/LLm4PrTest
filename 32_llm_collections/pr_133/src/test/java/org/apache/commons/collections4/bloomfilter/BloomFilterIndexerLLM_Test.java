package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BloomFilterIndexerLLM_Test {

    @Test
    void testCheckPositiveWithPositiveIndex() {
        // This should not throw an exception
        BloomFilterIndexer.checkPositive(1);
    }

    @Test
    void testCheckPositiveWithZeroIndex() {
        // This should not throw an exception
        BloomFilterIndexer.checkPositive(0);
    }

    @Test
    void testCheckPositiveWithNegativeIndex() {
        // This should throw an IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> BloomFilterIndexer.checkPositive(-1));
    }

    @Test
    void testGetLongIndexWithPositiveIndex() {
        // Test with a positive index
        assertEquals(0, BloomFilterIndexer.getLongIndex(0));
        assertEquals(0, BloomFilterIndexer.getLongIndex(63));
        assertEquals(1, BloomFilterIndexer.getLongIndex(64));
        assertEquals(1, BloomFilterIndexer.getLongIndex(127));
        assertEquals(2, BloomFilterIndexer.getLongIndex(128));
    }

    @Test
    void testGetLongIndexWithNegativeIndex() {
        // Test with a negative index, should return a negative value
        assertTrue(BloomFilterIndexer.getLongIndex(-1) < 0);
    }

    @Test
    void testGetLongBitWithPositiveIndex() {
        // Test with a positive index
        assertEquals(1L, BloomFilterIndexer.getLongBit(0));
        assertEquals(2L, BloomFilterIndexer.getLongBit(1));
        assertEquals(4L, BloomFilterIndexer.getLongBit(2));
        assertEquals(1L << 63, BloomFilterIndexer.getLongBit(63));
    }

    @Test
    void testGetLongBitWithNegativeIndex() {
        // Test with a negative index, behavior is not defined but should not crash
        assertNotNull(BloomFilterIndexer.getLongBit(-1));
    }
}