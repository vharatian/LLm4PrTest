package org.apache.commons.collections4.bloomfilter.hasher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity.ProcessType;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity.Signedness;
import java.util.ArrayList;
import org.junit.Test;

public class ShapeLLM_Test {

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

    @Test
    public void constructor_probability_bits_hash_BadNumberOfBitsTest() {
        try {
            new Shape(testFunction, 0.5, 0, 1);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    @Test
    public void constructor_probability_bits_hash_BadNumberOfHashFunctionsTest() {
        try {
            new Shape(testFunction, 0.5, 24, 0);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    @Test
    public void constructor_probability_bits_hash_BadProbabilityTest() {
        try {
            new Shape(testFunction, 0.0, 24, 1);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
        try {
            new Shape(testFunction, -1.0, 24, 1);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
        try {
            new Shape(testFunction, -1.5, 24, 1);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
        try {
            new Shape(testFunction, 1.0, 24, 1);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
        try {
            new Shape(testFunction, 2.0, 24, 1);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    @Test
    public void constructor_probability_bits_hashTest() {
        final Shape filterConfig = new Shape(testFunction, 0.1, 24, 3);
        assertEquals(24, filterConfig.getNumberOfBits());
        assertEquals(3, filterConfig.getNumberOfHashFunctions());
        assertEquals(5, filterConfig.getNumberOfItems());
        assertEquals(0.100375138, filterConfig.getProbability(), 0.000001);
    }

    @Test
    public void constructor_items_probability_NumberOfBitsOverflowTest() {
        try {
            new Shape(testFunction, Integer.MAX_VALUE, 1.0 / 10);
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
        }
    }

    @Test
    public void constructor_items_probability_Test() {
        final Shape shape = new Shape(testFunction, 5, 0.1);
        assertEquals(24, shape.getNumberOfBits());
        assertEquals(3, shape.getNumberOfHashFunctions());
        assertEquals(5, shape.getNumberOfItems());
        assertEquals(0.100375138, shape.getProbability(), 0.000001);
    }

    @Test
    public void equalsTest() {
        final Shape shape = new Shape(testFunction, 5, 0.1);
        assertEquals(shape, shape);
        assertEquals(shape, new Shape(testFunction, 5, 1.0 / 10));
        assertNotEquals(shape, null);
        assertNotEquals(shape, new Shape(testFunction, 5, 1.0 / 11));
        assertNotEquals(shape, new Shape(testFunction, 4, 1.0 / 10));
        final int numberOfBits = 10000;
        final int numberOfItems = 15;
        final int numberOfHashFunctions = 4;
        assertEquals(new Shape(testFunction, numberOfItems, numberOfBits, numberOfHashFunctions),
                new Shape(testFunction, numberOfItems + 1, numberOfBits, numberOfHashFunctions));
        assertNotEquals(new Shape(testFunction, numberOfItems, numberOfBits, numberOfHashFunctions),
                new Shape(testFunction, numberOfItems, numberOfBits + 1, numberOfHashFunctions));
        assertNotEquals(new Shape(testFunction, numberOfItems, numberOfBits, numberOfHashFunctions),
                new Shape(testFunction, numberOfItems, numberOfBits, numberOfHashFunctions + 1));
        final HashFunctionIdentity testFunction2 = new HashFunctionIdentity() {
            @Override
            public String getName() {
                return "Test Function2";
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
        assertNotEquals(shape, new Shape(testFunction2, 4, 1.0 / 10));
    }

    @Test
    public void hashCodeTest() {
        final ArrayList<HashFunctionIdentity> list = new ArrayList<>();
        list.add(new HashFunctionIdentityImpl("Provider", "Name", Signedness.SIGNED, ProcessType.ITERATIVE, 0L));
        list.add(new HashFunctionIdentityImpl("PROVIDER", "Name", Signedness.SIGNED, ProcessType.ITERATIVE, 0L));
        list.add(new HashFunctionIdentityImpl("Provider2", "Name", Signedness.SIGNED, ProcessType.ITERATIVE, 0L));
        list.add(new HashFunctionIdentityImpl("Provider", "name", Signedness.SIGNED, ProcessType.ITERATIVE, 0L));
        list.add(new HashFunctionIdentityImpl("Provider", "NAME", Signedness.SIGNED, ProcessType.ITERATIVE, 0L));
        list.add(new HashFunctionIdentityImpl("Provider", "Other", Signedness.SIGNED, ProcessType.ITERATIVE, 0L));
        list.add(new HashFunctionIdentityImpl("Provider", "Name", Signedness.UNSIGNED, ProcessType.ITERATIVE, 0L));
        list.add(new HashFunctionIdentityImpl("Provider", "Name", Signedness.SIGNED, ProcessType.CYCLIC, 0L));
        list.add(new HashFunctionIdentityImpl("Provider", "Name", Signedness.SIGNED, ProcessType.ITERATIVE, 1L));
        final int numberOfItems = 30;
        final int numberOfBits = 3000;
        final int numberOfHashFunctions = 10;
        final Shape shape1 = new Shape(list.get(0), numberOfItems, numberOfBits, numberOfHashFunctions);
        assertEquals(shape1, shape1);
        for (int i = 1; i < list.size(); i++) {
            final Shape shape2 = new Shape(list.get(i), numberOfItems, numberOfBits, numberOfHashFunctions);
            assertEquals(shape2, shape2);
            if (shape1.equals(shape2)) {
                assertEquals(shape1.hashCode(), shape2.hashCode());
            }
        }
    }
}