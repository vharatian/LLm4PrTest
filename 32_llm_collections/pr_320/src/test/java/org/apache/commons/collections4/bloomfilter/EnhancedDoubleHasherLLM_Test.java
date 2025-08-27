package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnhancedDoubleHasherLLM_Test {

    @Test
    public void testConstructorWithByteArray() {
        byte[] buffer = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        EnhancedDoubleHasher hasher = new EnhancedDoubleHasher(buffer);
        assertEquals(0x0102030405060708L, hasher.getInitial());
        assertEquals(0x090A0B0C0D0E0F10L, hasher.getIncrement());
    }

    @Test
    public void testConstructorWithByteArrayOddLength() {
        byte[] buffer = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        EnhancedDoubleHasher hasher = new EnhancedDoubleHasher(buffer);
        assertEquals(0x0102030405060708L, hasher.getInitial());
        assertEquals(0x0900000000000000L, hasher.getIncrement());
    }

    @Test
    public void testConstructorWithByteArrayShortLength() {
        byte[] buffer = {1, 2, 3, 4};
        EnhancedDoubleHasher hasher = new EnhancedDoubleHasher(buffer);
        assertEquals(0x01020304L, hasher.getInitial());
        assertEquals(0x00000000L, hasher.getIncrement());
    }

    @Test
    public void testConstructorWithLongs() {
        EnhancedDoubleHasher hasher = new EnhancedDoubleHasher(123456789L, 987654321L);
        assertEquals(123456789L, hasher.getInitial());
        assertEquals(987654321L, hasher.getIncrement());
    }

    @Test
    public void testMod() {
        assertEquals(1, EnhancedDoubleHasher.mod(10L, 3));
        assertEquals(0, EnhancedDoubleHasher.mod(9L, 3));
        assertEquals(2, EnhancedDoubleHasher.mod(8L, 3));
    }

    @Test
    public void testIndices() {
        EnhancedDoubleHasher hasher = new EnhancedDoubleHasher(123456789L, 987654321L);
        Shape shape = new Shape(10, 100); // Example shape
        IndexProducer producer = hasher.indices(shape);
        int[] indices = producer.asIndexArray();
        assertEquals(10, indices.length);
    }

    @Test
    public void testIndicesForEachIndex() {
        EnhancedDoubleHasher hasher = new EnhancedDoubleHasher(123456789L, 987654321L);
        Shape shape = new Shape(10, 100); // Example shape
        IndexProducer producer = hasher.indices(shape);
        assertTrue(producer.forEachIndex(index -> index >= 0 && index < shape.getNumberOfBits()));
    }

    @Test
    public void testConstructorWithEmptyByteArray() {
        byte[] buffer = {};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new EnhancedDoubleHasher(buffer);
        });
        assertEquals("buffer length must be greater than 0", exception.getMessage());
    }
}