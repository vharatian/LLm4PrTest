package org.apache.commons.collections4.bloomfilter.hasher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator.OfInt;
import org.junit.Test;

public class StaticHasherLLM_Test {

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

    /**
     * Test to ensure that the getBits method throws IllegalArgumentException
     * when the shape parameter does not match the internal shape.
     */
    @Test
    public void testGetBits_WrongShape_ExceptionMessage() {
        final List<Integer> lst = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        final StaticHasher hasher = new StaticHasher(lst.iterator(), shape);
        try {
            hasher.getBits(new Shape(new HashFunctionIdentity() {
                @Override
                public String getName() {
                    return "Different Function";
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
            }, 3, 72, 17));
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
            assertEquals("shape (Shape [hashFunctionIdentity=Different Function, numberOfBits=72, numberOfHashFunctions=3, numberOfItems=17]) does not match internal shape (Shape [hashFunctionIdentity=Test Function, numberOfBits=72, numberOfHashFunctions=3, numberOfItems=17])", expected.getMessage());
        }
    }
}