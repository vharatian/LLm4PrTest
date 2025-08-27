package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.IntPredicate;

public class SimpleHasherLLM_Test {

    @Test
    public void testToLong() {
        byte[] byteArray = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
        long expected = 0x0102030405060708L;
        assertEquals(expected, SimpleHasher.toLong(byteArray, 0, byteArray.length));
    }

    @Test
    public void testToLongWithOffset() {
        byte[] byteArray = {0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06};
        long expected = 0x010203040506L;
        assertEquals(expected, SimpleHasher.toLong(byteArray, 2, byteArray.length - 2));
    }

    @Test
    public void testSimpleHasherConstructorWithByteArray() {
        byte[] buffer = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C};
        SimpleHasher hasher = new SimpleHasher(buffer);
        assertNotNull(hasher);
    }

    @Test
    public void testSimpleHasherConstructorWithByteArrayThrowsException() {
        byte[] buffer = {};
        assertThrows(IllegalArgumentException.class, () -> new SimpleHasher(buffer));
    }

    @Test
    public void testSimpleHasherConstructorWithLongs() {
        SimpleHasher hasher = new SimpleHasher(0x0102030405060708L, 0x090A0B0C0D0E0F10L);
        assertNotNull(hasher);
    }

    @Test
    public void testSimpleHasherConstructorWithZeroIncrement() {
        SimpleHasher hasher = new SimpleHasher(0x0102030405060708L, 0L);
        assertEquals(0x9e3779b97f4a7c15L, hasher.getDefaultIncrement());
    }

    @Test
    public void testMod() {
        assertEquals(1, SimpleHasher.mod(10L, 3));
        assertEquals(0, SimpleHasher.mod(9L, 3));
    }

    @Test
    public void testIndices() {
        SimpleHasher hasher = new SimpleHasher(0x0102030405060708L, 0x090A0B0C0D0E0F10L);
        Shape shape = new Shape(3, 100, 0.01);
        IndexProducer producer = hasher.indices(shape);
        assertNotNull(producer);
    }

    @Test
    public void testUniqueIndices() {
        SimpleHasher hasher = new SimpleHasher(0x0102030405060708L, 0x090A0B0C0D0E0F10L);
        Shape shape = new Shape(3, 100, 0.01);
        IndexProducer producer = hasher.uniqueIndices(shape);
        assertNotNull(producer);
    }

    @Test
    public void testIndicesForEachIndex() {
        SimpleHasher hasher = new SimpleHasher(0x0102030405060708L, 0x090A0B0C0D0E0F10L);
        Shape shape = new Shape(3, 100, 0.01);
        IndexProducer producer = hasher.indices(shape);
        assertTrue(producer.forEachIndex(index -> index >= 0 && index < shape.getNumberOfBits()));
    }

    @Test
    public void testUniqueIndicesForEachIndex() {
        SimpleHasher hasher = new SimpleHasher(0x0102030405060708L, 0x090A0B0C0D0E0F10L);
        Shape shape = new Shape(3, 100, 0.01);
        IndexProducer producer = hasher.uniqueIndices(shape);
        assertTrue(producer.forEachIndex(index -> index >= 0 && index < shape.getNumberOfBits()));
    }

    @Test
    public void testIndicesAsIndexArray() {
        SimpleHasher hasher = new SimpleHasher(0x0102030405060708L, 0x090A0B0C0D0E0F10L);
        Shape shape = new Shape(3, 100, 0.01);
        IndexProducer producer = hasher.indices(shape);
        int[] indices = producer.asIndexArray();
        assertEquals(shape.getNumberOfHashFunctions(), indices.length);
    }
}