package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.collections4.bloomfilter.hasher.DynamicHasher;
import org.apache.commons.collections4.bloomfilter.hasher.Hasher;
import org.apache.commons.collections4.bloomfilter.hasher.Shape;
import org.apache.commons.collections4.bloomfilter.hasher.function.MD5Cyclic;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.PrimitiveIterator.OfInt;

public class HasherBloomFilterLLM_Test extends AbstractBloomFilterTest {

    @Test
    public void containsTest() {
        final Shape shape = new Shape(new MD5Cyclic(), 3, 72, 17);
        final DynamicHasher hasher1 = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello", StandardCharsets.UTF_8).build();
        final DynamicHasher hasher2 = new DynamicHasher.Builder(new MD5Cyclic()).with("Hello", StandardCharsets.UTF_8).build();
        final HasherBloomFilter filter = createFilter(hasher1, shape);
        assertTrue(filter.contains(hasher2));
    }

    @Override
    protected AbstractBloomFilter createEmptyFilter(final Shape shape) {
        return new HasherBloomFilter(shape);
    }

    @Override
    protected HasherBloomFilter createFilter(final Hasher hasher, final Shape shape) {
        return new HasherBloomFilter(hasher, shape);
    }
}