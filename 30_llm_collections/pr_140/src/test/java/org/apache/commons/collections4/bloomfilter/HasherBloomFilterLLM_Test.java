package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import org.apache.commons.collections4.bloomfilter.hasher.DynamicHasher;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.function.MD5Cyclic;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.PrimitiveIterator.OfInt;

public class HasherBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Test
    public void getBitsTest_Empty() {
        BloomFilter filter = createEmptyFilter(shape);
        Assert.assertArrayEquals(new long[0], filter.getBits());
    }

    @Test
    public void getBitsTest_NonZeroSizeNoIndices() {
        final Shape shape = new Shape(new MD5Cyclic(), 3, 72, 17);
        final DynamicHasher hasher = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello").build();
        final HasherBloomFilter filter = createFilter(hasher, shape);
        final long[] lb = filter.getBits();
        assertEquals(2, lb.length);
        assertEquals(0x6203101001888c44L, lb[0]);
        assertEquals(0x60L, lb[1]);
    }

    @Test
    public void getBitsTest_LowestBitOnly() {
        BloomFilter filter = createEmptyFilter(shape);
        filter.merge(new Hasher() {
            @Override
            public OfInt getBits(Shape shape) {
                return Arrays.stream(new int[] {0}).iterator();
            }

            @Override
            public HashFunctionIdentity getHashFunctionIdentity() {
                return shape.getHashFunctionIdentity();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        Assert.assertArrayEquals(new long[] {1L}, filter.getBits());
    }

    @Override
    protected AbstractBloomFilter createEmptyFilter(final Shape shape) {
        return new HasherBloomFilter(shape);
    }

    @Override
    protected HasherBloomFilter createFilter(final Hasher hasher, final Shape shape) {
        return new HasherBloomFilter(hasher, shape);
    }
}