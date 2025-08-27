package org.apache.commons.collections4.bloomfilter;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitSetBloomFilterLLM_Test {

    @Test
    public void testCreateEmptyFilter() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter = new BitSetBloomFilter(shape);
        assertNotNull(filter);
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testAndCardinality() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter1 = new BitSetBloomFilter(shape);
        BitSetBloomFilter filter2 = new BitSetBloomFilter(shape);
        assertEquals(0, filter1.andCardinality(filter2));
    }

    @Test
    public void testOrCardinality() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter1 = new BitSetBloomFilter(shape);
        BitSetBloomFilter filter2 = new BitSetBloomFilter(shape);
        assertEquals(0, filter1.orCardinality(filter2));
    }

    @Test
    public void testXorCardinality() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter1 = new BitSetBloomFilter(shape);
        BitSetBloomFilter filter2 = new BitSetBloomFilter(shape);
        assertEquals(0, filter1.xorCardinality(filter2));
    }

    @Test
    public void testMerge() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter1 = new BitSetBloomFilter(shape);
        BitSetBloomFilter filter2 = new BitSetBloomFilter(shape);
        assertTrue(filter1.merge(filter2));
    }

    @Test
    public void testContains() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter = new BitSetBloomFilter(shape);
        Hasher hasher = new Hasher() {
            @Override
            public OfInt iterator(Shape shape) {
                return new OfInt() {
                    private int[] data = {1, 2, 3};
                    private int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < data.length;
                    }

                    @Override
                    public int nextInt() {
                        return data[index++];
                    }
                };
            }
        };
        assertFalse(filter.contains(hasher));
    }

    @Test
    public void testGetBits() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter = new BitSetBloomFilter(shape);
        assertNotNull(filter.getBits());
    }

    @Test
    public void testGetHasher() {
        Shape shape = new Shape(3, 1000, 0.01);
        BitSetBloomFilter filter = new BitSetBloomFilter(shape);
        assertNotNull(filter.getHasher());
    }
}