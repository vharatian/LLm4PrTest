package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Arrays;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.jupiter.api.Test;

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
    public final void cardinalityTest() {
        List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        Hasher hasher = new StaticHasher(lst.iterator(), shape);
        BloomFilter filter = new HasherBloomFilter(hasher, shape);
        assertEquals(17, SetOperations.cardinality(filter));
    }

    @Test
    public final void andCardinalityTest() {
        List<Integer> lst1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> lst2 = Arrays.asList(4, 5, 6, 7, 8);
        Hasher hasher1 = new StaticHasher(lst1.iterator(), shape);
        Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher1, shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        assertEquals(2, SetOperations.andCardinality(filter1, filter2));
    }

    @Test
    public final void orCardinalityTest() {
        List<Integer> lst1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> lst2 = Arrays.asList(4, 5, 6, 7, 8);
        Hasher hasher1 = new StaticHasher(lst1.iterator(), shape);
        Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher1, shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        assertEquals(8, SetOperations.orCardinality(filter1, filter2));
    }

    @Test
    public final void xorCardinalityTest() {
        List<Integer> lst1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> lst2 = Arrays.asList(4, 5, 6, 7, 8);
        Hasher hasher1 = new StaticHasher(lst1.iterator(), shape);
        Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher1, shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        assertEquals(6, SetOperations.xorCardinality(filter1, filter2));
    }

    @Test
    public final void jaccardSimilarityTest() {
        List<Integer> lst1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> lst2 = Arrays.asList(4, 5, 6, 7, 8);
        Hasher hasher1 = new StaticHasher(lst1.iterator(), shape);
        Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        BloomFilter filter1 = new HasherBloomFilter(hasher1, shape);
        BloomFilter filter2 = new HasherBloomFilter(hasher2, shape);
        assertEquals(0.25, SetOperations.jaccardSimilarity(filter1, filter2), 0.0001);
    }

    @Test
    public final void jaccardSimilarityTest_NoValues() {
        BloomFilter filter1 = new HasherBloomFilter(shape);
        BloomFilter filter2 = new HasherBloomFilter(shape);
        assertEquals(0.0, SetOperations.jaccardSimilarity(filter1, filter2), 0.0001);
    }
}