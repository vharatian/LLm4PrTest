package org.apache.commons.collections4.bloomfilter.hasher.function;

import static org.junit.Assert.assertEquals;
import java.nio.charset.StandardCharsets;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.junit.Test;

public class Murmur128x64CyclicLLM_Test extends AbstractHashFunctionTest {

    @Test
    public void applyTestWithNonNullParts() {
        final Murmur128x64Cyclic murmur = new Murmur128x64Cyclic();
        final long l1 = 0xe7eb60dabb386407L;
        final long l2 = 0xc3ca49f691f73056L;
        final byte[] buffer = "Now is the time for all good men to come to the aid of their country"
                .getBytes(StandardCharsets.UTF_8);

        // Initial call to populate parts
        long l = murmur.apply(buffer, 0);
        assertEquals(l1, l);

        // Subsequent calls to test non-null parts
        l = murmur.apply(buffer, 1);
        assertEquals(l1 + l2, l);
        l = murmur.apply(buffer, 2);
        assertEquals(l1 + l2 + l2, l);
    }

    @Override
    protected HashFunction createHashFunction() {
        return new Murmur128x64Cyclic();
    }
}