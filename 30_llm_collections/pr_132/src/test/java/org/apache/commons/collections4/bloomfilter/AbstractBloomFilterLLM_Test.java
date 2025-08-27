package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.PrimitiveIterator.OfInt;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.Test;

public abstract class AbstractBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Test
    public final void mergeTest_BloomFilter_Abstract() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);
        final List<Integer> lst2 = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
        final Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        final BloomFilter bf2 = createFilter(hasher2, shape);
        bf.merge(bf2);
        assertEquals(27, bf.cardinality());
    }

    @Test
    public final void mergeTest_Hasher_Abstract() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);
        final List<Integer> lst2 = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
        final Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        bf.merge(hasher2);
        assertEquals(27, bf.cardinality());
    }

    @Test
    public final void mergeTest_BloomFilter_WrongShape_Abstract() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);
        final Shape anotherShape = new Shape(testFunctionX, 3, 72, 17);
        final List<Integer> lst2 = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
        final Hasher hasher2 = new StaticHasher(lst2.iterator(), anotherShape);
        final BloomFilter bf2 = createFilter(hasher2, anotherShape);
        try {
            bf.merge(bf2);
            fail("Should throw IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    @Test
    public final void mergeTest_Hasher_WrongShape_Abstract() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);
        final Shape anotherShape = new Shape(testFunctionX, 3, 72, 17);
        final List<Integer> lst2 = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
        final Hasher hasher2 = new StaticHasher(lst2.iterator(), anotherShape);
        try {
            bf.merge(hasher2);
            fail("Should throw IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }
}