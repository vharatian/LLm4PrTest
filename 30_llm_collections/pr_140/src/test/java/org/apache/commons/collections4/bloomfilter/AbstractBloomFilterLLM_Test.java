package org.apache.commons.collections4.bloomfilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import java.util.PrimitiveIterator.OfInt;
import java.util.function.BiFunction;
import java.util.function.IntConsumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.StaticHasher;
import org.junit.Test;

public abstract class AbstractBloomFilterLLM_Test {
    private static class TestBloomFilter extends AbstractBloomFilter {
        final BitSet bits;

        protected TestBloomFilter(Shape shape, BitSet bits) {
            super(shape);
            this.bits = bits;
        }

        @Override
        public long[] getBits() {
            return bits.toLongArray();
        }

        @Override
        public StaticHasher getHasher() {
            return new StaticHasher(bits.stream().iterator(), getShape());
        }

        @Override
        public void merge(BloomFilter other) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void merge(Hasher hasher) {
            throw new UnsupportedOperationException();
        }
    }

    protected HashFunctionIdentity testFunction = new HashFunctionIdentity() {
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

    protected HashFunctionIdentity testFunctionX = new HashFunctionIdentity() {
        @Override
        public String getName() {
            return "Test FunctionX";
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
            return 1;
        }

        @Override
        public Signedness getSignedness() {
            return Signedness.SIGNED;
        }
    };

    protected Shape shape = new Shape(testFunction, 3, 72, 17);

    @Test
    public final void verifyHasherTest() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);

        try {
            bf.contains(new StaticHasher(lst.iterator(), new Shape(testFunctionX, 3, 72, 17)));
            fail("Should throw IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    @Test
    public final void verifyShapeTest() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);

        try {
            bf.andCardinality(createFilter(hasher, new Shape(testFunctionX, 3, 72, 17)));
            fail("Should throw IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    protected abstract AbstractBloomFilter createEmptyFilter(Shape shape);

    protected abstract AbstractBloomFilter createFilter(Hasher hasher, Shape shape);

    private AbstractBloomFilter createGenericFilter(Hasher hasher, Shape shape) {
        BitSet bits = new BitSet();
        hasher.getBits(shape).forEachRemaining((IntConsumer) bits::set);
        return new TestBloomFilter(shape, bits);
    }
}