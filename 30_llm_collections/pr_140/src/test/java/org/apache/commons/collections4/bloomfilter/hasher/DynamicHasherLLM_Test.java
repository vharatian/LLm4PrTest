package org.apache.commons.collections4.bloomfilter.hasher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.PrimitiveIterator.OfInt;

import org.apache.commons.collections4.bloomfilter.hasher.function.MD5Cyclic;
import org.junit.Before;
import org.junit.Test;

public class DynamicHasherLLM_Test {

    private DynamicHasher.Builder builder;
    private Shape shape;
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

    @Before
    public void setup() {
        builder = new DynamicHasher.Builder(new MD5Cyclic());
        shape = new Shape(new MD5Cyclic(), 3, 72, 17);
    }

    @Test
    public void testGetBits_NoValuesIterator() {
        // Test when buffers are empty
        DynamicHasher hasher = builder.build();
        OfInt iter = hasher.getBits(shape);
        assertFalse(iter.hasNext());
        try {
            iter.next();
            fail("Should have thrown NoSuchElementException");
        } catch (NoSuchElementException expected) {
        }
    }

    @Test
    public void testGetBits_Iterator() {
        // Test when buffers are not empty
        final int[] expected = {6, 69, 44, 19, 10, 57, 48, 23, 70, 61, 36, 11, 2, 49, 24, 15, 62};
        final Hasher hasher = builder.with("Hello").build();
        final OfInt iter = hasher.getBits(shape);
        for (final int element : expected) {
            assertTrue(iter.hasNext());
            assertEquals(element, iter.nextInt());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testGetBits_IteratorMultipleBuffers() {
        // Test when multiple buffers are added
        final int[] expected = {6, 69, 44, 19, 10, 57, 48, 23, 70, 61, 36, 11, 2, 49, 24, 15, 62, 1, 63, 53, 43, 17, 7, 69,
                59, 49, 39, 13, 3, 65, 55, 45, 35, 25};
        final Hasher hasher = builder.with("Hello").with("World").build();
        final OfInt iter = hasher.getBits(shape);
        for (final int element : expected) {
            assertTrue(iter.hasNext());
            assertEquals(element, iter.nextInt());
        }
        assertFalse(iter.hasNext());
        try {
            iter.next();
            fail("Should have thrown NoSuchElementException");
        } catch (final NoSuchElementException ignore) {
        }
    }

    @Test
    public void testGetBits_WrongShape() {
        // Test when shape has a different hash function identity
        final Hasher hasher = builder.with("Hello").build();
        try {
            hasher.getBits(new Shape(testFunction, 3, 72, 17));
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    @Test
    public void testIsEmpty() {
        // Test isEmpty method
        DynamicHasher hasher = builder.build();
        assertTrue(hasher.isEmpty());
        final OfInt iter = hasher.getBits(shape);
        assertFalse(iter.hasNext());
        try {
            iter.next();
            fail("Should have thrown NoSuchElementException");
        } catch (final NoSuchElementException expected) {
        }
        assertFalse(builder.with("Hello").build().isEmpty());
    }
}