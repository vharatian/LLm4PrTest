package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator.OfInt;
import java.util.TreeSet;

public class StaticHasherLLM_Test {

    private Shape shape;
    private StaticHasher staticHasher;

    @BeforeEach
    public void setUp() {
        shape = new Shape(new HashFunctionIdentity() {
            @Override
            public String getName() {
                return "TestHashFunction";
            }

            @Override
            public String getProvider() {
                return "TestProvider";
            }

            @Override
            public String getVersion() {
                return "1.0";
            }

            @Override
            public ProcessType getProcessType() {
                return ProcessType.CYCLIC;
            }

            @Override
            public int getSignature() {
                return 0;
            }
        }, 100, 3, 0.01);
    }

    @Test
    public void testConstructorWithStaticHasherAndShape() {
        StaticHasher hasher = new StaticHasher(new StaticHasher(Arrays.asList(1, 2, 3).iterator(), shape), shape);
        assertNotNull(hasher);
        assertEquals(shape, hasher.getShape());
    }

    @Test
    public void testConstructorWithHasherAndShape() {
        Hasher hasher = new StaticHasher(Arrays.asList(1, 2, 3).iterator(), shape);
        StaticHasher staticHasher = new StaticHasher(hasher, shape);
        assertNotNull(staticHasher);
        assertEquals(shape, staticHasher.getShape());
    }

    @Test
    public void testConstructorWithIteratorAndShape() {
        Iterator<Integer> iter = Arrays.asList(1, 2, 3).iterator();
        StaticHasher hasher = new StaticHasher(iter, shape);
        assertNotNull(hasher);
        assertEquals(shape, hasher.getShape());
    }

    @Test
    public void testConstructorWithIteratorAndShapeThrowsExceptionForInvalidIndex() {
        Iterator<Integer> iter = Arrays.asList(-1, 101).iterator();
        assertThrows(IllegalArgumentException.class, () -> new StaticHasher(iter, shape));
    }

    @Test
    public void testGetShape() {
        StaticHasher hasher = new StaticHasher(Arrays.asList(1, 2, 3).iterator(), shape);
        assertEquals(shape, hasher.getShape());
    }

    @Test
    public void testIsEmpty() {
        StaticHasher hasher = new StaticHasher(Arrays.asList().iterator(), shape);
        assertTrue(hasher.isEmpty());

        hasher = new StaticHasher(Arrays.asList(1).iterator(), shape);
        assertFalse(hasher.isEmpty());
    }

    @Test
    public void testGetHashFunctionIdentity() {
        StaticHasher hasher = new StaticHasher(Arrays.asList(1, 2, 3).iterator(), shape);
        assertEquals(shape.getHashFunctionIdentity(), hasher.getHashFunctionIdentity());
    }

    @Test
    public void testSize() {
        StaticHasher hasher = new StaticHasher(Arrays.asList(1, 2, 3).iterator(), shape);
        assertEquals(3, hasher.size());
    }

    @Test
    public void testGetBits() {
        StaticHasher hasher = new StaticHasher(Arrays.asList(1, 2, 3).iterator(), shape);
        OfInt bits = hasher.getBits(shape);
        assertTrue(bits.hasNext());
        assertEquals(1, bits.nextInt());
        assertEquals(2, bits.nextInt());
        assertEquals(3, bits.nextInt());
        assertFalse(bits.hasNext());
    }

    @Test
    public void testGetBitsThrowsExceptionForMismatchedShape() {
        Shape differentShape = new Shape(new HashFunctionIdentity() {
            @Override
            public String getName() {
                return "DifferentHashFunction";
            }

            @Override
            public String getProvider() {
                return "DifferentProvider";
            }

            @Override
            public String getVersion() {
                return "1.0";
            }

            @Override
            public ProcessType getProcessType() {
                return ProcessType.CYCLIC;
            }

            @Override
            public int getSignature() {
                return 1;
            }
        }, 100, 3, 0.01);

        StaticHasher hasher = new StaticHasher(Arrays.asList(1, 2, 3).iterator(), shape);
        assertThrows(IllegalArgumentException.class, () -> hasher.getBits(differentShape));
    }
}