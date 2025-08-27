package org.apache.commons.collections4.bloomfilter.hasher.function;

import static org.junit.Assert.assertEquals;
import java.nio.charset.StandardCharsets;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.junit.Test;

/**
 * Test class for Murmur128x86Cyclic to cover changes and additional functionalities.
 */
public class Murmur128x86CyclicLLM_Test extends AbstractHashFunctionTest {

    /**
     * Test to verify the signature generation.
     */
    @Test
    public void testSignatureGeneration() {
        final Murmur128x86Cyclic murmur = new Murmur128x86Cyclic();
        final long expectedSignature = Signatures.getSignature(murmur);
        assertEquals(expectedSignature, murmur.getSignature());
    }

    /**
     * Test to verify the apply method with different seeds.
     */
    @Test
    public void testApplyWithDifferentSeeds() {
        final Murmur128x86Cyclic murmur = new Murmur128x86Cyclic();
        final byte[] buffer = "Test buffer for different seeds".getBytes(StandardCharsets.UTF_8);
        final long initialHash = murmur.apply(buffer, 0);
        final long hashWithSeed1 = murmur.apply(buffer, 1);
        final long hashWithSeed2 = murmur.apply(buffer, 2);

        // Verify that the hash changes with different seeds
        assertEquals(initialHash + MurmurHash3.hash128x64(buffer, 0, buffer.length, 0)[1], hashWithSeed1);
        assertEquals(hashWithSeed1 + MurmurHash3.hash128x64(buffer, 0, buffer.length, 0)[1], hashWithSeed2);
    }

    @Override
    protected HashFunction createHashFunction() {
        return new Murmur128x86Cyclic();
    }
}