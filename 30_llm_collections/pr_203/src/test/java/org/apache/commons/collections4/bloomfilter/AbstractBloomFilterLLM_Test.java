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
        
        protected TestBloomFilter(final Shape shape, final BitSet bits) {
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
        public boolean merge(final BloomFilter other) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean merge(final Hasher hasher) {
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
    public final void opCardinalityTest() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);
        
        final List<Integer> lst2 = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
        final Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        final BloomFilter bf2 = createFilter(hasher2, shape);
        
        // Testing AND operation
        assertEquals(7, bf.andCardinality(bf2));
        
        // Testing OR operation
        assertEquals(27, bf.orCardinality(bf2));
        
        // Testing XOR operation
        assertEquals(20, bf.xorCardinality(bf2));
    }
    
    @Test
    public final void opCardinalityTest_ExtraLongs() {
        final List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        final Hasher hasher = new StaticHasher(lst.iterator(), shape);
        final BloomFilter bf = createFilter(hasher, shape);
        
        final List<Integer> lst2 = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69);
        final Hasher hasher2 = new StaticHasher(lst2.iterator(), shape);
        final BloomFilter bf2 = createFilter(hasher2, shape);
        
        // Testing AND operation
        assertEquals(7, bf.andCardinality(bf2));
        assertEquals(7, bf2.andCardinality(bf));
        
        // Testing OR operation
        assertEquals(27, bf.orCardinality(bf2));
        assertEquals(27, bf2.orCardinality(bf));
        
        // Testing XOR operation
        assertEquals(20, bf.xorCardinality(bf2));
        assertEquals(20, bf2.xorCardinality(bf));
    }
    
    protected abstract AbstractBloomFilter createEmptyFilter(Shape shape);
    protected abstract AbstractBloomFilter createFilter(Hasher hasher, Shape shape);
}