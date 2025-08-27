package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.ArrayList;

public class DynamicHasherLLM_Test {

    private HashFunction mockFunction;
    private List<byte[]> buffers;

    @BeforeEach
    public void setUp() {
        mockFunction = (data, seed) -> {
            // Simple mock hash function for testing
            int hash = 0;
            for (byte b : data) {
                hash += b;
            }
            return hash + seed;
        };
        buffers = new ArrayList<>();
        buffers.add(new byte[]{1, 2, 3});
        buffers.add(new byte[]{4, 5, 6});
    }

    @Test
    public void testConstructor() {
        DynamicHasher hasher = new DynamicHasher(mockFunction, buffers);
        assertNotNull(hasher);
    }

    @Test
    public void testGetHashFunctionIdentity() {
        DynamicHasher hasher = new DynamicHasher(mockFunction, buffers);
        assertEquals(mockFunction, hasher.getHashFunctionIdentity());
    }

    @Test
    public void testIsEmpty() {
        DynamicHasher hasher = new DynamicHasher(mockFunction, buffers);
        assertFalse(hasher.isEmpty());

        DynamicHasher emptyHasher = new DynamicHasher(mockFunction, new ArrayList<>());
        assertTrue(emptyHasher.isEmpty());
    }

    @Test
    public void testGetBits() {
        Shape mockShape = new Shape() {
            @Override
            public int getNumberOfHashFunctions() {
                return 2;
            }

            @Override
            public int getNumberOfBits() {
                return 10;
            }

            @Override
            public HashFunctionIdentity getHashFunctionIdentity() {
                return mockFunction;
            }
        };

        DynamicHasher hasher = new DynamicHasher(mockFunction, buffers);
        PrimitiveIterator.OfInt iterator = hasher.getBits(mockShape);

        assertTrue(iterator.hasNext());
        assertEquals(6, iterator.nextInt());
        assertTrue(iterator.hasNext());
        assertEquals(7, iterator.nextInt());
        assertTrue(iterator.hasNext());
        assertEquals(15, iterator.nextInt());
        assertTrue(iterator.hasNext());
        assertEquals(16, iterator.nextInt());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testGetBitsWithInvalidShape() {
        Shape invalidShape = new Shape() {
            @Override
            public int getNumberOfHashFunctions() {
                return 2;
            }

            @Override
            public int getNumberOfBits() {
                return 10;
            }

            @Override
            public HashFunctionIdentity getHashFunctionIdentity() {
                return new HashFunctionIdentity() {
                    // Different identity for testing
                };
            }
        };

        DynamicHasher hasher = new DynamicHasher(mockFunction, buffers);
        assertThrows(IllegalArgumentException.class, () -> hasher.getBits(invalidShape));
    }

    @Test
    public void testIteratorNoSuchElementException() {
        Shape mockShape = new Shape() {
            @Override
            public int getNumberOfHashFunctions() {
                return 1;
            }

            @Override
            public int getNumberOfBits() {
                return 10;
            }

            @Override
            public HashFunctionIdentity getHashFunctionIdentity() {
                return mockFunction;
            }
        };

        DynamicHasher hasher = new DynamicHasher(mockFunction, buffers);
        PrimitiveIterator.OfInt iterator = hasher.getBits(mockShape);

        iterator.nextInt(); // First element
        iterator.nextInt(); // Second element
        assertThrows(NoSuchElementException.class, iterator::nextInt);
    }

    @Test
    public void testBuilder() {
        DynamicHasher.Builder builder = new DynamicHasher.Builder(mockFunction);
        builder.with(new byte[]{7, 8, 9});
        DynamicHasher hasher = builder.build();

        assertNotNull(hasher);
        assertFalse(hasher.isEmpty());
    }

    @Test
    public void testBuilderWithByte() {
        DynamicHasher.Builder builder = new DynamicHasher.Builder(mockFunction);
        builder.with((byte) 10);
        DynamicHasher hasher = builder.build();

        assertNotNull(hasher);
        assertFalse(hasher.isEmpty());
    }

    @Test
    public void testBuilderWithString() {
        DynamicHasher.Builder builder = new DynamicHasher.Builder(mockFunction);
        builder.with("test");
        DynamicHasher hasher = builder.build();

        assertNotNull(hasher);
        assertFalse(hasher.isEmpty());
    }
}