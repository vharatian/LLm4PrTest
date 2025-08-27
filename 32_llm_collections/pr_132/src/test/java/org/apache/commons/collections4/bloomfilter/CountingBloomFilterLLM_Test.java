package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.Assert;
import org.junit.Test;

public class CountingBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Test
    public void testAndCardinalityWithWhitespaceChanges() {
        final Hasher hasher = new StaticHasher(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator(), shape);
        final CountingBloomFilter bf = createFilter(hasher, shape);
        Hasher hasher2 = new StaticHasher(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator(), shape);
        CountingBloomFilter bf2 = createFilter(hasher2, shape);
        assertEquals(10, bf.andCardinality(bf2));
        assertEquals(10, bf2.andCardinality(bf));
        hasher2 = new StaticHasher(Arrays.asList(1, 2, 3, 4, 5).iterator(), shape);
        bf2 = createFilter(hasher2, shape);
        assertEquals(5, bf.andCardinality(bf2));
        assertEquals(5, bf2.andCardinality(bf));
        hasher2 = new StaticHasher(Arrays.asList(11, 12, 13, 14, 15).iterator(), shape);
        bf2 = createFilter(hasher2, shape);
        assertEquals(0, bf.andCardinality(bf2));
        assertEquals(0, bf2.andCardinality(bf));
    }

    @Test
    public void testRemoveWithWhitespaceChanges() {
        final int[] values = {
            0, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1
        };
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < values.length; i++) {
            map.put(i, values[i]);
        }
        final CountingBloomFilter bf = new CountingBloomFilter(map, shape);
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        bf.remove(hasher);
        assertEquals(17, bf.cardinality());
        final Map<Integer, Integer> map2 = new HashMap<>();
        bf.getCounts().forEach(e -> map2.put(e.getKey(), e.getValue()));
        for (int i = 11; i < values.length; i++) {
            assertNotNull(map2.get(i));
            assertEquals(1, map2.get(i).intValue());
        }
    }
}