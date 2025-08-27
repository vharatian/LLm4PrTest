package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.PrimitiveIterator;

public class HasherLLM_Test {

    @Test
    public void testGetHashFunctionIdentity() {
        Hasher hasher = new MockHasher();
        assertNotNull(hasher.getHashFunctionIdentity());
    }

    @Test
    public void testIsEmpty() {
        Hasher hasher = new MockHasher();
        assertFalse(hasher.isEmpty());
    }

    @Test
    public void testGetBits() {
        Hasher hasher = new MockHasher();
        Shape shape = new Shape("MockHasher", 100, 3);
        PrimitiveIterator.OfInt iterator = hasher.getBits(shape);
        assertNotNull(iterator);
    }

    @Test
    public void testBuilderWithByte() {
        Hasher.Builder builder = new MockHasherBuilder();
        builder.with((byte) 1);
        Hasher hasher = builder.build();
        assertNotNull(hasher);
    }

    @Test
    public void testBuilderWithByteArray() {
        Hasher.Builder builder = new MockHasherBuilder();
        builder.with(new byte[]{1, 2, 3});
        Hasher hasher = builder.build();
        assertNotNull(hasher);
    }

    @Test
    public void testBuilderWithString() {
        Hasher.Builder builder = new MockHasherBuilder();
        builder.with("test");
        Hasher hasher = builder.build();
        assertNotNull(hasher);
    }

    // Mock classes for testing
    private static class MockHasher implements Hasher {
        @Override
        public HashFunctionIdentity getHashFunctionIdentity() {
            return new HashFunctionIdentity() {
                @Override
                public String getName() {
                    return "MockHasher";
                }
            };
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public PrimitiveIterator.OfInt getBits(Shape shape) {
            return new PrimitiveIterator.OfInt() {
                private int current = 0;

                @Override
                public int nextInt() {
                    return current++;
                }

                @Override
                public boolean hasNext() {
                    return current < 10;
                }
            };
        }
    }

    private static class MockHasherBuilder implements Hasher.Builder {
        @Override
        public Hasher build() {
            return new MockHasher();
        }

        @Override
        public Builder with(byte property) {
            return this;
        }

        @Override
        public Builder with(byte[] property) {
            return this;
        }

        @Override
        public Builder with(String property) {
            return this;
        }
    }
}