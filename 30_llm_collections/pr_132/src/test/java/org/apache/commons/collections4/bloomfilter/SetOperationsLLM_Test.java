package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Arrays;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.Assert;
import org.junit.Test;

public class SetOperationsLLM_Test {

    private final HashFunctionIdentity testFunction = new HashFunctionIdentity() {
        @Override
        public String getName() {
            return "Test Function";
        }

        @Override
        public ProcessType getProcessType() {
            return ProcessType.CYCLIC;
        }

        @Override
        public String getProvider() {
            return "Apache Commons Collection Tests";
        }

        @Override
        public long getSignature() {
            return 0;
        }

        @Override
        public Signedness getSignedness() {
            return Signedness.SIGNED;
        }
    };

    private final Shape shape = new Shape(testFunction, 3, 72, 17);

    @Test
    public void testCosineDistanceFormatting() {
        List<Integer> lst = Arrays.asList(1, 2);
        Hasher hasher = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher, shape);
        List<Integer> lst2 = Arrays.asList(2, 3);
        Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        assertEquals(0.5, SetOperations.cosineDistance(filter1, filter2), 0.0001);
    }

    @Test
    public void testEstimateIntersectionSizeFormatting() {
        List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        Hasher hasher = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher, shape);
        lst = Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        Hasher hasher2 = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        long estimate = SetOperations.estimateIntersectionSize(filter1, filter2);
        assertEquals(1, estimate);
    }

    @Test
    public void testEstimateUnionSizeFormatting() {
        List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        Hasher hasher = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher, shape);
        lst = Arrays.asList(17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40);
        Hasher hasher2 = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        long estimate = SetOperations.estimateUnionSize(filter1, filter2);
        assertEquals(3, estimate);
    }

    @Test
    public void testHammingDistanceFormatting() {
        List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        Hasher hasher = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher, shape);
        List<Integer> lst2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        assertEquals(0, SetOperations.hammingDistance(filter1, filter2));
    }

    @Test
    public void testJaccardDistanceFormatting() {
        List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        Hasher hasher = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher, shape);
        List<Integer> lst2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        assertEquals(1.0, SetOperations.jaccardDistance(filter1, filter2), 0.0001);
    }
}